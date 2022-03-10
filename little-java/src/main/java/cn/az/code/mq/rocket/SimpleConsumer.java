package cn.az.code.mq.rocket;

import cn.az.code.util.LogUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Arrays;

/**
 * @author ycpang
 * @since 2021-03-12 10:33
 */
public class SimpleConsumer {

    private final DefaultMQPushConsumer consumer;

    public SimpleConsumer(String topic) throws MQClientException {
        this.consumer = new DefaultMQPushConsumer();
        this.consumer.setNamesrvAddr(RocketConfig.NAME_SRV);
        this.consumer.subscribe(topic, "*");
        consumer.setMessageListener((MessageListenerConcurrently) (a, b) -> {
            for (MessageExt messageExt : a) {
                LogUtil.info("consuming {}" + Arrays.toString(messageExt.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        consumer.start();
    }

    @Override
    protected void finalize() {
        this.consumer.shutdown();
    }
}
