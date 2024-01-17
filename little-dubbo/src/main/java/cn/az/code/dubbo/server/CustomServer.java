package cn.az.code.dubbo.server;

import cn.az.code.dubbo.codec.FrameDecoder;
import cn.az.code.dubbo.codec.MessageProtocolDecoder;
import cn.az.code.dubbo.handler.RequestMessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class CustomServer {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup());

        bootstrap.option(ChannelOption.SO_LINGER, 2);

        bootstrap.childOption(ChannelOption.SO_REUSEADDR, true);

        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {

            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                var pipeline = ch.pipeline();
                pipeline.addLast(new FrameDecoder());
                pipeline.addLast(new MessageProtocolDecoder());

                pipeline.addLast(new RequestMessageHandler());
            }

        });

    }
}
