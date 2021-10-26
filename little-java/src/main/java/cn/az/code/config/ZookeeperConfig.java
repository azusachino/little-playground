package cn.az.code.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Zk Config
 *
 * @author ycpang
 * @since 2021-10-26 19:23
 */
@Configuration
public class ZookeeperConfig {

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.newClient(
            "localhost:2181",
            1000,
            1000,
            new RetryNTimes(3, 1000)
        );
    }
}
