package cn.az.code.limit;

/**
 * 令牌桶
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see TokenBucket
 * @since 2020-03-12
 */
public class TokenBucket {
    /**
     * 时间
     */
    private long time;
    /**
     * 总量
     */
    private Double total;
    /**
     * token 放入速度
     */
    private Double rate;
    /**
     * 当前总量
     */
    private Double nowSize;


    public boolean limit() {
        long now = System.currentTimeMillis();
        nowSize = Math.min(total, nowSize + (now - time) * rate);
        time = now;
        if (nowSize < 1) {
            // 桶里没有token
            return false;
        } else {
            // 存在token
            nowSize -= 1;
            return true;
        }
    }

}
