package cn.az.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author az
 * @since 2020-04-14
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

//    @Value("${spring.redis.sentinel.master}")
//    private String masterName;
//
//    @Value("${spring.redis.sentinel.master}")
//    private Set<String> sentinels;

    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(host, port);
    }

//    @Bean
//    public JedisSentinelPool jedisSentinelPool() {
//        return new JedisSentinelPool(masterName, sentinels);
//    }
}
