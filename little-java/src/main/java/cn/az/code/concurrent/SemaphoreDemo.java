package cn.az.code.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author az
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        int n = 8;
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < n; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private final int num;
        private final Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         */
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("worker" + this.num + " is working...");
                Thread.sleep(2000);
                semaphore.release();
                System.out.println("worker" + this.num + " release his working position...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
