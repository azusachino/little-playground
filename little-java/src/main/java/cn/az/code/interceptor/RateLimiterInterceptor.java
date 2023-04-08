package cn.az.code.interceptor;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author az
 * @since 09/01/20
 */
@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(10);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!RATE_LIMITER.tryAcquire()) {

            throw new RuntimeException("已被限流");
        }

        // 令牌桶 -> 获取一个令牌
        RATE_LIMITER.acquire(1);
        return true;

    }
}
