package cn.az.code.thread;

import cn.hutool.log.Log;

/**
 * 在预设的地点检测flag。然后就是wait/notify配合使用。
 *
 * @author az
 */
public class ThreadPauseDemo {

    private static final Log log = Log.get();

    public static void main(String[] args) {
        PauseRunnable pr = new PauseRunnable();
        new Thread(pr).start();

        pr.tellToPause();

        doSomething(6);

        pr.tellToResume();
    }

    /**
     * 16:01:04.986 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - thread is running, 1
     * 16:01:10.001 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - thread is finished,1
     * 16:01:10.001 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - check status of stop flag: true
     * 16:01:10.001 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - thread paused
     * 16:01:10.983 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - resumed...
     * 16:01:10.983 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - thread is running 2
     * 16:01:13.984 [Thread-0] INFO cn.az.code.thread.ThreadPauseDemo - thread is finished 2
     */
    static class PauseRunnable implements Runnable {

        volatile boolean pause;

        void tellToPause() {
            pause = true;
        }

        void tellToResume() {
            synchronized (this) {
                this.notify();
            }
        }

        @Override
        public void run() {
            log.info("thread is running, {} ", 1);
            doSomething(5);
            log.info("thread is finished,{} ", 1);

            log.info("check status of stop flag: " + pause);

            if (pause) {
                log.info("thread paused");
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    log.error(e.getLocalizedMessage(), e);
                }
                log.info("resumed...");
            }
            log.info("thread is running " + 2);
            doSomething(3);
            log.info("thread is finished " + 2);
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
