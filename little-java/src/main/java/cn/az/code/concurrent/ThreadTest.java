package cn.az.code.concurrent;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.common.util.concurrent.UncaughtExceptionHandlers;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author azusachino
 * @version 2019/12/16
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setPriority(5).setNameFormat("Thread-test-%d")
                .setUncaughtExceptionHandler(UncaughtExceptionHandlers.systemExit()).build();
        // ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 100L,
        // TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), threadFactory);
        ExecutorService service = Executors.newCachedThreadPool(threadFactory);

        Executor exe = MoreExecutors.directExecutor();

        for (int i = 0; i < 5; i++) {
            service.execute(threadFactory.newThread(new ThreadTask()));
            exe.execute(() -> System.out.println("watermelon"));
        }

        service.shutdown();
    }
}
