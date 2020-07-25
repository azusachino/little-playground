package cn.az.code.design;

/**
 * @author az
 * @date 2020-03-04
 * 懒汉模式
 * 优点：懒加载启动快，资源占用小，使用时才实例化，无锁。
 * 缺点：非线程安全。
 */
public class Singleton1 {

    private static Singleton1 singleton = null;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (singleton == null) {
            singleton = new Singleton1();
        }
        return singleton;
    }
}
