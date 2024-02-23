package cn.az.webflux;

import cn.az.webflux.currency.DelegateCurrencyRateHandler;
import cn.az.webflux.utils.JsonUtils;
import org.redisson.spring.starter.RedissonAutoConfigurationV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * WebFlux Application
 *
 * @author az
 * @since 2021/8/2
 */
@EnableWebFlux
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,
    MongoDataAutoConfiguration.class, MongoReactiveAutoConfiguration.class,
    RedisAutoConfiguration.class, RedissonAutoConfigurationV2.class})
public class LittleWebfluxApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LittleWebfluxApplication.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LittleWebfluxApplication.class);
        var delegate = ctx.getBean(DelegateCurrencyRateHandler.class);
        var result = delegate.getCurrencyRate();
        if (Boolean.TRUE.equals(result.getSecond())) {
            LOGGER.info("currency rate is {}", JsonUtils.toJson(result));
        }
    }

}
