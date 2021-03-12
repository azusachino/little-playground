package cn.az.code.mq.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author ycpang
 * @since 2021-03-12 10:33
 */
@Slf4j
public class SimpleConsumer {

    private final DefaultMQPushConsumer consumer;

    public SimpleConsumer(String topic) throws MQClientException {
        this.consumer = new DefaultMQPushConsumer();
        this.consumer.setNamesrvAddr(RocketConfig.NAME_SRV);
        this.consumer.subscribe(topic, "*");
        consumer.setMessageListener((MessageListenerConcurrently) (a, b) -> {
            for (MessageExt messageExt : a) {
                log.info("consuming {}", messageExt.getBody());
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
    }

    @Override
    protected void finalize() throws Throwable {
        this.consumer.shutdown();
    }
}
