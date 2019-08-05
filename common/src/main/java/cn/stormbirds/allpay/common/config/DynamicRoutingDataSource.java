package cn.stormbirds.allpay.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
/*    @NacosValue(value = "${spring.datasource.url:}", autoRefreshed = true)
    private String url;
    @NacosValue(value = "${spring.datasource.username:}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${spring.datasource.password:}", autoRefreshed = true)
    private String password;
    @NacosValue(value = "${spring.datasource.driverClassName:}", autoRefreshed = true)
    private String driverClassName;

    public DynamicRoutingDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setDriverClassName(driverClassName);

        Map<Object, Object> dataSourceMap = new HashMap<>(4);
        String key = UUID.randomUUID().toString().replace("-","");
        dataSourceMap.put(key,druidDataSource);
        super.setDefaultTargetDataSource(druidDataSource);
        super.setTargetDataSources(dataSourceMap);
    }*/

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Current DataSource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}