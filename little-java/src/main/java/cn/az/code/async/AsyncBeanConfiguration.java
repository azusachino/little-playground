package cn.az.code.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AsyncBeanConfiguration {

    // ensure all async-bean ready, relies on ContextRefreshedEvent
    @Bean
    AsyncTaskExecutionListener asyncTaskExecutionListener() {
        return new AsyncTaskExecutionListener();
    }
}
