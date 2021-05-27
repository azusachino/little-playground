package cn.az.code.mq.rabbit;

import cn.az.code.util.LogUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author ycpang
 * @since 2021-03-13 11:34
 */
@Component
public class RabbitConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue", durable = "true"),
                    exchange = @Exchange(value = "exchanger", type = "topic"),
                    key = "rabbitKey"
            )
    )
    @RabbitHandler
    public void onMessage(@Payload Object o, @Headers Map<String, Object> headers, Channel channel) throws IOException {

        LogUtil.info(o.toString());

        /*
         * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
         * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
         * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
         */
        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        /*
         *  ACK，确认一条消息已经被消费
         *  multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        channel.basicAck(deliverTag, false);
    }
}
