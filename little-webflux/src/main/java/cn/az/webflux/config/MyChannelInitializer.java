package cn.az.webflux.config;

import cn.az.webflux.handler.MyChannelHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Triggers an IdleStateEvent when a Channel has not performed read, write, or both operation for a while.
 *
 * @author az
 * @since 2022-02-20 10:03
 */
public class MyChannelInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline().addLast("idleStateHandler", new IdleStateHandler(60, 30, 0));
        // after idelStateHandler, wait for IdleStateEvent
        channel.pipeline().addLast("myChannelHandler", new MyChannelHandler());
    }
}
