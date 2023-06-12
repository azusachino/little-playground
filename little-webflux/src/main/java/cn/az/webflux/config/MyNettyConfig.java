package cn.az.webflux.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;

import reactor.core.publisher.Mono;
import reactor.netty.ConnectionObserver;
import reactor.netty.http.server.HttpServer;

/**
 * Mapping function that can be used to customize a Reactor Netty server instance.
 *
 * @author az
 * @since 2022-02-20 10:08
 */
public class MyNettyConfig implements NettyServerCustomizer, InitializingBean {

    @Override
    public HttpServer apply(HttpServer httpServer) {
        // 最新版本使用http.childObserve(ConnectionObserver)
        return httpServer.childObserve(
            ((connection, newState) -> {
                if (ConnectionObserver.State.CONNECTED.equals(newState)) {
                    connection.addHandlerLast(new MyChannelInitializer());
                }
            })
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Mono.just("hello").subscribe();
        
    }

    
}
