package cn.az.code.dubbo.handler;

import cn.az.code.dubbo.protocol.Message;
import cn.az.code.dubbo.utils.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * {@link SimpleChannelInboundHandler#channelRead(ChannelHandlerContext, Object)}
 * auto release bytebuf
 */
public class RequestMessageHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        System.out.println("receive message: " + JsonUtil.toJson(msg));
    }

}
