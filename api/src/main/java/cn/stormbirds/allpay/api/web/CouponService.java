package cn.stormbirds.allpay.api.web;


import cn.stormbirds.allpay.model.web.ShopCoupon;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author ZMY
 * @date 2019/3/1217:05
 */
public interface CouponService {

    void insertCoupon(Long shopId, String name, String url);

    List<ShopCoupon> selectByShopId(Long shopId);

    Map delById(Long id);

    Map addCoupon(Long id, List<String> url, List<String> name);
}
