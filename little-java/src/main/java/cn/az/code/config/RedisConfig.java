package cn.az.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @author az
 * @since 2020-04-14
 */
@Configuration
public class RedisConfig {

    @Bean
    StatefulRedisConnection<String, String> redisConnection() {
        return RedisClient.create().connect();
    }
}
