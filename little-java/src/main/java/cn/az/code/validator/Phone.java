package cn.az.code.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author az
 * @since 08/31/20
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class) // 声明被什么所校验
public @interface Phone {

    String message() default "手机号校验失败";

    // 分组
    Class<?>[] groups() default {Default.class};

    // 约束注解的有效负载
    Class<? extends Payload>[] payload() default {};
}
