package cn.az.code.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * ZK pub sub
 *
 * @author ycpang
 * @since 2021-10-25 11:44
 */
public class PubSub {

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        doTest();
    }

    public static void doTest() throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("", 3000, null);
        // register watcher, watches every operation on /config
        zk.getData("/config", System.out::println, null);

        zk.setData("/config", "test=123".getBytes(StandardCharsets.UTF_8), 0);

        Thread.sleep(Duration.ofSeconds(10));
        zk.close();
    }

}
