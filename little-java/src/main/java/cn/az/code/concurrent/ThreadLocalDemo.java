package cn.az.code.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author Liz
 * @date 2020/1/7
 */
@Slf4j
public class ThreadLocalDemo {

    public static ThreadLocal<Index> index = ThreadLocal.withInitial(Index::new);

    private static class Index {
        private int num;

        private void incr() {
            num++;
        }
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ThreadLocal-%s").build();
        ExecutorService service = Executors.newFixedThreadPool(10,threadFactory);
        for (int i = 0; i < 5; i++) {
            service.execute(threadFactory.newThread(() -> {
                Index local = index.get();
                local.incr();
                log.info(Thread.currentThread().getName() + ", num: {}",index.get().num);
            }));
        }
    }
}
