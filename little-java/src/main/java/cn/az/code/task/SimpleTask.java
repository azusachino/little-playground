package cn.az.code.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ycpang
 * @since 2021-01-28 11:26
 */
@Component
public class SimpleTask {

    @Scheduled(cron = "0/5 * * * * *")
    public void execute() {
        System.out.println("Hello Simple Job");
    }
}
