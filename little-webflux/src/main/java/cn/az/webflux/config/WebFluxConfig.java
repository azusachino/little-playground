package cn.az.webflux.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * WebFlux CORS跨域配置
 *
 * @author ycpang
 * @since 2021-05-17 20:31
 */
@Configuration
public class WebFluxConfig extends WebFluxConfigurationSupport {

    private static final String ALLOWED_HEADERS = "Origin, X-Requested-With, Content-Type, Accept";
    private static final String ALLOWED_METHODS = "POST, GET, PATCH, DELETE, PUT";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String MAX_AGE = "3600L";

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        // 1. LoadBalancerAutoConfiguration
        // 2. LoadBalancerInterceptor(LoadBalancerClient loadBalancerClient,
        // LoadBalancerRequestFactory requestFactory)
        // 3. InterceptingHttpAccessor.interceptors
        // 4. RestTemplate#doExecute() getRequestFactory ->
        // InterceptingClientHttpRequestFactory -> InterceptingClientHttpRequest
        // 5. InterceptingClientHttpRequest#executeInternal ->
        // LoadBalancerInterceptor#intercept -> this.loadBalancerClient.execute()
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    WebClient webClient() {
        // 1. LoadBalancerWebClientBuilderBeanPostProcessor
        // 2. ReactorLoadBalancerExchangeFilterFunction
        return WebClient.builder().build();
    }

    @Bean
    WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                headers.add("Access-Control-Max-Age", MAX_AGE);
                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                headers.add("Access-Control-Allow-Credentials", "true");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}
