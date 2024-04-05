package cn.az.webflux.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class XxlJobConfig {

    @Bean
    XxlJobSpringExecutor xxlJobSpringExecutor() {
        return new XxlJobSpringExecutor();
    }

    @Bean
    Scheduler scheduler() {
        var threadPoolExecutor = Executors.newVirtualThreadPerTaskExecutor();
        return Schedulers.fromExecutorService(threadPoolExecutor);
    }
}
