package cn.az.code.lock;

import cn.az.code.function.JedisExecutor;
import cn.hutool.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author az
 * @since 2020-04-14
 */
@Component
public class RedisLock {

    private static final Log log = Log.get();

    final JedisPool jedisPool;

    @Autowired
    public RedisLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private static final int DEFAULT_SINGLE_EXPIRE_TIME = 3;

    private static final int DEFAULT_BATCH_EXPIRE_TIME = 6;

    public <T> T execute(JedisExecutor<Jedis, T> j) throws Exception {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.execute(jedis);
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
