package cn.az.code.concurrent;

import cn.hutool.cron.TaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: azusachino
 * @version: 2019/12/16
 */
public class ThreadTest {
    public static void main(String[] args) {
        CommonThreadFactory threadFactory = new CommonThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,100L, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10),threadFactory);

        for (int i = 0; i < 5; i++) {
            executor.execute(threadFactory.newThread(new ThreadTask()));
        }
    }
}
