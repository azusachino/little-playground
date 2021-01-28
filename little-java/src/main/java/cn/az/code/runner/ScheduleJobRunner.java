package cn.az.code.runner;

import cn.az.code.job.ScheduleJob;
import cn.az.code.job.ScheduleJobService;
import cn.az.code.job.ScheduleManager;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务执行器，启动完成后开始执行
 *
 * @author ycpang
 * @since 2021-01-26 14:06
 */
@Component
public class ScheduleJobRunner implements ApplicationRunner {

    @Resource
    private ScheduleJobService scheduleJobService;

    @Resource
    private ScheduleManager scheduleManager;

    /**
     * 项目启动时重新激活启用的定时任务
     *
     * @param applicationArguments arguments
     */
    @Override
    public void run(ApplicationArguments applicationArguments) {
        List<ScheduleJob> scheduleJobs = scheduleJobService.list(Wrappers.<ScheduleJob>lambdaQuery().eq(ScheduleJob::getIsPause, false));
        scheduleJobs.forEach(scheduleJob -> {
            scheduleManager.addJob(scheduleJob);
        });
    }
}