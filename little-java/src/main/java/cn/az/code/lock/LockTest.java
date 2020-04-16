package cn.az.code.lock;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.log.Log;

import java.util.concurrent.ExecutorService;

/**
 * Zookeeper实现分布式锁
 *
 * @author az
 * @date 2020/4/16
 */
public class LockTest {

    private static final Log log = Log.get();

    public static void main(String[] args) {
        int maxThread = 4;
        ExecutorService service = ThreadUtil.newExecutor(10);
        for (int i = 0; i < maxThread; i++) {
            service.execute(() -> {
                ZkLock lock = new ZkLock();
                ClhLock clhLock = new ClhLock();
                try {
                    lock.lock();
                    clhLock.lock();
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    log.error(e);
                } finally {
                    clhLock.unlock();
                    lock.unlock();
                }
            });
        }
        service.shutdown();
    }
}
