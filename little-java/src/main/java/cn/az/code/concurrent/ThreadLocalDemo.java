package cn.az.code.concurrent;

import cn.hutool.log.Log;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author Liz
 */

public class ThreadLocalDemo {

    private static final Log log = Log.get();

    public static ThreadLocal<Index> index = ThreadLocal.withInitial(Index::new);

    private static class Index {
        private int num;

        private void incr() {
            num++;
        }
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ThreadLocal-%s").build();
        ExecutorService service = Executors.newFixedThreadPool(10, threadFactory);
        for (int i = 0; i < 5; i++) {
            service.execute(threadFactory.newThread(() -> {
                Index local = index.get();
                local.incr();
                log.info(Thread.currentThread().getName() + ", num: {}", index.get().num);
            }));
        }
    }
}
