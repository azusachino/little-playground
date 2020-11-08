package cn.az.code.proxy;

import cn.az.code.proxy.service.UserService;
import cn.az.code.proxy.service.UserServiceImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * The type My cglib proxy.
 *
 * @author az
 * @since 2020 -04-09
 */
public class MyCglibProxy implements MethodInterceptor {

    private Object target;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        //实例化CglibProxy对象
        MyCglibProxy cglib = new MyCglibProxy();
        //获取代理对象
        UserService userService = (UserService) cglib.getCglibProxy(new UserServiceImpl());
        //执行删除方法
        userService.delUser("admin");
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        //方法执行，参数：target 目标对象 arr参数数组
        Object invoke = method.invoke(target, objects);
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }

    /**
     * 定义获取代理对象方法
     *
     * @param objectTarget the object target
     * @return the cglib proxy
     */

    public Object getCglibProxy(Object objectTarget) {
        // 为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        // 设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 设置回调
        enhancer.setCallback(this);
        // 创建并返回代理对象
        return enhancer.create();
    }
}
