package cn.az.code.job;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ycpang
 * @since 2021-01-26 14:19
 */
@Service
public class ScheduleJobService extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements IService<ScheduleJob> {
}
