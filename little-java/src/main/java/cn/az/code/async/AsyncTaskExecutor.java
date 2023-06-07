package cn.az.code.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AsyncTaskExecutor {

    protected static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    protected static final AtomicReference<ThreadPoolExecutor> THREAD_POOL_REF = new AtomicReference<ThreadPoolExecutor>();

    protected static final List<Future<?>> FUTURES = new ArrayList<>();

    public static Future<?> submitTask(Runnable runnable) {
        if (THREAD_POOL_REF.get() == null) {
            ThreadPoolExecutor threadPoolExecutor = createThreadPoolExecutor();
            boolean success = THREAD_POOL_REF.compareAndSet(null, threadPoolExecutor);
            if (!success) {
                threadPoolExecutor.shutdown();
            }
        }
        Future<?> future = THREAD_POOL_REF.get().submit(runnable);
        FUTURES.add(future);
        return future;
    }

    private static ThreadPoolExecutor createThreadPoolExecutor() {
        int threadPoolCoreSize = CPU_COUNT + 1;
        int threadPoolMaxSize = CPU_COUNT + 1;
        System.out.println(String.format(
                "create async-init-bean thread pool, corePoolSize: %d, maxPoolSize: %d.",
                threadPoolCoreSize, threadPoolMaxSize));
        return new ThreadPoolExecutor(threadPoolCoreSize, threadPoolMaxSize, 30,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static void ensureAsyncTasksFinish() {
        for (Future<?> future : FUTURES) {
            try {
                future.get();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        FUTURES.clear();
        if (THREAD_POOL_REF.get() != null) {
            THREAD_POOL_REF.get().shutdown();
            THREAD_POOL_REF.set(null);
        }
    }
}
