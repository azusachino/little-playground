package cn.az.code.limit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器
 * <b>控制单位时间内的请求数量</b>
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see AtomicInteger
 * @since 2020-03-12
 */
public class Counter {
    /**
     * 最大访问数量
     */
    private final int limit = 10;
    /**
     * 访问时间差
     */
    private final long timeout = 1000;
    /**
     * 请求时间
     */
    private long time;
    /**
     * 当前计数器
     */
    private AtomicInteger reqCount = new AtomicInteger(0);

    public boolean limit() {
        long now = System.currentTimeMillis();
        if (now < time + timeout) {
            // 单位时间内
            reqCount.addAndGet(1);
            return reqCount.get() <= limit;
        } else {
            // 超出单位时间
            time = now;
            reqCount = new AtomicInteger(0);
            return true;
        }
    }

}
