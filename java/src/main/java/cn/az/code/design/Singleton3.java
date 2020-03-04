package cn.az.code.design;

/**
 * @author az
 * @date 2020-03-04
 *
 * 优点：懒加载，线程安全。
 * 注：实例必须有 volatile 关键字修饰，其保证初始化完全。
 */
public class Singleton3 {

    private volatile static Singleton3 instance = null;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
