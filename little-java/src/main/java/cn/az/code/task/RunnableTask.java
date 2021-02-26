package cn.az.code.task;

import lombok.extern.slf4j.Slf4j;

/**
 * @author az
 * @since 02/25/21 22:57
 */
@Slf4j
public class RunnableTask implements Runnable {

    /**
     * When an object implementing interface <code>Runnable</code> is used to create a thread,
     * starting the thread causes the object's <code>run</code> method to be called in that separately
     * executing thread.
     *
     * <p>The general contract of the method <code>run</code> is that it may take any action
     * whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        log.info("doing runnable TASK...");
    }
}
