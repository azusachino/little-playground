package cn.az.code.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @see org.springframework.cache.annotation.Cacheable
 * @author az
 * @since 09/01/20
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        // 可以使用 EhCache 或 Caffeine
        List<Cache> caches = new ArrayList<>();
        cacheManager.setCaches(caches);

        return cacheManager;
    }
}
