package cn.az.webflux.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;

public class DbConfig {

    @Bean
    DruidDataSource dataSource() {
        return new DruidDataSource();
    }

}
