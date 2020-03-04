package cn.az.code.design;

/**
 * @author az
 * @date 2020-03-04
 * 懒汉模式
 * 优点：同上，但加锁了。
 * 缺点：synchronized 为独占排他锁，并发性能差。即使在创建成功以后，获取实例仍然是串行化操作。
 */
public class Singleton2 {
    private static Singleton2 uniqueInstance = null;

    private Singleton2 () {

    }

    public static synchronized Singleton2 getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton2();
        }
        return uniqueInstance;
    }
}
