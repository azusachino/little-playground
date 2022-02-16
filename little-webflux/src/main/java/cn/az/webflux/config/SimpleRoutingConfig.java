package cn.az.webflux.config;

import cn.az.webflux.handler.SimpleHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * SimpleConfig
 *
 * @author az
 * @since 2022-02-16 20:56
 */
@Configuration
public class SimpleRoutingConfig {

    private final SimpleHandler simpleHandler;

    public SimpleRoutingConfig(SimpleHandler simpleHandler) {
        this.simpleHandler = simpleHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> simpleRouting() {
        return RouterFunctions.route(
            RequestPredicates.GET("/hello"),
            request -> ServerResponse.ok().body(this.simpleHandler.hello(), String.class));
    }
}
