package cn.stormbirds.allpay.model.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * <p>
 * cn.stormbirds.allpay.model.config
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/3 18:09
 */

@Data
public class DataSourceBean {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

}
