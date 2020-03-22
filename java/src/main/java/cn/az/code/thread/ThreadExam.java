package cn.az.code.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 每完成一个线程，计数器减1，当减到0时，被阻塞的线程自动执行。
 * @author az
 * @date 2020/3/22
 */
@Slf4j
public class ThreadExam {

    private static final int COUNT = 20;

    static CountDownLatch cdl = new CountDownLatch(COUNT);

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new TeacherRunnable(cdl)).start();
        Thread.sleep(1 * 1000L);
        for (int i = 0; i < COUNT; i++) {
            new Thread(new StudentRunnable(i, cdl)).start();
        }

        synchronized (ThreadExam.class) {
            ThreadExam.class.wait();
        }
    }

    static class TeacherRunnable implements Runnable {

        CountDownLatch cdl;

        TeacherRunnable(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {
            log.info("sending out papers...");
            try {
                this.cdl.wait();
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage(), e);
            }
            log.info("picking up papers...");
        }
    }

    static class StudentRunnable implements Runnable {

        CountDownLatch cdl;
        Integer num;

        StudentRunnable(int num, CountDownLatch cdl) {
            this.num = num;
            this.cdl = cdl;
        }

        @Override
        @SneakyThrows
        public void run() {
            log.info("{} is writing hard...", this.num);
            Thread.sleep(5 * 1000L);
            log.info("{} is finished the paper", this.num);
            cdl.countDown();
        }
    }
}
