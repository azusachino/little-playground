package cn.az.code.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author az
 * @since 09/04/20
 */
@Configuration
@MapperScan(basePackages = {"cn.az.code.mvc.mapper"})
@EnableTransactionManagement
public class MybatisPlusConfig {

    // 启用分页插件

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        interceptor.setDbType(DbType.MYSQL);
        interceptor.setLimit(100);
        return interceptor;
    }

}
