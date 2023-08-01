package cn.az.code.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * AtomicIntegerDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see AtomicInteger
 * @since 2020-03-24
 */
@Slf4j
public class AtomicIntegerDemo {

    public static void main(String[] args) {
        AtomicInteger val = new AtomicInteger(999);
        ExecutorService service = Executors.newFixedThreadPool(10);

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
