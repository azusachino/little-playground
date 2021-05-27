package cn.az.code.config;

import cn.az.code.util.LogUtil;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Configuration;

/**
 * @author ycpang
 * @since 2021-03-13 11:38
 */
@EnableRabbit
@Configuration
public class RabbitmqConfig implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
        endpoint.setMessageListener(message -> {
            LogUtil.info(new String(message.getBody()));
        });
    }
}
