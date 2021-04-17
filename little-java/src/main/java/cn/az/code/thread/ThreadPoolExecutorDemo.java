package cn.az.code.thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

/**
 * @author az
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ExecutorService service = ThreadUtil.newExecutor(20);
        Set<Thread> set = new HashSet<>();

        setThreadFactory(service, set);

        for (int i = 0; i < 9; i++) {
            service.submit(() -> {
            });
        }

        set.stream().filter(Thread::isAlive)
                .forEach(t -> {
                    System.out.printf("current thread [%s] is running...\n", t.getName());
                });

        // main thread group is always the parent

        Thread mainThread = Thread.currentThread();
        ThreadGroup threadGroup = mainThread.getThreadGroup();

        int count = threadGroup.activeCount();
        Thread[] threads = new Thread[count];
        threadGroup.enumerate(threads);

        Stream.of(threads)
                .filter(Thread::isAlive)
                .forEach(t -> {
                    System.out.printf("main's subThread [%s] is running...\n", t.getName());
                });

        service.shutdown();
    }

    private static void setThreadFactory(ExecutorService service, Set<Thread> threads) {
        if (service instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
            ThreadFactory oldFactory = executor.getThreadFactory();
            executor.setThreadFactory(new DelegateThreadFactory(oldFactory, threads));
        }
    }

    private static class DelegateThreadFactory implements ThreadFactory {

        private final ThreadFactory threadFactory;

        private final Set<Thread> threads;

        private DelegateThreadFactory(ThreadFactory threadFactory, Set<Thread> threads) {
            this.threadFactory = threadFactory;
            this.threads = threads;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = threadFactory.newThread(r);
            threads.add(t);
            return t;
        }
    }
}
