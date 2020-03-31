package cn.az.code.thread;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.Log;

import java.util.concurrent.Phaser;

import static cn.az.code.thread.ThreadTravel.doingLongTime;

/**
 * 某个线程到达预设点后，可以选择等待同伴或自己退出，等大家都到达后，再一起向下一个预设点出发，随时都可以有新的线程加入，退出的也可以再次加入。
 *
 * @author az
 * @date 2020/3/22
 */
public class ThreadGame {

    private static Log log = Log.get();

    static final int COUNT = 6;

    public static void main(String[] args) throws Exception {
        new Thread(new Challenger("张三")).start();
        new Thread(new Challenger("李四")).start();
        new Thread(new Challenger("王五")).start();
        new Thread(new Challenger("赵六")).start();
        new Thread(new Challenger("大胖")).start();
        new Thread(new Challenger("小白")).start();
        synchronized (ThreadGame.class) {
            ThreadGame.class.wait();
        }
    }

    static Phaser ph = new Phaser() {

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            log.info("第({})局，剩余[{}]人", phase, registeredParties);
            return registeredParties == 0 ||
                    (phase != 0 && registeredParties == COUNT);
        }

        ;
    };

    static class Challenger implements Runnable {

        String name;
        int state;

        Challenger(String name) {
            this.name = name;
            this.state = 0;
        }

        @Override
        public void run() {
            log.info("[{}]开始挑战。。。", name);
            ph.register();
            int phase = 0;
            int h;
            while (!ph.isTerminated() && phase < 100) {
                try {
                    doingLongTime();
                } catch (Exception e) {
                    log.warn(e);
                }
                if (state == 0) {
                    if (Decide.goon()) {
                        h = ph.arriveAndAwaitAdvance();
                        if (h < 0) {
                            log.info("No{}.[{}]继续，但已胜利。。。", phase, name);
                        } else {
                            log.info("No{}.[{}]继续at({})。。。", phase, name, h);
                        }
                    } else {
                        state = -1;
                        h = ph.arriveAndDeregister();
                        log.info("No{}.[{}]退出at({})。。。", phase, name, h);
                    }
                } else {
                    if (Decide.revive()) {
                        state = 0;
                        h = ph.register();
                        if (h < 0) {
                            log.info("No{}.[{}]复活，但已失败。。。", phase, name);
                        } else {
                            log.info("No{}.[{}]复活at({})。。。", phase, name, h);
                        }
                    } else {
                        log.info("No{}.[{}]没有复活。。。", phase, name);
                    }
                }
                phase++;
            }
            if (state == 0) {
                ph.arriveAndDeregister();
            }
            log.info("[{}]结束。。。", name);
        }

    }

    static class Decide {

        static boolean goon() {
            return RandomUtil.randomInt(1, 10) > 5;
        }

        static boolean revive() {
            return RandomUtil.randomInt(1, 10) < 5;
        }
    }
}
