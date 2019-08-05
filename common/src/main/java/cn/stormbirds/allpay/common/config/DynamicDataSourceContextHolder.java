package cn.stormbirds.allpay.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * <p>
 * cn.stormbirds.allpay.common.config
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/5 11:47
 */

@Component
public class DynamicDataSourceContextHolder {
    private static ThreadLocal<String> contextHolder=new ThreadLocal<String>();

    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;

    public void setDataSource(String url,String username,String password,String driverClassName){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-","");
        dataSourceMap.put(key,dataSource);
        contextHolder.set(key);
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
    }

    public static String getDataSourceKey() {
        return contextHolder.get();
    }

}
