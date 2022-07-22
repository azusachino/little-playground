package cn.az.code.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * zk ns
 *
 * @author ycpang
 * @since 2021-10-25 11:52
 */
public class NamingService {

    public void doT() throws InterruptedException, KeeperException, IOException {
        ZooKeeper zk = new ZooKeeper("", 3000, null);

        zk.create("/metrics", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        zk.create("/metrics", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

        List<String> ch = zk.getChildren("/", null);
        System.out.println(ch);
        zk.close();
    }
}
