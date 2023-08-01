package cn.az.code.thread;

import java.util.concurrent.CyclicBarrier;

import lombok.extern.slf4j.Slf4j;

/**
 * 某个线程到达预设点时就在此等待，等所有的线程都到达时，大家再一起向下个预设点出发。如此循环反复下去。
 *
 * @author az
 */
@Slf4j
public class ThreadTravel {

    static final int COUNT = 5;
    static CyclicBarrier cb = new CyclicBarrier(COUNT, new SingerRunnable());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < COUNT; i++) {
            new Thread(new StaffRunnable(i, cb)).start();
        }
        synchronized (ThreadTravel.class) {
            ThreadTravel.class.wait();
        }
    }

    /**
     * 16:57:51.523 [Thread-2] INFO cn.az.code.thread.ThreadTravel - 员工2出发。。。
     * 16:57:51.522 [Thread-4] INFO cn.az.code.thread.ThreadTravel - 员工4出发。。。
     * 16:57:51.523 [Thread-1] INFO cn.az.code.thread.ThreadTravel - 员工1出发。。。
     * 16:57:51.522 [Thread-0] INFO cn.az.code.thread.ThreadTravel - 员工0出发。。。
     * 16:57:51.522 [Thread-3] INFO cn.az.code.thread.ThreadTravel - 员工3出发。。。
     * 16:57:56.535 [Thread-4] INFO cn.az.code.thread.ThreadTravel - 员工4到达地点一。。。
     * 16:57:56.535 [Thread-2] INFO cn.az.code.thread.ThreadTravel - 员工2到达地点一。。。
     * 16:57:56.535 [Thread-0] INFO cn.az.code.thread.ThreadTravel - 员工0到达地点一。。。
     * 16:57:56.535 [Thread-3] INFO cn.az.code.thread.ThreadTravel - 员工3到达地点一。。。
     * 16:57:56.535 [Thread-1] INFO cn.az.code.thread.ThreadTravel - 员工1到达地点一。。。
     * 16:57:56.535 [Thread-1] INFO cn.az.code.thread.ThreadTravel - Thread-1 is
     * singing for everyone
     * 16:57:56.536 [Thread-0] INFO cn.az.code.thread.ThreadTravel - 员工0再出发。。。
     * 16:57:56.536 [Thread-1] INFO cn.az.code.thread.ThreadTravel - 员工1再出发。。。
     * 16:57:56.536 [Thread-3] INFO cn.az.code.thread.ThreadTravel - 员工3再出发。。。
     * 16:57:56.536 [Thread-2] INFO cn.az.code.thread.ThreadTravel - 员工2再出发。。。
     * 16:57:56.536 [Thread-4] INFO cn.az.code.thread.ThreadTravel - 员工4再出发。。。
     */
    static class SingerRunnable implements Runnable {

        @Override
        public void run() {
            log.info("{} is singing for everyone", Thread.currentThread().getName());
        }
    }

    static class StaffRunnable implements Runnable {

        CyclicBarrier cb;
        int num;

        StaffRunnable(int num, CyclicBarrier cb) {
            this.num = num;
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                log.info("员工{}出发。。。", num);
                doingLongTime();
                log.info("员工{}到达地点一。。。", num);
                try {
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("员工{}再出发。。。", num);
                doingLongTime();
                log.info("员工{}到达地点二。。。", num);
                try {
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("员工{}再出发。。。", num);
                doingLongTime();
                log.info("员工{}到达地点三。。。", num);
                try {
                    cb.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.info("员工{}结束。。。", num);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static void doingLongTime() throws InterruptedException {
        Thread.sleep(5 * 1000L);
    }
}
