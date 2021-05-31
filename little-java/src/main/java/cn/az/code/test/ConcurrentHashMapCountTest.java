package cn.az.code.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ycpang
 * @since 2021-02-03 15:04
 */
public class ConcurrentHashMapCountTest {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMapCountTest t = new ConcurrentHashMapCountTest();
        t.goodUsage();
        t.normalUsage();
    }

    private static final int LOOP_COUNT = 10_000_000;

    private static final int THREAD_COUNT = 10;

    private static final int ITEM_COUNT = 1_000;

    private Map<String, Long> normalUsage() throws InterruptedException {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>(ITEM_COUNT);

        ForkJoinPool pool = new ForkJoinPool(THREAD_COUNT);

        pool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            synchronized (map) {
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1L);
                }
            }
        }));

        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.HOURS);
        return map;
    }

    private Map<String, Long> goodUsage() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> map = new ConcurrentHashMap<>(ITEM_COUNT);

        ForkJoinPool pool = new ForkJoinPool(THREAD_COUNT);

        pool.execute(() -> IntStream.rangeClosed(1, LOOP_COUNT).parallel().forEach(i -> {
            String key = "item" + ThreadLocalRandom.current().nextInt(ITEM_COUNT);
            map.computeIfAbsent(key, k -> new LongAdder()).increment();
        }));

        pool.shutdown();
        pool.awaitTermination(1L, TimeUnit.HOURS);
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
    }
}
