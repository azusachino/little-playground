package cn.az.code.mq.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author ycpang
 * @since 2021-03-12 10:33
 */
@Slf4j
public class SimpleProducer {

    private final String topic;
    private final DefaultMQProducer producer;

    public SimpleProducer(String topic) {
        this.producer = new DefaultMQProducer();
        this.producer.setNamesrvAddr(RocketConfig.NAME_SRV);

        try {
            this.producer.start();
        } catch (MQClientException e) {
            log.error("error ", e);
        }
        this.topic = topic;
    }

    public void send(Object o) {
        Message message = new Message(this.topic, o.toString().getBytes(StandardCharsets.UTF_8));
        try {
            this.producer.send(message);
        } catch (Exception e) {
            log.error("error ", e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.producer.shutdown();
    }
}
