package cn.az.webflux.handler;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

/**
 * Simple Handler
 *
 * @author az
 * @since 2022-02-16 20:55
 */
@Service
public class SimpleHandler {

    private final Object mutex = new Object();

    public Mono<String> hello() {
        // mechanism of singleton handler
        synchronized (this.mutex) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return Mono.just("hello");
    }

    public Mono<String> goodbye(String toUserId) {
        return Mono.just(String.format("Goodbye %s", toUserId));
    }
}
