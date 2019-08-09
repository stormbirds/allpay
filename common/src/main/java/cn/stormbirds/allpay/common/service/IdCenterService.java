package cn.stormbirds.allpay.common.service;

/**
 *
 * <p> IdCenterService.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/6 16:06
 *
 */
public interface IdCenterService {
    long getId();
    long getSyncId();
    String getSyncIdString();
}