package cn.az.webflux.handler;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

/**
 * SimpleWebSocketHandler
 *
 * @author az
 * @since 2022-02-16 21:03
 */
@Service
public class SimpleWebSocketHandler implements WebSocketHandler {

    @Override
    @NonNull
    public Mono<Void> handle(@NonNull WebSocketSession session) {
        return Mono.empty();
    }
}
