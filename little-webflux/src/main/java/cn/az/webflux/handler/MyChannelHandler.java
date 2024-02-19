package cn.az.webflux.handler;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * Handler should handle the IdleStateEvent triggered by IdleStateHandler.
 *
 * @author az
 * @since 2022-02-20 10:05
 */
public class MyChannelHandler extends ChannelDuplexHandler {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        if (evt instanceof IdleStateEvent e) {
            if (e.state() == IdleState.READER_IDLE) {
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush("ping");
            }
        }
    }

    /**
     * 当通道有数据可读时执行
     *
     * @param ctx 上下文对象
     * @param msg 客户端发送的数据
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super duper slow operation, send it to eventloop
        final Object finalMsg = msg;
        // 通过 ctx.channel().eventLoop().schedule()
        // 将操作放入任务队列定时执行（5min 之后才进行处理）
        ctx.channel().eventLoop().schedule(() -> {
            ByteBuf byteBuf = (ByteBuf) finalMsg;
            System.out.println("data from client: "
                + byteBuf.toString(CharsetUtil.UTF_8));
        }, 5, TimeUnit.MINUTES);

        // 可以继续调用 ctx.channel().eventLoop().schedule()
        // 将更多操作放入队列
        System.out.println("return right now.");
    }

}
