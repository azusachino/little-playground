package cn.az.webflux.support;

import java.util.List;

import cn.az.webflux.model.IrisMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class IrisProtocolEncoder extends MessageToMessageEncoder<IrisMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, IrisMessage msg, List<Object> out) throws Exception {
        out.add(msg.toString());
    }

}
