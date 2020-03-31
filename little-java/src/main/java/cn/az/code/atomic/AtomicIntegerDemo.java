package cn.az.code.atomic;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicIntegerDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see AtomicInteger
 * @since 2020-03-24
 */
public class AtomicIntegerDemo {

    private static Log log = Log.get();

    public static void main(String[] args) {
        AtomicInteger val = new AtomicInteger(999);
        ExecutorService service = ThreadUtil.newExecutor(10);

        for (int i = 0; i < 10; i++) {
            service.submit(() -> {
                AtomicIntegerDemo.action(val);
            });
        }

        service.shutdown();
    }

    private static void action(AtomicInteger val) {
        val.getAndIncrement();
        log.info("current val is {}, thread is {}", val.get(), Thread.currentThread().getName());
    }
}
