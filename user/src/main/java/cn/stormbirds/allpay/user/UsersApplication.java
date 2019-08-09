package cn.stormbirds.allpay.user;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "cn.stormbirds.allpay")
@NacosPropertySource(dataId = "allpay_user-dev.properties",autoRefreshed = true)
public class UsersApplication {
//    @Bean
//    public static SpringContextUtil springContextUtil(){
//        return new SpringContextUtil();
//    }

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(UsersApplication.class, args);
    }

    public static void refreshDataSource(){

    }

}
