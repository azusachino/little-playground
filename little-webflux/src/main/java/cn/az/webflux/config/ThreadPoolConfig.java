package cn.az.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * TODO
 *
 * @author az
 * @since 2021-08-26 21:52
 */
public class ThreadPoolConfig implements AsyncConfigurer {

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        int cpus = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1 << cpus);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("custom-executor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}
