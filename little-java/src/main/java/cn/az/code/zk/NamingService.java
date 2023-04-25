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

    // 使用上面的代码就能在 Zookeeper 中创建两个带序号的 metrics 节点，分别是 metrics0000000001 和
    // metrics0000000002，也就是说 Zookeeper 帮助我们保证了节点的唯一性，让我们能通过唯一的 ID 查找到对应服务的地址等信息。
    public void doT() throws InterruptedException, KeeperException, IOException {
        ZooKeeper zk = new ZooKeeper("localhost", 3000, null);

        zk.create("/metrics", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        zk.create("/metrics", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

        List<String> ch = zk.getChildren("/", null);
        System.out.println(ch);
        zk.close();
    }
}
