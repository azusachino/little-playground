package cn.az.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author az
 * @version 2020-03-07
 */
@EnableFeignClients
@MapperScan(basePackages = "cn.az.code.mapper")
@ServletComponentScan(basePackages = { "cn.az.code.servlet" })
@SpringBootApplication(exclude = { ElasticsearchDataAutoConfiguration.class,
        ElasticsearchRepositoriesAutoConfiguration.class })

public class LittleJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleJavaApplication.class, args);
    }

}
