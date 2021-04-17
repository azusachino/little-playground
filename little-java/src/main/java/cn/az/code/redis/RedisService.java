package cn.az.code.redis;

import cn.az.code.function.JedisExecutor;
import cn.hutool.log.Log;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author az
 */
@Component
public class RedisService {

    private static final Log log = Log.get();

    @Resource
    private JedisPool jedisPool;

//    @Resource
//    private JedisSentinelPool jedisSentinelPool;

    public <T> Optional<T> execute(JedisExecutor<Jedis, T> j) {
        try (Jedis jedis = jedisPool.getResource()) {
            return Optional.of(j.execute(jedis));
        } catch (Exception e) {
            log.error(e);
        }
        return Optional.empty();
    }

//    public <T> Optional<T> executeSentinel(JedisExecutor<Jedis, T> j) {
//        try (Jedis jedis = jedisSentinelPool.getResource()) {
//            return Optional.of(j.execute(jedis));
//        } catch (Exception e) {
//            log.error(e);
//        }
//        return Optional.empty();
//    }

}
