package cn.az.webflux.config;

import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledGlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ConditionalOnEnabledGlobalFilter
public class DbConfig {

    @Bean
    DruidDataSource dataSource() {
        return new DruidDataSource();
    }

}
