package cn.az.code.thread;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;

import static cn.az.code.thread.ThreadTravel.doingLongTime;

/**
 * 两个线程在预设点交换变量，先到达的等待对方。
 *
 * @author az
 * @date 2020/3/22
 */
@Slf4j
public class ThreadWorking {

    static Exchanger<Tool> ex = new Exchanger<Tool>();

    @SneakyThrows
    public static void main(String[] args) {
        new Thread(new StaffRunnable("大胖", new Tool("笤帚", "扫地"), ex)).start();
        new Thread(new StaffRunnable("小白", new Tool("抹布", "擦桌"), ex)).start();

        synchronized (ThreadWorking.class) {
            ThreadWorking.class.wait();
        }
    }

    /**
     * 17:03:39.192 [Thread-0] INFO cn.az.code.thread.ThreadWorking - 大胖拿的工具是[笤帚]，他开始[扫地]。。。
     * 17:03:39.192 [Thread-1] INFO cn.az.code.thread.ThreadWorking - 小白拿的工具是[抹布]，他开始[擦桌]。。。
     * 17:03:44.204 [Thread-0] INFO cn.az.code.thread.ThreadWorking - 大胖开始交换工具。。。
     * 17:03:44.204 [Thread-1] INFO cn.az.code.thread.ThreadWorking - 小白开始交换工具。。。
     * 17:03:44.204 [Thread-1] INFO cn.az.code.thread.ThreadWorking - 小白的工具变为[笤帚]，他开始[扫地]。。。
     * 17:03:44.204 [Thread-0] INFO cn.az.code.thread.ThreadWorking - 大胖的工具变为[抹布]，他开始[擦桌]。。。
     */
    @AllArgsConstructor
    static class StaffRunnable implements Runnable {

        String name;
        Tool tool;
        Exchanger<Tool> ex;

        @Override
        public void run() {
            log.info("{}拿的工具是[{}]，他开始[{}]。。。", name, tool.name, tool.work);
            doingLongTime();
            log.info("{}开始交换工具。。。", name);
            try {
                tool = ex.exchange(tool);
            } catch (Exception e) {
                e.printStackTrace();
            }

            log.info("{}的工具变为[{}]，他开始[{}]。。。", name, tool.name, tool.work);

        }
    }

    @AllArgsConstructor
    static class Tool {
        String name;
        String work;
    }
}
