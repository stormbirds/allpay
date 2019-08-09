package cn.stormbirds.allpay.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * <p> DynamicRoutingDataSource.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/6 09:45
 */


@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
