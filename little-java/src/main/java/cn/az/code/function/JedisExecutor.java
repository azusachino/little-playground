package cn.az.code.function;

import org.springframework.data.redis.RedisConnectionFailureException;

/**
 * @author az
 * @since 2020-04-14
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {

    R execute(T t) throws RedisConnectionFailureException;
}
