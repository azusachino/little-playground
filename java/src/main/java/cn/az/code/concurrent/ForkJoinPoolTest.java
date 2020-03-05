package cn.az.code.concurrent;

import java.util.concurrent.ForkJoinPool;

/**
 * @author az
 * @date 2020-03-05
 */
public class ForkJoinPoolTest {

    private static ForkJoinPool pool = ForkJoinPool.commonPool();

    public static void main(String[] args) {
        pool.execute(() -> {
            for (int i = 0; i < 10; i++) {
               System.out.println(Thread.currentThread().getName());
            }
        });

    }
}
