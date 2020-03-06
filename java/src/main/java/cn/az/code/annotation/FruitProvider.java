package cn.az.code.annotation;

import java.lang.annotation.*;

/**
 * @author az
 * @date 2020-03-06
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProvider {

    int id() default -1;

    String name() default "";

    String address() default "";
}
