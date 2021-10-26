package cn.az.code.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * ZK pub sub
 *
 * @author ycpang
 * @since 2021-10-25 11:44
 */
public class PubSub {


    public void doTest() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("", 3000, null);
        // register watcher, watches every operation on /config
        zk.getData("/config", System.out::println, null);

        zk.setData("/config", "dddd".getBytes(StandardCharsets.UTF_8), 0);
    }

}
