package cn.az.code.design;

/**
 * @author az
 * @date 2020-03-04
 * <p>
 * 优点：<b>饿汉模式</b>天生是线程安全的，使用时没有延迟。
 * 缺点：启动时即创建实例，启动慢，有可能造成资源浪费。
 */
public class Singleton4 {

    private static Singleton4 instance = new Singleton4();

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return instance;
    }
}
