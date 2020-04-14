package cn.az.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author az
 * @since 2020-04-14
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool();
    }
}
