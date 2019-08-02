package cn.stormbirds.allpayusers.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * <p>
 * cn.stormbirds.allpayusers.config
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/2 10:51
 */

@Configuration
public class DataSourceConfig {

    @Resource
    private DataSourceBean dataSourceBean;


    @Bean
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceBean.getUrl());
        dataSource.setUsername(dataSourceBean.getUsername());
        dataSource.setPassword(dataSourceBean.getPassword());
        dataSource.setDriverClassName(dataSourceBean.getDriverClassName());
        return dataSource;
    }
}
