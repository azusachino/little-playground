package cn.az.webflux.config;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import cn.az.webflux.handler.SimpleWebSocketHandler;

/**
 * CustomRoutingConfiguration
 *
 * @author az
 * @since 2021-08-05 22:41
 */
@Configuration
public class CustomRoutingConfiguration {

    private final SimpleWebSocketHandler webSocketHandler;

    public CustomRoutingConfiguration(SimpleWebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    @Bean
    HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = Map.of("/ws", this.webSocketHandler);
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setUrlMap(map);
        handlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return handlerMapping;
    }

}
