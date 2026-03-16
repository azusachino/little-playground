package cn.az.webflux;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.flush.FlushConsolidationHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * Netty TryOut
 *
 * @author ycpang
 * @since 2022-03-10 13:55
 */
class NettyTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyTests.class);

    @Test
    void serverTest() {

        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();


        var executor = new DefaultEventExecutor(new DefaultThreadFactory("traffic"));
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        // only process byteBuf
                        ch.pipeline().addLast("trafficShapingHandler", new GlobalTrafficShapingHandler(executor, 3));

                        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                        ch.pipeline().addLast(new HttpRequestDecoder());
                        ch.pipeline().addLast("flushHandler", new FlushConsolidationHandler(4, true));
                    }
                });
            ChannelFuture cf = bootstrap.bind(1234).sync();
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("err ", e);
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
        Assertions.assertNotNull(parentGroup);
    }

    @Test
    void clientTest() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                    }
                });
            ChannelFuture cf = bootstrap.connect("localhost", 1234).sync();
            cf.channel().writeAndFlush("Did I send the right message");
            cf.addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("client has connected to server!");

                    } else {
                        System.out.println("connect to serverfail!");
                    }
                }

            });
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("err ", e);
        } finally {
            group.shutdownGracefully();
        }

        Assertions.assertNotNull(group);
    }
}
