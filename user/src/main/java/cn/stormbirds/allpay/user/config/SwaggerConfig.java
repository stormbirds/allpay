package cn.stormbirds.allpay.user.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private Contact contact = new Contact("stormbirds","https://blog.stormbirds.cn", "xbaojun@gmail.com");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "分布式支付订单系统-用户服务 Swagger 实例文档",
                "我的博客网站：https://blog.stormbirds.cn，欢迎大家访问。",
                "API V1.0",
                null,
                contact,
                "Apache", "https://opensource.org/licenses/Apache-2.0", Collections.emptyList());
    }
}