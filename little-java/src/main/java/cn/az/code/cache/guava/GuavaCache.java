package cn.az.code.cache.guava;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author az
 * @since 09/02/20
 */
public class GuavaCache {

    private static final LoadingCache<String, Object> LOADING_CACHE = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .recordStats()
            .weakKeys()
            .ticker(Ticker.systemTicker())
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) {
                    return null;
                }
            });

    public static void main(String[] args) {
        put("1", 1);
        put("2", 1);
        System.out.println(viewCache());
    }

    public static void put(String k, Object o) {
        LOADING_CACHE.put(k, o);
    }

    public static Map<?, ?> viewCache() {
        return LOADING_CACHE.asMap();
    }

    public static void remove(String k) {
        LOADING_CACHE.invalidate(k);
    }

}
