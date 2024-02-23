package cn.az.code.config;

import feign.Client;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.cloud.openfeign.loadbalancer.LoadBalancerFeignRequestTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomFeignConfig extends FeignClientsConfiguration {

    @Bean
    Client lbClient(LoadBalancerClient loadBalancerClient,
                    LoadBalancerClientFactory loadBalancerClientFactory,
                    List<LoadBalancerFeignRequestTransformer> transformers) {
        Client dc = new Client.Default(null, null);
        return new FeignBlockingLoadBalancerClient(dc, loadBalancerClient, loadBalancerClientFactory, transformers);
    }

}
