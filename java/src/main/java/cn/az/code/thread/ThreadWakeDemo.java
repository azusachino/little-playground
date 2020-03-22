package cn.az.code.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程在sleep或wait时，是处于无法交互的状态的，此时只能使用interrupt方法中断它，线程会被激活并收到中断异常。
 *
 * @author az
 * @date 2020/3/22
 */
@Slf4j
public class ThreadWakeDemo {

    @SneakyThrows
    public static void main(String[] args) {
        WakeRunnable wr = new WakeRunnable();
        Thread t = new Thread(wr);
        t.start();
        Thread.sleep(2 * 1000L);
        t.interrupt();
    }

    /**
     * 16:42:42.094 [Thread-0] INFO cn.az.code.thread.ThreadWakeDemo - entering stop
     * 16:42:42.106 [Thread-0] ERROR cn.az.code.thread.ThreadWakeDemo - sleep interrupted
     * java.lang.InterruptedException: sleep interrupted
     * at java.lang.Thread.sleep(Native Method)
     * at cn.az.code.thread.ThreadWakeDemo$WakeRunnable.run(ThreadWakeDemo.java:31)
     * at java.lang.Thread.run(Thread.java:748)
     * 16:42:42.106 [Thread-0] WARN cn.az.code.thread.ThreadWakeDemo - interrupted
     * 16:42:42.106 [Thread-0] INFO cn.az.code.thread.ThreadWakeDemo - doing some funcs
     * 16:42:42.106 [Thread-0] INFO cn.az.code.thread.ThreadWakeDemo - continue or quit...
     */
    static class WakeRunnable implements Runnable {

        @Override
        public void run() {
            log.info("entering stop");

            try {
                Thread.sleep(5 * 1000L);
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage(), e);
                log.warn("interrupted");
                log.info("doing some funcs");
            }
            log.info("continue or quit...");
        }
    }

}
