package cn.stormbirds.allpay.model.web;

import cn.stormbirds.allpay.common.utils.clusterutils.ClusterItem;
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
public class CustomFindModel implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private FindRoundEqModel model;
    private int size = 0 ;
    private LatLngBounds bounds;

    public CustomFindModel(FindRoundEqModel model) {
        this.mPosition = new LatLng(model.getLa(),model.getLo());
        this.model = model;
        this.mTitle = null;
        this.mSnippet = null;
        this.bounds = null;
    }
    public CustomFindModel(int size, LatLngBounds bound){
        this.mPosition = bound.getCenter();
        this.size = size;
        this.bounds = bound;
        this.model = null;
        this.mTitle = null;
        this.mSnippet = null;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    public FindRoundEqModel getModel() {
        return model;
    }

    public void setModel(FindRoundEqModel model) {
        this.model = model;
    }
}
