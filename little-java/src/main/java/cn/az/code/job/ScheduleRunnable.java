package cn.az.code.job;

import cn.az.code.util.SpringUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * @author ycpang
 * @since 2021-01-26 14:10
 */
public class ScheduleRunnable implements Callable<Object> {

    private final Object target;
    private final Method method;
    private final String params;

    ScheduleRunnable(String beanName, String methodName, String params)
            throws NoSuchMethodException, SecurityException {
        this.target = SpringUtil.getBean(beanName);
        this.params = params;

        if (StrUtil.isNotBlank(params)) {
            this.method = target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public Object call() throws Exception {
        ReflectionUtils.makeAccessible(method);
        if (StrUtil.isNotBlank(params)) {
            method.invoke(target, params);
        } else {
            method.invoke(target);
        }
        return null;
    }
}