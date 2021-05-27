package cn.az.code.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

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

    // by using ConstraintValidator, the annotation must have groups and payload, probably

    // 分组
    Class<?>[] groups() default {};

    // 约束注解的有效负载
    Class<? extends Payload>[] payload() default {};
}
