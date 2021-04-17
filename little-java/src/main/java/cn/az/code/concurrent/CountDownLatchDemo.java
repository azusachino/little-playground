package cn.az.code.concurrent;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author az
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(4);
        ExecutorService service = ThreadUtil.newExecutor(5);
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                action();
                latch.countDown(); // -1
            });
        }
        // waiting for finish
        // when count > 0, blocking
        latch.await();
        System.out.println("two subThreads complete");
        // close executor
        service.shutdown();
    }

    public static void action() {
        System.out.printf("Thread[%s] is running...\n", Thread.currentThread().getName());
    }
}
