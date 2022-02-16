package cn.az.webflux.handler;

import org.springframework.context.annotation.Bean;
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

    public Mono<String> hello() {
        return Mono.just("hello");
    }

    public Mono<String> goodbye(String toUserId) {
        return Mono.just(String.format("Goodbye %s", toUserId));
    }
}
