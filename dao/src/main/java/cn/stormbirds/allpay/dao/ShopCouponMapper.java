package cn.stormbirds.allpay.dao;



import cn.stormbirds.allpay.model.web.ShopCoupon;

import java.util.List;
import java.util.Map;

public interface ShopCouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopCoupon record);

    int insertSelective(ShopCoupon record);

    ShopCoupon selectByPrimaryKey(Long id);

    List<ShopCoupon> selectByShopId(Long id);

    int updateByPrimaryKeySelective(ShopCoupon record);

    int updateByPrimaryKey(ShopCoupon record);
}