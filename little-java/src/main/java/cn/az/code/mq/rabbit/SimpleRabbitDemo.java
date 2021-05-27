package cn.az.code.mq.rabbit;

import cn.az.code.util.LogUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author ycpang
 * @since 2021-03-12 10:38
 */
@Component
public class SimpleRabbitDemo {

    private final static String QUEUE_DEMO = "DEMO_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("180.76.169.35");
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_DEMO, false, false, true, null);
            String msg = "Hello MSG";

            channel.basicPublish("", QUEUE_DEMO, null, msg.getBytes(StandardCharsets.UTF_8));
            LogUtil.info("sent, {}" + msg);

        }
    }

}
