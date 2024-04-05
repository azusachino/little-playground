package cn.az.webflux.job;

import com.xxl.job.core.biz.model.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component
public class DefaultXxlJobs implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultXxlJobs.class);

    private final Scheduler scheduler;

    private final WebClient webClient;


    public DefaultXxlJobs(Scheduler scheduler,
                          @Qualifier("firstWebClient") WebClient webClient) {
        this.scheduler = scheduler;
        this.webClient = webClient;
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.webClient.get();
    }

    //    @Scheduled(fixedRate = 1000)
    public void scheduledJob() {
        var result = this.webClient.get()
            .uri("https://example.com")
            .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class))
            .block();
        LOGGER.info("example.com: {}", result);
    }

    //    @XxlJob("the-job")
    public ReturnT<String> theJob() {
        return Mono.just("https://example.com")
            .flatMap(x -> this.webClient.get()
                .uri(x)
                .exchangeToMono(cr -> cr.bodyToMono(String.class))
            )
            .subscribeOn(this.scheduler)
            .map(x -> ReturnT.SUCCESS)
            .block();
    }
}
