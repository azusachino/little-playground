package cn.az.code.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * LegacyCountDownLatchDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see CountDownLatch
 * @since 2020-03-16
 */
public class LegacyCountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.submit(() -> {
                action();
                latch.countDown();
            });
        }

        // main lock
        latch.await();
        service.shutdown();
    }

    private static void action() {
        System.out.printf("Thread[%s] is running...\n", Thread.currentThread().getName());
    }

    private static class CountDownLatch {
        private int count;

        private CountDownLatch(int count) {
            this.count = count;
        }

        public void await() throws InterruptedException {
            // count > 0 wait
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            synchronized (this) {
                while (count > 0) {
                    wait(); // block current thread
                }
            }
        }

        public void countDown() {
            synchronized (this) {
                if (count < 1) {
                    return;
                }
                count--;
                if (count == 0) {
                    notifyAll();
                }
            }
        }
    }
}
