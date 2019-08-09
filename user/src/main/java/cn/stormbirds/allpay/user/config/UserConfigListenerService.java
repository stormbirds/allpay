package cn.stormbirds.allpay.user.config;


import cn.stormbirds.allpay.common.config.DynamicDataSourceContextHolder;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private final DynamicDataSourceContextHolder dynamicDataSourceContextHolder;

    @Autowired
    public UserConfigListenerService(DynamicDataSourceContextHolder dynamicDataSourceContextHolder) {
        this.dynamicDataSourceContextHolder = dynamicDataSourceContextHolder;
    }

    @NacosConfigListener(dataId = "allpay_user-dev.properties")
    public void onReceived(Properties message) throws Exception {

        dynamicDataSourceContextHolder.setDataSource(message);

        log.info("ConfigListenerService {}", message);
    }

}
