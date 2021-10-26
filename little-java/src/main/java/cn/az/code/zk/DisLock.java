package cn.az.code.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Lock
 *
 * @author ycpang
 * @since 2021-10-25 11:57
 */
public class DisLock {

    public void lock() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("localhost", 3000, null);
        final String resource = "/resource";

        final String lockNumber = zk
            .create("/resource/lock-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        List<String> locks = zk.getChildren(resource, false, null);
        Collections.sort(locks);

        if (locks.get(0).equals(lockNumber.replace("/resource/", ""))) {
            System.out.println("Acquire Lock");
            zk.delete(lockNumber, 0);
        } else {
            zk.getChildren(resource, watchedEvent -> {
                try {
                    ZooKeeper zk1 = new ZooKeeper("localhost", 3000, null);
                    List<String> locks1 = zk1.getChildren(resource, null, null);
                    Collections.sort(locks1);

                    if (locks1.get(0).equals(lockNumber.replace("/resource/", ""))) {
                        System.out.println("Acquire Lock");
                        zk1.delete(lockNumber, 0);
                    }

                } catch (Exception ignored) {
                }
            }, null);
        }
    }

    public void lock2() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("localhost", 3000, null);
        final String resource = "/resource";

        final String lockNumber = zk
            .create("/resource/lock-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        Integer number = Integer.parseInt(lockNumber.replace("/resource/lock-", "")) - 1;
        String previousLock = "/resource/lock-" + String.format("%010d", number);

        zk.getData(previousLock, watchedEvent -> {
            try {
                if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                    System.out.println("Acquire Lock");
                    ZooKeeper zk1 = new ZooKeeper("localhost", 3000, null);
                    zk1.delete(lockNumber, 0);
                }
            } catch (Exception ignored) {
            }
        }, null);
    }
}
