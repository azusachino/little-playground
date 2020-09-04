package cn.az.code.config;

import cn.az.code.interceptor.RateLimiterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * @author az
 * @since 09/01/20
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Resource
    private RateLimiterInterceptor rateLimiterInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimiterInterceptor)
        .addPathPatterns("/api/**");
        super.addInterceptors(registry);
    }

    // 可以通过http访问附件

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置目录映射
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/xxx/xx");
        super.addResourceHandlers(registry);
    }
}
