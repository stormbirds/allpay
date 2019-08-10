package cn.stormbirds.allpay.common.config;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.pool.DataSourceNotAvailableException;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;
import sun.nio.cs.CharsetMapping;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * <p>
 * cn.stormbirds.allpay.common.config
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/5 11:47
 */

@Slf4j
@Component
public class DynamicDataSourceContextHolder {
    @Autowired
    private DataSourceProperties basicProperties;
    @Autowired
    private ConfigFilter configFilter;



    private static ThreadLocal<String> contextHolder = null;
    private static DynamicRoutingDataSource routingDataSourceThreadLocal = null;


    public void setDataSource(String url, String username, String password, String driverClassName) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-", "");
        dataSourceMap.put(key, dataSource);
        contextHolder.set(key);
        routingDataSourceThreadLocal.setTargetDataSources(dataSourceMap);
        routingDataSourceThreadLocal.setDefaultTargetDataSource(dataSource);
        routingDataSourceThreadLocal.afterPropertiesSet();
    }

    public void setDataSource(Properties properties) throws SQLException {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        Properties dataSourceProperties = new Properties();

        dataSourceProperties.putAll(properties.entrySet().stream()
                .filter(entry -> entry.getKey().toString().startsWith("spring.datasource")).collect(Collectors.toMap(
                        e -> e.getKey().toString().replace("spring.datasource.", ""),
                        Map.Entry::getValue)));
        dataSource.setName("DynamicDataSource");

        dataSource.configFromPropety(dataSourceProperties);
        if (properties.getProperty("spring.datasource.url") != null) {
            dataSource.setUrl(properties.getProperty("spring.datasource.url"));
        } else if (properties.getProperty("spring.datasource.druid.url") != null) {
            dataSource.setUrl(properties.getProperty("spring.datasource.druid.url"));
        } else {
            throw new SQLException();
        }
        if (properties.getProperty("spring.datasource.username") != null) {
            dataSource.setUsername(properties.getProperty("spring.datasource.username"));
        } else if (properties.getProperty("spring.datasource.druid.username") != null) {
            dataSource.setUsername(properties.getProperty("spring.datasource.druid.username"));
        } else {
            throw new SQLException("Not Found DataSource UserName");
        }
        if (properties.getProperty("spring.datasource.password") != null) {
            dataSource.setPassword(properties.getProperty("spring.datasource.password"));

        } else if (properties.getProperty("spring.datasource.druid.password") != null) {
            dataSource.setPassword(properties.getProperty("spring.datasource.druid.password"));
        } else {
            throw new SQLException("Not Found DataSource password");
        }

        if (properties.getProperty("spring.datasource.driverClassName") != null) {
            dataSource.setDriverClassName(properties.getProperty("spring.datasource.driverClassName"));
        } else if (properties.getProperty("spring.datasource.druid.driverClassName") != null) {
            dataSource.setDriverClassName(properties.getProperty("spring.datasource.druid.driverClassName"));
        }
        configFilter.init(dataSource);
        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-", "");
        dataSourceMap.put(key, dataSource);
        contextHolder.set(key);
        routingDataSourceThreadLocal.setTargetDataSources(dataSourceMap);
        routingDataSourceThreadLocal.setDefaultTargetDataSource(dataSource);
        routingDataSourceThreadLocal.afterPropertiesSet();
    }


    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }

    public static DynamicRoutingDataSource getDynamicRoutingDataSource() {
        if (routingDataSourceThreadLocal == null) {
            routingDataSourceThreadLocal = new DynamicRoutingDataSource();

        }

        return routingDataSourceThreadLocal;
    }

}
