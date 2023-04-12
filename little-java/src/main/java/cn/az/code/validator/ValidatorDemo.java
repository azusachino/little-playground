package cn.az.code.validator;

import cn.az.code.config.ValidatorConfig;
import cn.az.code.test.SimpleTest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author az
 * @since 08/31/20
 */
@Configuration
@ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
public class ValidatorDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ValidatorDemo.class);
        applicationContext.register(ValidatorConfig.class);

        // as the application started
        applicationContext.refresh();

        Validator validator = applicationContext.getBean(Validator.class);

        Demo demo = applicationContext.getBean(Demo.class);
        Set<ConstraintViolation<Demo>> set = validator.validate(demo);
        set.forEach(x -> System.out.println(x.getMessage()));
        applicationContext.close();
    }

    @Bean
    public Demo logDemo() {
        Demo demo = new Demo();
        demo.setDate("2021-03-03");
        demo.setTime("12:30:30");
        demo.setDateTime("sadhsakhd");
        return demo;
    }

    public void demo() throws NoSuchMethodException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ValidatorDemo.class);
        applicationContext.register(ValidatorConfig.class);

        // as the application started
        applicationContext.refresh();

        Validator validator = applicationContext.getBean(Validator.class);

        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        SimpleTest simpleTest = new SimpleTest();
        Method method = simpleTest.getClass().getMethod("say");

        Set<ConstraintViolation<SimpleTest>> constraintViolationSet = executableValidator.validateParameters(
                simpleTest,
                method,
                new Object[] {});
        constraintViolationSet.parallelStream().forEach(System.out::println);

        applicationContext.close();
    }

    public void demoFunc() {
        DateFormatValidation dfv = Demo.class.getAnnotation(DateFormatValidation.class);
        System.out.println(dfv.message());
    }
}
