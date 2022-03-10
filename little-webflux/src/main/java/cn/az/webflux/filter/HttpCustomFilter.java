package cn.az.webflux.filter;

import cn.az.webflux.service.CommonService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * HttpCustomFilter
 *
 * @author az
 * @since 2021/8/2 22:24
 */
public class HttpCustomFilter implements WebFilter {

    private final CommonService commonService;

    public HttpCustomFilter(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    @NonNull
    public Mono<Void> filter(ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String key = "Content-Type";
        HttpHeaders httpHeaders = request.getHeaders();
        if (httpHeaders.containsKey(key)) {
            commonService.LOCAL_STRING.set(Objects.requireNonNull(httpHeaders.get(key)).toString());
        }
        return chain.filter(exchange);
    }
}
