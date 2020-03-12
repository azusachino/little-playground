package cn.az.code.concurrent;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * CountDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see CountDemo
 * @since 2020-03-12
 */
@Slf4j
public class CountDemo {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) {
        ExecutorService service = ThreadUtil.newExecutor();

        final Semaphore semaphore = new Semaphore(threadTotal);

        for (int i = 0; i < clientTotal; i++) {
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
            });

        }
        service.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count++;
    }
}
