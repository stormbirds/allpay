package cn.stormbirds.allpay.service;


import cn.stormbirds.allpay.common.config.DataSourceConfig;
import cn.stormbirds.allpay.common.config.DynamicDataSourceContextHolder;
import cn.stormbirds.allpay.common.config.DynamicRoutingDataSource;
import cn.stormbirds.allpay.common.utils.SpringContextUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.util.parse.DefaultPropertiesConfigParse;
import javafx.scene.chart.XYChart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.sql.PooledConnection;
import java.util.Properties;

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
public class UserConfigListenerService {
    @NacosValue(value = "${spring.datasource.url:}", autoRefreshed = true)
    private String url;
    @NacosValue(value = "${spring.datasource.username:}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${spring.datasource.password:}", autoRefreshed = true)
    private String password;
    @NacosValue(value = "${spring.datasource.driverClassName:}", autoRefreshed = true)
    private String driverClassName;
    @NacosValue(value = "${test:}",autoRefreshed = true)
    private Boolean test;
    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Autowired
    private SpringContextUtil springContextUtil;
    @Autowired
    private DynamicDataSourceContextHolder dynamicDataSourceContextHolder;

    @NacosConfigListener(
            dataId = "huawei_cloud",
            timeout = 5000
    )
    public void onChange(String message) throws Exception {
        log.info(" {} {} {} {} {}", url,username,password,driverClassName,test);
//        Properties properties = new DefaultPropertiesConfigParse().parse(message);
//        DynamicDataSourceContextHolder.setDataSource(url,username,password,driverClassName);

//
//        druidDataSource.close();
//        druidDataSource.init();
//        druidDataSource=dataSourceConfig.dataSource();
//        druidDataSource.restart();
        dynamicDataSourceContextHolder.setDataSource(url,username,password,driverClassName);
        log.info("test = {}",test);
        log.info("ConfigListenerService {}", message);
        DruidDataSource druidDataSource = (DruidDataSource)springContextUtil.getBean("dataSource");
    }
}
