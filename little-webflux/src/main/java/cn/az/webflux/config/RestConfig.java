package cn.az.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ReactorNettyClientRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import okhttp3.OkHttpClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

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

    public OkHttpClient client() {
        final OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        var connectionSpec = new okhttp3.ConnectionSpec.Builder(okhttp3.ConnectionSpec.MODERN_TLS)
                .allEnabledTlsVersions()
                .allEnabledCipherSuites()
                .build();
        // clientBuilder.
        clientBuilder.connectTimeout(1, TimeUnit.SECONDS);
        clientBuilder.readTimeout(2, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(3, TimeUnit.SECONDS);
        clientBuilder.retryOnConnectionFailure(false);
        clientBuilder.protocols(Arrays.asList(okhttp3.Protocol.HTTP_2, okhttp3.Protocol.HTTP_1_1));
        clientBuilder.connectionSpecs(Collections.singletonList(connectionSpec));

        return clientBuilder.build();
    }
}
