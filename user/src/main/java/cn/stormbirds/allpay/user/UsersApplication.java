package cn.stormbirds.allpay.user;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ServletComponentScan
@SpringBootApplication(scanBasePackages = "cn.stormbirds.allpay")
@NacosPropertySource(dataId = "huawei_cloud", autoRefreshed = true)
@MapperScan(basePackages = "cn.stormbirds.allpay.dao")
//@ComponentScan(basePackages = "cn.stormbirds.allpay")
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }

}
