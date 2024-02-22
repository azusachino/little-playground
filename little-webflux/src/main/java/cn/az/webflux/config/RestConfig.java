package cn.az.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ReactorNettyClientRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class RestConfig {

    // async http client
    @Bean
    WebClient webClient() {
        var httpClient = HttpClient.create()
            .keepAlive(false)
            .disableRetry(true)
            .responseTimeout(Duration.ofSeconds(2))
            .headers(hb -> hb.set(HttpHeaders.CONNECTION, "close"));
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
    }

    // blocking http client
    @Bean
    RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        var template = new RestTemplate();
        template.setRequestFactory(clientHttpRequestFactory);
        return template;
    }

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory() {
        var httpClient = HttpClient.create()
            .keepAlive(false)
            .disableRetry(true)
            .responseTimeout(Duration.ofSeconds(2))
            .headers(hb -> hb.set(HttpHeaders.CONNECTION, "close"));
        return new ReactorNettyClientRequestFactory(httpClient);
    }
}
