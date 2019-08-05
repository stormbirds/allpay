package cn.stormbirds.allpay.user;

import cn.stormbirds.allpay.common.utils.SpringContextUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.sun.jndi.ldap.pool.PooledConnectionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import javax.sql.DataSource;

@SpringBootApplication(scanBasePackages = "cn.stormbirds.allpay")
@NacosPropertySource(dataId = "huawei_cloud", autoRefreshed = true)
@MapperScan(basePackages = "cn.stormbirds.allpay.dao")
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
