package cn.az.code.job;

import cn.az.code.util.LogUtil;
import cn.hutool.core.thread.ThreadUtil;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author ycpang
 * @since 2021-01-26 14:10
 */
@Async
public class ExecutionJob extends QuartzJobBean {

    private final ExecutorService executorService = ThreadUtil.newSingleExecutor();

    @Override
    protected void executeInternal(JobExecutionContext context) {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_KEY);

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            LogUtil.info("任务准备执行, 任务名称:" + scheduleJob.getJobName());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(),
                    scheduleJob.getParams());
            Future<?> future = executorService.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;
            LogUtil.info("任务执行完毕，任务名称：{} 总共耗时：{} 毫秒" + scheduleJob.getJobName() + times);
        } catch (Exception e) {
            LogUtil.error("任务执行失败，任务名称：{}" + scheduleJob.getJobName(), e);
        }
    }
}
