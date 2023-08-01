package cn.az.code.dubbo.codec;

import java.nio.ByteOrder;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class FrameDecoder extends LengthFieldBasedFrameDecoder {

    public FrameDecoder() {
        super(ByteOrder.BIG_ENDIAN, Integer.MAX_VALUE, 0, 2, 0, 2, true);
    }

}
