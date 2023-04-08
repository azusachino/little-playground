package cn.az.webflux.filter;

import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import cn.az.webflux.service.CommonService;
import reactor.core.publisher.Mono;

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
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String key = "Content-Type";
        HttpHeaders httpHeaders = request.getHeaders();
        if (httpHeaders.containsKey(key)) {
            commonService.LOCAL_STRING.set(Objects.requireNonNull(httpHeaders.get(key)).toString());
        }
        return chain.filter(exchange);
    }
}
