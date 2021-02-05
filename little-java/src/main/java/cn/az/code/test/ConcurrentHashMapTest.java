package cn.az.code.test;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author ycpang
 * @since 2021-02-03 14:37
 */
@Slf4j
@SpringBootTest
public class ConcurrentHashMapTest {

    private static final int THREAD_SIZE = 10;

    private static final int ITEM_COUNT = 1000;

    private ConcurrentHashMap<String, Long> createMap(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.fastUUID().toString(true),
                        Function.identity(),
                        ((o1, o2) -> o2), ConcurrentHashMap::new));
    }

    @Test
    public String wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = createMap(ITEM_COUNT - 100);
        log.info("init size: {}", map.size());

        ForkJoinPool fjp = new ForkJoinPool(THREAD_SIZE);

        fjp.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    // 需要加锁
                    int gap = ITEM_COUNT - map.size();
                    log.info("current gap is {}", gap);
                    map.putAll(createMap(gap));
                }));

        fjp.shutdown();
        fjp.awaitTermination(1, TimeUnit.HOURS);
        log.info("final size is {}", map.size());
        return "wrong";
    }

    @Test
    public String ok() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = createMap(ITEM_COUNT - 100);
        log.info("init size: {}", map.size());

        ForkJoinPool fjp = new ForkJoinPool(THREAD_SIZE);

        fjp.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    synchronized (map) {
                        int gap = ITEM_COUNT - map.size();
                        log.info("current gap is {}", gap);
                        map.putAll(createMap(gap));
                    }
                }));

        fjp.shutdown();
        fjp.awaitTermination(1, TimeUnit.HOURS);
        log.info("final size is {}", map.size());
        return "ok";
    }
}

