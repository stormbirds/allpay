package cn.stormbirds.allpay.model.web;

import lombok.Data;

/**
 * <p>
 * cn.stormbirds.allpay.model.web
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/15 17:54
 */
@Data
public class FindRoundModel {
    /**
     * 主键
     */
    private Long id;
    /**
     * 可借电池
     */
    private String cb;
    /**
     * 可还电池
     */
    private String cr;
    /**
     * 设备所在的经度
     */
    private Double lo;
    /**
     * 设备所在的纬度
     */
    private Double la;
    /**
     * 店铺名
     */
    private String sName;
    /**
     * 店铺logo
     */
    private String sLogo;
    /**
     * 店铺地址
     */
    private String address;

    public FindRoundModel() {
    }

    public FindRoundModel(LogoMarkerItem markerItem) {
        this.id = markerItem.getDeviceInfo().getId();
        this.cb = markerItem.getDeviceInfo().getCb();
        this.cr = markerItem.getDeviceInfo().getCr();
        this.lo = markerItem.getDeviceInfo().getLo();
        this.la = markerItem.getDeviceInfo().getLa();
        this.sName = markerItem.getDeviceInfo().getSName();
        this.address = markerItem.getDeviceInfo().getAddress();
        this.sLogo = markerItem.getDeviceInfo().getSLogo();
    }
}
