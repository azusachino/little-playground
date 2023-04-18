package cn.az.code.lock;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.IdUtil;
import cn.hutool.log.Log;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import jakarta.annotation.Resource;

/**
 * @author az
 * @since 2020-04-14
 */
public class RedisLock {

    private static final Log log = Log.get();

    @Resource
    private StatefulRedisConnection<String, String> redisConnection;

    private static final String LOCK_KEY = "az";
    private static final String LOCK_OK = "OK";
    private static final Long RELEASE_OK = 1L;

    public String acquire() {
        long timeout = 1000;
        long endTime = System.currentTimeMillis() + timeout;

        try {
            String token = IdUtil.fastSimpleUUID();
            while (System.currentTimeMillis() <= endTime) {
                RedisFuture<String> rf = this.redisConnection.async().set(LOCK_KEY, token,
                        SetArgs.Builder.nx().px(timeout));
                if (rf.get(10, TimeUnit.SECONDS).equals(LOCK_OK)) {
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

        try {
            RedisFuture<Object> rf = this.redisConnection.async().eval(script,
                    ScriptOutputType.INTEGER, LOCK_KEY, identity);

            if (RELEASE_OK.equals(rf.get(10, TimeUnit.SECONDS))) {
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
