package cn.az.code.thread;

import cn.hutool.log.Log;

/**
 * join方法可以让某个线程插到自己前面，等它执行完，自己才会继续执行。
 *
 * @author az
 */
public class ThreadJoinDemo {

    private static final Log log = Log.get();

    public static void main(String[] args) {
        JoinRunnable jr = new JoinRunnable();
        Thread t = new Thread(jr);
        t.start();
        doSomething(1);
        try {
            t.join();
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        log.info("after join, finally my turn");
    }

    /**
     * 16:36:25.454 [Thread-0] INFO cn.az.code.thread.ThreadJoinDemo - thread is running, 10
     * 16:36:30.468 [Thread-0] INFO cn.az.code.thread.ThreadJoinDemo - thread is finished, 10
     * 16:36:35.442 [main] INFO cn.az.code.thread.ThreadJoinDemo - after join, finally my turn
     */
    static class JoinRunnable implements Runnable {

        @Override
        public void run() {
            log.info("thread is running, {}", Thread.currentThread().getId());
            doSomething(5);
            log.info("thread is finished, {} ", Thread.currentThread().getId());
        }
    }

    static void doSomething(int t) {
        try {
            Thread.sleep(t * 1000);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
