package cn.stormbirds.allpay.service;

import cn.stormbirds.allpay.common.config.DataSourceConfig;
import cn.stormbirds.allpay.common.config.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidDataSourceMBean;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * <p>
 * cn.stormbirds.allpay.service
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/3 17:24
 */
@Slf4j
@Service
public class ConfigListenerService {
    @NacosValue(value = "${spring.datasource.url:}", autoRefreshed = true)
    private String url;
    @NacosValue(value = "${spring.datasource.username:}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${spring.datasource.password:}", autoRefreshed = true)
    private String password;
    @NacosValue(value = "${spring.datasource.driverClassName:}", autoRefreshed = true)
    private String driverClassName;
    @Autowired
    private DataSourceConfig dataSourceConfig;
    @Autowired
    private DruidDataSource druidDataSource;
    @NacosConfigListener(
            dataId = "huawei_cloud",
            timeout = 5000
    )
    public void onChange(String message) throws Exception {
        druidDataSource=dataSourceConfig.dataSource();
        druidDataSource.restart();
        log.info("ConfigListenerService {}", message);
        log.info(url,username,password,driverClassName);
    }
}
