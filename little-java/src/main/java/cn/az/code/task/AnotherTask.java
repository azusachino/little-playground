package cn.az.code.task;

import cn.az.code.util.LogUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ycpang
 * @since 2021-02-05 18:22
 */
@Component
public class AnotherTask {

    @Scheduled(fixedRate = 1_000L)
    public void execute() {
        LogUtil.info("Current DateTime is, " + LocalDateTime.now());
    }
}
