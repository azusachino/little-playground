package cn.az.code.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 解决Job中注入Spring Bean为null的问题
 *
 * @author ycpang
 * @since 2021-01-26 14:05
 */
@Component
public class QuartzJobFactory extends AdaptableJobFactory {

    @Resource
    private AutowireCapableBeanFactory capableBeanFactory;

    @NonNull
    @Override
    protected Object createJobInstance(@NonNull TriggerFiredBundle bundle) throws Exception {

        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}