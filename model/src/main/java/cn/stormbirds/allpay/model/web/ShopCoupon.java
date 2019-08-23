package cn.stormbirds.allpay.model.web;

import java.util.Date;

public class ShopCoupon {
    private Long id;

    private Long shop;

    private String couponName;

    private String couponPic;

    private Date cteatTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShop() {
        return shop;
    }

    public void setShop(Long shop) {
        this.shop = shop;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getCouponPic() {
        return couponPic;
    }

    public void setCouponPic(String couponPic) {
        this.couponPic = couponPic == null ? null : couponPic.trim();
    }

    public Date getCteatTime() {
        return cteatTime;
    }

    public void setCteatTime(Date cteatTime) {
        this.cteatTime = cteatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}