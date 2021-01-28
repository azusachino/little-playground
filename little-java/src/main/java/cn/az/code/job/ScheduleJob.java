package cn.az.code.job;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author ycpang
 * @since 2021-01-26 14:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ScheduleJob extends Model<ScheduleJob> {

    public static final String JOB_KEY = "JOB_KEY_";

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 定时器名称
     */
    private String jobName;
    /**
     * Bean名称
     */
    private String beanName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * cron表达式
     */
    @NotBlank
    private String cronExpression;
    /**
     * 状态 1：正常，0：暂停
     */
    private Integer isPause;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 更新日期
     */
    private LocalDateTime updateTime;
}