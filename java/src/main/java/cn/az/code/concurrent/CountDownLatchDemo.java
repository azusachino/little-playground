package cn.az.code.concurrent;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @author az
 * @date 2020/3/5
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException{
        final CountDownLatch latch = new CountDownLatch(2);
        ExecutorService service = ThreadUtil.newExecutor(3);

        service.execute(() -> {
            System.out.println("subThread" + Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("subThread" + Thread.currentThread().getName() + " is complete");
        });

        service.execute(() -> {
            System.out.println("subThread" + Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
            System.out.println("subThread" + Thread.currentThread().getName() + " is complete");
        });

        System.out.println("waiting two subThread");
        latch.await();
        System.out.println("two subThreads complete");
        service.execute(() -> System.out.println("main Thread..."));

    }
}
