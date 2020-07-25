package cn.az.code.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author az
 * @date 2020/3/28
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    SpringUtil() {
        throw new Error("Invalid Operation");
    }

    private static ApplicationContext context;

    public static Optional<Object> getBean(String name) {
        return Optional.of(context.getBean(name));
    }

    public static <T> Optional<T> getBean(Class<T> clazz) {
        return Optional.of(context.getBean(clazz));
    }

    public static <T> Optional<T> getBean(String name, Class<T> requiredType) {
        return Optional.of(context.getBean(name, requiredType));
    }

    public static Class<?> getType(String name) {
        return context.getType(name);
    }

    public static boolean containsBean(String name) {
        return context.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return context.isSingleton(name);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = applicationContext;
    }
}
