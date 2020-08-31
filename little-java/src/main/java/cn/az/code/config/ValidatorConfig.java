package cn.az.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author az
 * @since 08/31/20
 */
@Configuration
public class ValidatorConfig {

    @Bean
    @Primary
    public Validator validator() {
        return Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();
    }
}
