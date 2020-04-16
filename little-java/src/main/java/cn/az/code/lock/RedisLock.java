package cn.az.code.lock;

import cn.az.code.function.JedisExecutor;
import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;
import java.util.Objects;

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

    private static final String LOCK_KEY = "az";
    private static final String LOCK_OK = "OK";
    private static final Long RELEASE_OK = 1L;

    public <T> T execute(JedisExecutor<Jedis, T> j) throws Exception {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.execute(jedis);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public String acquire() {
        long timeout = 1000;
        long endTime = System.currentTimeMillis() + timeout;

        try {
            String token = IdUtil.fastSimpleUUID();
            while (System.currentTimeMillis() <= endTime) {
                SetParams setParams = SetParams.setParams().nx().px(timeout);
                String res = execute(j -> j.set(LOCK_KEY, token, setParams));
                if (StringUtils.equals(LOCK_OK, res)) {
                    return token;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    public boolean release(String identity) {
        if (Objects.isNull(identity)) {
            return false;
        }
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object res;

        try {
            res = execute(j -> j.eval(script, Collections.singletonList(LOCK_KEY), Collections.singletonList(identity)));
            if (RELEASE_OK.equals(res)) {
                log.info("release lock success, required token: {}", identity);
                return true;
            }
        } catch (Exception e) {
            log.error(e);
        }
        log.info("release lock failed");
        return false;
    }

}
