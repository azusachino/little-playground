package cn.az.code.dubbo.protocol;

import com.google.common.base.Charsets;

import cn.az.code.dubbo.utils.JsonUtil;
import io.netty.buffer.ByteBuf;

/**
 * struct
 * length - version,opCode,streamId - body
 */
public abstract class Message<T extends MessageBody> {

    private MessageHeader header;
    private T body;

    public void encode(ByteBuf buf) {
        buf.writeInt(this.header.version());
        buf.writeInt(this.header.opCode());
        buf.writeLong(this.header.streamId());
        buf.writeBytes(JsonUtil.toJson(this.body).getBytes());
    }

    public abstract Class<T> getMessageBodyDecodeClass(int opcode);

    @SuppressWarnings("unchecked")
    public void decode(ByteBuf buf) {
        int version = buf.readInt();
        int opCode = buf.readInt();
        long streamId = buf.readLong();

        this.header = new MessageHeader(version, opCode, streamId);

        Class<?> clazz = this.getMessageBodyDecodeClass(opCode);
        var body = (T) JsonUtil.fromJson(buf.toString(Charsets.UTF_8), clazz);
        this.body = body;
    }

}
