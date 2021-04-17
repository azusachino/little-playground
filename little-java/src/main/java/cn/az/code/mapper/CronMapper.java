package cn.az.code.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author az
 * @since 2021-04-17 21:39
 */
@Mapper
public interface CronMapper {

    /**
     * 获取表达式
     *
     * @return cron
     */
    @Select("select cron from cron limit 1")
    String getCron();
}