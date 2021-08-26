package cn.az.webflux.service.impl;

import cn.az.webflux.entity.DateTest;
import cn.az.webflux.mapper.DateTestMapper;
import cn.az.webflux.service.DateTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * TODO
 *
 * @author az
 * @since 2021-08-05 22:43
 */
@Service
@Transactional(rollbackFor = SQLException.class)
public class DateTestServiceImpl extends ServiceImpl<DateTestMapper, DateTest> implements DateTestService {
}
