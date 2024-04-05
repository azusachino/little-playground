package cn.az.code.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MybatisCustomInterceptor implements Interceptor, ApplicationContextAware {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        throw new UnsupportedOperationException("Unimplemented method 'intercept'");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        throw new UnsupportedOperationException("Unimplemented method 'setApplicationContext'");
    }
    
}
