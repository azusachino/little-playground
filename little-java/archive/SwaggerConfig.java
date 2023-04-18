package cn.az.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author az
 * @since 09/01/20
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        //用来统一一些公用参数，这里设置前端和后台登录的输入参数
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("sessionId")
                .description("登录令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")  //参数类型为header中的参数
                .required(false)
                .build();

        ParameterBuilder tokenPar1 = new ParameterBuilder();
        tokenPar1.name("backSessionId")
                .description("后台登录令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();

        pars.add(tokenPar.build());
        pars.add(tokenPar1.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(pars)//定义全局的请求参数
                //.globalResponseMessage( customerResponseMessage())//用来自定义全局返回错误码
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lovego.cloud.user"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "用户中心",
                "",
                "",
                "",
                new Contact("", "", ""),
                "",
                "", Collections.emptyList()

        );
    }
}
