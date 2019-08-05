package cn.stormbirds.allpay.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 * cn.stormbirds.allpayusers.config
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/2 10:51
 */
@Slf4j
@Configuration
public class DataSourceConfig {
    @NacosValue(value = "${spring.datasource.url:}", autoRefreshed = true)
    private String url;
    @NacosValue(value = "${spring.datasource.username:}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${spring.datasource.password:}", autoRefreshed = true)
    private String password;
    @NacosValue(value = "${spring.datasource.driverClassName:}", autoRefreshed = true)
    private String driverClassName;


    @Scope("singleton")
    @Primary
    @Bean
    public DataSource dataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource=new DynamicRoutingDataSource();
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);

        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-","");
        dataSourceMap.put(key,druidDataSource);
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        dynamicRoutingDataSource.setDefaultTargetDataSource(druidDataSource);
        return dynamicRoutingDataSource;
    }

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
//        sqlSessionFactoryBean.setDataSource(dataSource());
//        return sqlSessionFactoryBean;
//    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
