package cn.az.code.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author az
 * @since 2020-04-01
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(
                5, new ThreadFactoryBuilder().setNameFormat("banana-%d").build());

        Stream.iterate(1, i -> i + 1).limit(5).forEach(i -> {
            Future<?> future = ses.scheduleAtFixedRate(ScheduledThreadPoolExecutorDemo::action, 2, i, TimeUnit.SECONDS);
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        ses.shutdown();
        ses.close();
    }


    private static void action() {
        Thread currentThread = Thread.currentThread();
        if (!currentThread.isInterrupted()) {
            String name = currentThread.getName();
            System.out.printf("Thread [%s] is running\n", name);
        }
    }
}
