package cn.az.code.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

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
                // 采用HibernateValidator
                .byProvider(HibernateValidator.class)
                .configure()
                // 一次获取所有的错误信息
                .failFast(false)
                .buildValidatorFactory()
                .getValidator();
    }
}
