package cn.az.code.concurrent;

import java.util.concurrent.ForkJoinPool;

/**
 * @author az
 * @date 2020-03-05
 */
public class ForkJoinPoolTest {

    private static ForkJoinPool pool = ForkJoinPool.commonPool();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            pool.submit(ForkJoinPoolTest::action);
        }
    }

    public static void action() {
        System.out.printf("Thread [%s] is running...", Thread.currentThread().getName());
    }
}
