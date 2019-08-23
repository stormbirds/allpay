package cn.stormbirds.allpay.model.web;

import cn.stormbirds.allpay.common.utils.clusterutils.LatLng;
import cn.stormbirds.allpay.common.utils.clusterutils.LatLngBounds;
import lombok.Data;

/**
 * <p>
 * cn.stormbirds.allpay.model.web
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/14 15:26
 */

@Data
public class CustomFindModel extends FindRoundModel{
    private int size = 0 ;
    private LatLngBounds bounds;

    public CustomFindModel(FindRoundModel model) {
        super.setId(model.getId());
        super.setAddress(model.getAddress());
        super.setSName(model.getSName());
        super.setCb(model.getCb());
        super.setCr(model.getCr());
        super.setLa(model.getLa());
        super.setLo(model.getLo());
        this.size = 1;
        this.bounds = null;
    }
    public CustomFindModel(int size, LatLngBounds bound){
        this.size = size;
        this.bounds = bound;
    }

    public CustomFindModel(LogoMarkerItem logoMarkerItem) {
        super(logoMarkerItem);
        this.size = 1;
        this.bounds = null;
    }

}
