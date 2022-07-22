package cn.az.code.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Op;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ycpang
 * @since 2021-10-25 11:54
 */
public class Coordinator {

    public void doT() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("localhost", 3000, null);
        String path = zk.create("/transfer/tx", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

        List<Op> ops = Arrays.asList(
            Op.create(path + "/cohort", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL),
            Op.create(path + "/cohort", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL),
            Op.create(path + "/cohort", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL)
        );
        zk.multi(ops);
        zk.close();
    }
}
