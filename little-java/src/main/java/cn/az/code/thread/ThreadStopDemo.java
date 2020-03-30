package cn.az.code.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程在预设的地点检测flag，来决定是否停止。
 * @author az
 * @date 2020/3/22
 */
@Slf4j
public class ThreadStopDemo {

    public static void main(String[] args) {
        StopRunnable sr = new StopRunnable();
        new Thread(sr).start();
        sr.tellToStop();
    }

    /**
     * 15:56:01.113 [Thread-0] INFO cn.az.code.thread.ThreadStopDemo - thread is running 1
     * 15:56:01.118 [Thread-0] INFO cn.az.code.thread.ThreadStopDemo - thread is finished 1
     * 15:56:01.118 [Thread-0] INFO cn.az.code.thread.ThreadStopDemo - check status of stop flag: true
     * 15:56:01.118 [Thread-0] INFO cn.az.code.thread.ThreadStopDemo - thread stop
     */
    static class StopRunnable implements Runnable {

        volatile boolean stop;

        void tellToStop() {
            stop = true;
        }

        @Override
        public void run() {
            log.info("thread is running " + 1);
            doSomething(5);
            log.info("thread is finished " + 1);

            log.info("check status of stop flag: " + stop);

            if (stop) {
                log.info("thread stop");
                return;
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
