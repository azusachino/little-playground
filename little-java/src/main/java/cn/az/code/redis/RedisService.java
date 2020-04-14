package cn.az.code.redis;

import cn.az.code.function.JedisExecutor;
import cn.hutool.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Optional;

/**
 * @author az
 * @date 2020/4/14
 */
@Component
public class RedisService {

    private static final Log log = Log.get();

    private final JedisPool jedisPool;

    private final JedisSentinelPool jedisSentinelPool;

    @Autowired
    public RedisService(JedisPool jedisPool, JedisSentinelPool jedisSentinelPool) {
        this.jedisPool = jedisPool;
        this.jedisSentinelPool = jedisSentinelPool;
    }

    public <T> Optional<T> execute(JedisExecutor<Jedis, T> j) {
        try (Jedis jedis = jedisPool.getResource()) {
            return Optional.of(j.execute(jedis));
        } catch (Exception e) {
            log.error(e);
        }
        return Optional.empty();
    }

    public <T> Optional<T> executeSentinel(JedisExecutor<Jedis, T> j) {
        try (Jedis jedis = jedisSentinelPool.getResource()) {
            return Optional.of(j.execute(jedis));
        } catch (Exception e) {
            log.error(e);
        }
        return Optional.empty();
    }
}
