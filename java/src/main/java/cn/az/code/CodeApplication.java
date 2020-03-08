package cn.az.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author az
 * @version 2020-03-07
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"cn.az.code.servlet"})
public class CodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeApplication.class, args);
    }

}
