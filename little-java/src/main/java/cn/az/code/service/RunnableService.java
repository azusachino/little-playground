package cn.az.code.service;

import cn.az.code.task.RunnableTask;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author az
 * @since 02/25/21 22:59
 */
public class RunnableService {

    /**
     * 存储定时任务
     */
    private static final Map<String, ScheduledFuture<?>> TASK_MAP = new ConcurrentHashMap<>(16);

    private static final int SIZE = 10;

    @Resource
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public void startTasks() {
        for (int i = 0; i < SIZE; ++i) {
            String uuid = UUID.fastUUID().toString(true);
            RunnableTask rt = new RunnableTask();
            TASK_MAP.put(uuid, scheduledThreadPoolExecutor.schedule(rt, 10, TimeUnit.SECONDS));
        }
    }

    public void endTask() {
        int index = RandomUtil.randomInt(TASK_MAP.size());
        String key = TASK_MAP.keySet().toArray()[index].toString();
        ScheduledFuture<?> sf = TASK_MAP.get(key);
        sf.cancel(true);
        TASK_MAP.remove(key);
    }
}
