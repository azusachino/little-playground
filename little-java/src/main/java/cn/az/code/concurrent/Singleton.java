package cn.az.code.concurrent;

/**
 * @author Liz
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
