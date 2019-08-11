
package cn.stormbirds.allpay.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


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

    @NacosValue("${spring.datasource.druid.url:}")
    private String url;

    @NacosValue("${spring.datasource.druid.username:}")
    private String username;

    @NacosValue("${spring.datasource.druid.password:}")
    private String password;

    @NacosValue("${spring.datasource.druid.driverClassName:}")
    private String driverClassName;

    @NacosValue("${spring.datasource.druid.initialSize:-1}")
    private Integer initialSize;

    @NacosValue("${spring.datasource.druid.minIdle:-1}")
    private Integer minIdle;

    @NacosValue("${spring.datasource.druid.maxActive:-1}")
    private Integer maxActive;

    @NacosValue("${spring.datasource.druid.maxWait:-1}")
    private Long maxWait;

    @NacosValue("${spring.datasource.druid.timeBetweenEvictionRunsMillis:-1}")
    private Long timeBetweenEvictionRunsMillis;

    @NacosValue("${spring.datasource.druid.minEvictableIdleTimeMillis:-1}")
    private Long minEvictableIdleTimeMillis;

    @NacosValue("${spring.datasource.druid.validationQuery:select 1}")
    private String validationQuery;

    @NacosValue("${spring.datasource.druid.validationQueryTimeout:-1}")
    private Integer validationQueryTimeout;

    @NacosValue("${spring.datasource.druid.testWhileIdle:true}")
    private Boolean testWhileIdle;

    @NacosValue("${spring.datasource.druid.testOnBorrow:true}")
    private Boolean testOnBorrow;

    @NacosValue("${spring.datasource.druid.testOnReturn:false}")
    private Boolean testOnReturn;

    @NacosValue("${spring.datasource.druid.keepAlive:false}")
    private Boolean keepAlive;

    @NacosValue("${spring.datasource.druid.poolPreparedStatements:false}")
    private Boolean poolPreparedStatements;

    @NacosValue("${spring.datasource.druid.maxPoolPreparedStatementPerConnectionSize:-1}")
    private Integer maxPoolPreparedStatementPerConnectionSize;

    @NacosValue("${spring.datasource.druid.filters:}")
    private String filters;

    @NacosValue("${spring.datasource.druid.connectProperties:}")
    private String connectProperties;
    @Autowired
    private DataSourceProperties basicProperties;

    @Autowired
    private DynamicDataSourceContextHolder dynamicDataSourceContextHolder;

    @ConfigurationProperties("spring.datasource")
    @Bean()
    public DataSource dataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = DynamicDataSourceContextHolder.getDynamicRoutingDataSource();
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        if(url!=null && !url.isEmpty()) {
            druidDataSource.setUrl(url);
        }else{
            druidDataSource.setUrl(basicProperties.getUrl());
        }
        if(username!=null && !username.isEmpty()) {
            druidDataSource.setUsername(username);
        }else {
            druidDataSource.setUsername(basicProperties.getUsername());
        }
        if(password!=null && !password.isEmpty()) {
            druidDataSource.setPassword(password);
        }else{
            druidDataSource.setPassword(basicProperties.getPassword());
        }
        if(driverClassName!=null && !driverClassName.isEmpty()) {
            druidDataSource.setDriverClassName(driverClassName);
        }else{
            druidDataSource.setDriverClassName(basicProperties.getDriverClassName());
        }
        if(initialSize!=null && initialSize!=-1) {
            druidDataSource.setInitialSize(initialSize);
        }
        if(maxActive!=null && maxActive!=-1) {
            druidDataSource.setMaxActive(maxActive);
        }
        if(minIdle!=null && minIdle!=-1) {
            druidDataSource.setMinIdle(minIdle);
        }
        if(maxWait!=null && maxWait!=-1) {
            druidDataSource.setMaxWait(maxWait);
        }
        if(poolPreparedStatements!=null) {
            druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
        }
        if(maxPoolPreparedStatementPerConnectionSize!=null && maxPoolPreparedStatementPerConnectionSize!=-1) {
            druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        }
        if(validationQuery!=null && !validationQuery.isEmpty()) {
            druidDataSource.setValidationQuery(validationQuery);
        }
        if(validationQueryTimeout!=null && validationQueryTimeout!=-1) {
            druidDataSource.setValidationQueryTimeout(validationQueryTimeout);
        }
        if(testOnBorrow!=null) {
            druidDataSource.setTestOnBorrow(testOnBorrow);
        }
        if(testOnReturn!=null) {
            druidDataSource.setTestOnReturn(testOnReturn);
        }
        if(testWhileIdle!=null) {
            druidDataSource.setTestWhileIdle(testWhileIdle);
        }
        if(keepAlive!=null) {
            druidDataSource.setKeepAlive(keepAlive);
        }
        if(timeBetweenEvictionRunsMillis!=null && timeBetweenEvictionRunsMillis!=-1) {
            druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        }
        if(minEvictableIdleTimeMillis!=null && minEvictableIdleTimeMillis!=-1) {
            druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        }
//            druidDataSource.setConnectionInitSqls(connectionInitSqls);
        if(filters!=null && !filters.isEmpty()) {
            try {
                druidDataSource.setFilters(filters);
            } catch (SQLException e) {
                log.error("setFilters error", e);
            }
        }
        if (connectProperties != null && !connectProperties.isEmpty()) {
            Properties properties = new Properties();
            List<String> strings = Arrays.asList(connectProperties.split(";"));
            properties.putAll(Arrays.stream(connectProperties.split(";"))
                    .collect(Collectors.toMap(s -> s.substring(0,s.indexOf("=")), s -> s.substring(s.indexOf("=")+1))));
            druidDataSource.setConnectProperties(properties);
        }

        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-","");
        DynamicDataSourceContextHolder.setDataSourceKey(key);
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

/*

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
        DynamicRoutingDataSource dynamicRoutingDataSource = DynamicDataSourceContextHolder.getDynamicRoutingDataSource();
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);

        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-","");
        DynamicDataSourceContextHolder.setDataSourceKey(key);
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
*/
