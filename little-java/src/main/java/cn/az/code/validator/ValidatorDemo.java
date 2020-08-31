package cn.az.code.validator;

import cn.az.code.config.ValidatorConfig;
import cn.az.code.test.SimpleTest;
import com.google.common.base.Strings;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * @author az
 * @since 08/31/20
 */
public class ValidatorDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(ValidatorDemo.class);
        applicationContext.register(ValidatorConfig.class);

        applicationContext.refresh();

        Validator validator = applicationContext.getBean(Validator.class);

        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        SimpleTest simpleTest = new SimpleTest();
        Method method = simpleTest.getClass().getMethod("say");

        Set<ConstraintViolation<SimpleTest>> constraintViolationSet = executableValidator.validateParameters(
                simpleTest,
                method,
                new Object[]{}
        );
        constraintViolationSet.parallelStream().forEach(System.out::println);

        applicationContext.close();
    }
}
