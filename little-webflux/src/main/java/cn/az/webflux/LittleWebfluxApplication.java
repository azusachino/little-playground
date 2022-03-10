package cn.az.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * WebFlux Application
 *
 * @author az
 * @since 2021/8/2
 */
@EnableWebFlux
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, MongoReactiveAutoConfiguration.class})
public class LittleWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleWebfluxApplication.class);
    }
}
