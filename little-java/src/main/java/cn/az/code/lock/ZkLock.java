package cn.az.code.lock;

import cn.hutool.log.Log;
import com.google.common.base.Strings;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author az
 * @since 2020-04-14
 */
public class ZkLock {

    private static final Log log = Log.get();

    private final String lockNameSpace = "/local-lock";

    private final String nodeString = lockNameSpace + "/test";

    private ZooKeeper zk;

    public ZkLock() {
        try {
            String zkHostPort = "localhost:2181";
            zk = new ZooKeeper(zkHostPort, 6000, watchedEvent -> {
                System.out.println("Receive event " + watchedEvent);
                if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    System.out.println("connection is established...");
                }
            });
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void ensureRootPath() throws InterruptedException {
        try {
            if (zk.exists(lockNameSpace, true) == null) {
                zk.create(lockNameSpace, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            log.error(e);
        }
    }

    private void watchNode(final Thread thread) throws InterruptedException {
        try {
            zk.exists(nodeString, watchedEvent -> {
                System.out.println("==" + watchedEvent.toString());
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                    System.out.println("There is a Thread released Lock==============");
                    thread.interrupt();
                }
                try {
                    zk.exists(nodeString, watchedEvent1 -> {
                        System.out.println("==" + watchedEvent1.toString());
                        if (watchedEvent1.getType() == Watcher.Event.EventType.NodeDeleted) {
                            System.out.println("There is a Thread released Lock==============");
                            thread.interrupt();
                        }
                        try {
                            zk.exists("/mylock/test1", true);
                        } catch (KeeperException | InterruptedException e) {
                            log.error(e);
                        }
                    });
                } catch (KeeperException | InterruptedException e) {
                    log.error(e);
                }
            });
        } catch (KeeperException e) {
            log.error(e);
        }
    }

    /**
     * 获取锁
     *
     * @return boolean
     * @throws InterruptedException e
     */
    public boolean lock() throws InterruptedException {
        String path = null;
        ensureRootPath();
        watchNode(Thread.currentThread());
        while (true) {
            try {
                path = zk.create(nodeString, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (KeeperException e) {
                System.out.println(Thread.currentThread().getName() + " is getting Lock but failed");
            }
            if (!Strings.nullToEmpty(path).trim().isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " gets Lock...");
                return true;
            }
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        try {
            zk.delete(nodeString, -1);
            System.out.println("Thread.currentThread().getName() +  released Lock...");
        } catch (InterruptedException | KeeperException e) {
            log.error(e);
        }
    }
}
