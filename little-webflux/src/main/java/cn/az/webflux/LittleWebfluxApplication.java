package cn.az.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * WebFlux Application
 *
 * @author az
 * @since 2021/8/2
 */
@EnableWebFlux
@SpringBootApplication
public class LittleWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleWebfluxApplication.class);
    }
}
