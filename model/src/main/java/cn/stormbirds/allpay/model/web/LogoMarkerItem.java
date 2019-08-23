package cn.stormbirds.allpay.model.web;

import cn.stormbirds.allpay.common.utils.clusterutils.ClusterItem;
import cn.stormbirds.allpay.common.utils.clusterutils.LatLng;
import cn.stormbirds.allpay.common.utils.clusterutils.LatLngBounds;

/**
 *
 * <p> LogoMarkerItem.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/19 10:48
 *
 */
public class LogoMarkerItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;
    private FindRoundModel deviceInfo;
    private int size = 0 ;
    private LatLngBounds bounds;

    public LogoMarkerItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
        mTitle = null;
        mSnippet = null;
        this.deviceInfo = null;
        this.size = 0;
        this.bounds = null;
    }

    public LogoMarkerItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
        this.deviceInfo = null;
        this.size = 0;
        this.bounds = null;
    }

    public LogoMarkerItem(CustomFindModel model) {
        this.mPosition = new LatLng(model.getLa(),model.getLo());
        this.mTitle = null;
        this.mSnippet = null;
        this.deviceInfo = model;
        this.size = model.getSize();
        this.bounds = model.getBounds();
    }

    public LogoMarkerItem(FindRoundModel findRoundEqModel) {
        this.mPosition = new LatLng(findRoundEqModel.getLa(),findRoundEqModel.getLo());
        this.mTitle = null;
        this.mSnippet = null;
        this.deviceInfo = findRoundEqModel;
        this.size = 0;
        this.bounds = null;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() { return mTitle; }

    @Override
    public String getSnippet() { return mSnippet; }

    /**
     * Set the title of the marker
     * @param title string to be set as title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

    /**
     * Set the description of the marker
     * @param snippet string to be set as snippet
     */
    public void setSnippet(String snippet) {
        mSnippet = snippet;
    }

    public FindRoundModel getDeviceInfo() {
        return deviceInfo;
    }

    public LogoMarkerItem setDeviceInfo(FindRoundModel deviceInfo) {
        this.deviceInfo = deviceInfo;
        return this;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LatLngBounds getBounds() {
        return bounds;
    }

    public void setBounds(LatLngBounds bounds) {
        this.bounds = bounds;
    }
}

