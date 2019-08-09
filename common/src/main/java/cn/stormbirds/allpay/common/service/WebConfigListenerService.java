/*
package cn.stormbirds.allpay.common.service;


import cn.stormbirds.allpay.common.config.DynamicDataSourceContextHolder;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.util.parse.DefaultPropertiesConfigParse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Properties;

*/
/**
 * <p>
 * cn.stormbirds.allpay.service
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/3 17:24
 *//*

@Slf4j
@Service
public class WebConfigListenerService {
    @NacosValue(value = "${spring.datasource.url:}", autoRefreshed = true)
    private String url;
    @NacosValue(value = "${spring.datasource.username:}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${spring.datasource.password:}", autoRefreshed = true)
    private String password;
    @NacosValue(value = "${spring.datasource.driverClassName:}", autoRefreshed = true)
    private String driverClassName;

    @Autowired
    private DynamicDataSourceContextHolder dynamicDataSourceContextHolder;

    @NacosConfigListener(
            dataId = "huawei_cloud",
            timeout = 5000
    )
    public void onChange(String message) throws Exception {
        Properties properties = new DefaultPropertiesConfigParse().parse(message);
        for (Object t : properties.keySet()) {
            String key = String.valueOf(t);
            log.info("获取到属性文件 key={} value={}",key,properties.getProperty(key));
        }

        dynamicDataSourceContextHolder.setDataSource(properties);
        log.info("ConfigListenerService {}", message);
    }
}
*/
