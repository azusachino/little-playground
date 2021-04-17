package cn.az.code.concurrent;

import java.util.concurrent.ForkJoinPool;

/**
 * @author az
 */
public class ForkJoinPoolTest {

    private static final ForkJoinPool POOL = ForkJoinPool.commonPool();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            POOL.submit(ForkJoinPoolTest::action);
        }
    }

    public static void action() {
        System.out.printf("Thread [%s] is running...", Thread.currentThread().getName());
    }
}
