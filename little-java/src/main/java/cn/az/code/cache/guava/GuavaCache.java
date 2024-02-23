package cn.az.code.cache.guava;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import cn.az.code.util.LogUtil;

/**
 * @author az
 * @since 09/02/20
 */
public class GuavaCache {

    private static final LoadingCache<String, Object> LOADING_CACHE = CacheBuilder
            .newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .recordStats()
            .weakKeys()
            .ticker(Ticker.systemTicker())
            .build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) {
                    switch (key) {
                        case "1":
                            return "new data of 1";
                        case "2":
                            return "new data of 2";
                        default:
                            return null;
                    }
                }
            });

    public static void main(String[] args) throws Exception {
        put("1", 1);
        put("2", 1);
        LogUtil.info("data: {}", viewCache());
        Thread.sleep(1001);
        LogUtil.info("data: {}", viewCache());
        String myFirst = LOADING_CACHE.get("1").toString();
        LogUtil.info("1 is: {}", myFirst);
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
