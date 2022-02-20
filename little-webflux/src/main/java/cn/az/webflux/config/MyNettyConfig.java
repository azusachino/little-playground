package cn.az.webflux.config;

import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import reactor.netty.http.server.HttpServer;

/**
 * Mapping function that can be used to customize a Reactor Netty server instance.
 *
 * @author az
 * @since 2022-02-20 10:08
 */
public class MyNettyConfig implements NettyServerCustomizer {

    @Override
    public HttpServer apply(HttpServer httpServer) {
        // 最新版本使用http.childObserve(ConnectionObserver)
        return httpServer.tcpConfiguration(
            tcpServer -> {
                tcpServer.configure()
                    .childHandler(new MyChannelInitializer());
                return tcpServer;
            }
        );
    }
}
