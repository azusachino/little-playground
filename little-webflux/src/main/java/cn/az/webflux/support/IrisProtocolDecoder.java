package cn.az.webflux.support;

import java.util.List;

import cn.az.webflux.model.IrisMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class IrisProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        // first int is length, next byte is type, last bytes is data
        int length = buf.readInt();
        byte type = buf.readByte();
        byte[] data = new byte[length - 1];
        buf.readBytes(data);
        IrisMessage message = new IrisMessage();
        message.setType(type);
        message.setData(new String(data));
        out.add(message);

    }

}
