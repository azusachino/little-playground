package cn.az.code.lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Lock
 *
 * @author ycpang
 * @since 2021-10-26 19:28
 */
@Component
public class LockUtil {

    private static final String NODE_PATH = "/lock/%s";

    @Autowired
    private CuratorFramework curatorFramework;

    public InterProcessMutex tryLock(String key, int expireTime, TimeUnit timeUnit) {
        try {
            InterProcessMutex mutex = new InterProcessMutex(this.curatorFramework, String.format(NODE_PATH, key));
            boolean success = mutex.acquire(expireTime, timeUnit);
            if (success) {
                System.out.println("lock succeed");
                return mutex;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("lock failed");
        return null;
    }

    public void unLock(InterProcessMutex mutex) {
        try {
            mutex.release();
            System.out.println("unlock succeed");
        } catch (Exception e) {
            System.err.println("unlock failed");
        }
    }
}
