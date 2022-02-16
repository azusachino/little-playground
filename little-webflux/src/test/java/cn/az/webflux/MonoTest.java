package cn.az.webflux;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Test Mono
 *
 * @author az
 * @since 2021-08-26 22:15
 */
public class MonoTest {

    private static final ThreadPoolExecutor executor;

    static {
        int cpus = Runtime.getRuntime().availableProcessors();
        executor = new ThreadPoolExecutor(cpus, 1 << cpus, 1000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>());
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            search().subscribe();
        }
    }

    public static void m(Scheduler scheduler) {
        v().subscribeOn(scheduler)
            .doOnNext(v -> System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName()))
            .subscribe()
            .dispose();

    }

    public static Mono<Void> v() {
        return Mono.fromRunnable(() -> System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName()));
    }

    public static Mono<String> search() {
        return Mono.just("kkk")
            .doOnNext(data -> Mono.fromRunnable(MonoTest::doThisAsync)
                .subscribeOn(Schedulers.elastic())  // delegate to proper thread to not block main flow
                .subscribe()); // add error logging here or inside doThisAsync

    }

    public static void doThisAsync() {
        //do some blocking I/O on calling thread
        try {
            Thread.sleep(10);
            System.out.println(LocalDateTime.now() + " " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
