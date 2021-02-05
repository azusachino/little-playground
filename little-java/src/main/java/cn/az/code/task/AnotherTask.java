package cn.az.code.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ycpang
 * @since 2021-02-05 18:22
 */
@Slf4j
@Component
public class AnotherTask {

    @Scheduled(fixedRate = 1_000L)
    public void execute() {
        log.info("Current DateTime is, {}", LocalDateTime.now());
    }
}
