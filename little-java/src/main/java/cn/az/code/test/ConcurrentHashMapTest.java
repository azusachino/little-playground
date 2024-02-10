package cn.az.code.test;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cn.az.code.util.LogUtil;

/**
 * @author ycpang
 * @since 2021-02-03 14:37
 */
@SpringBootTest
public class ConcurrentHashMapTest {

    private static final int THREAD_SIZE = 10;

    private static final int ITEM_COUNT = 1000;

    private ConcurrentHashMap<String, Long> createMap(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString(),
                        Function.identity(),
                        ((o1, o2) -> o2), ConcurrentHashMap::new));
    }

    @Test
    public String wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = createMap(ITEM_COUNT - 100);
        LogUtil.info("init size: {}" + map.size());

        ForkJoinPool fjp = new ForkJoinPool(THREAD_SIZE);

        fjp.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    // 需要加锁
                    int gap = ITEM_COUNT - map.size();
                    LogUtil.info("current gap is {}" + gap);
                    map.putAll(createMap(gap));
                }));

        fjp.shutdown();
        fjp.awaitTermination(1, TimeUnit.HOURS);
        fjp.close();
        LogUtil.info("final size is {}" + map.size());
        return "wrong";
    }

    @Test
    public String ok() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = createMap(ITEM_COUNT - 100);
        LogUtil.info("init size: {}" + map.size());

        ForkJoinPool fjp = new ForkJoinPool(THREAD_SIZE);

        fjp.execute(() -> IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> {
                    synchronized (map) {
                        int gap = ITEM_COUNT - map.size();
                        LogUtil.info("current gap is {}" + gap);
                        map.putAll(createMap(gap));
                    }
                }));

        fjp.shutdown();
        fjp.awaitTermination(1, TimeUnit.HOURS);
        fjp.close();
        LogUtil.info("final size is {}" + map.size());
        return "ok";
    }
}
