package cn.az.code.concurrent;

/**
 * @author Liz
 * @date 1/14/2020
 */
public class Singleton {

    private volatile static Singleton instance = null;

    private Singleton() {

    }

    /**
     * 双检查锁 单例模式
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
