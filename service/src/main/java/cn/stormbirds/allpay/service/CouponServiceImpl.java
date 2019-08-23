package cn.stormbirds.allpay.service;

import cn.stormbirds.allpay.api.web.CouponService;
import cn.stormbirds.allpay.dao.ShopCouponMapper;
import cn.stormbirds.allpay.model.web.ShopCoupon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author ZMY
 * @date 2019/3/1217:06
 */
@Slf4j
@Service
public class CouponServiceImpl implements CouponService {

    @Resource
    ShopCouponMapper shopCouponMapper;

    @Override
    public void insertCoupon(Long shopId, String name,String url) {
        List<ShopCoupon> shopCoupons = shopCouponMapper.selectByShopId(shopId);
        if (shopCoupons.size()>5){
            new RuntimeException("优惠劵不能超过5张");
        }

        if (name == null){
            name = "default";
        }
        ShopCoupon shopCoupon = new ShopCoupon();
        shopCoupon.setShop(shopId);
        shopCoupon.setCteatTime(new Date());
        shopCoupon.setCouponPic(url);
        shopCoupon.setCouponName(name);
        int i = shopCouponMapper.insert(shopCoupon);
        if (i<=0){
            new RuntimeException("新增记录失败");
        }

    }

    @Override
    public List<ShopCoupon> selectByShopId(Long shopId) {

        List<ShopCoupon> shopCoupons = shopCouponMapper.selectByShopId(shopId);
        return shopCoupons;
    }

    @Override
    public Map delById(Long id) {
        int i = shopCouponMapper.deleteByPrimaryKey(id);
        Map map = new HashMap();
        if (i>0){
            //删除成功
            map.put("code","1");
            map.put("msg","删除成功");
            return map;
        }
        //删除失败
        map.put("code","2");
        map.put("msg","删除失败");
        return map;
    }

    @Override
    public Map addCoupon(Long id, List<String> url,List<String> name) {
        List<ShopCoupon> shopCoupons = shopCouponMapper.selectByShopId(id);
        if (url.size()+shopCoupons.size()>5){
            new RuntimeException("优惠劵不能超过5张");
        }
        ShopCoupon shopCoupon = new ShopCoupon();
        String nameStr = "default";
        for (int i = 0; i < url.size() ; i++) {
            String urlStr = url.get(i);
            if (name.size()== url.size()){
                nameStr = url.get(i);
            }
            shopCoupon.setShop(id);
            shopCoupon.setCteatTime(new Date());
            shopCoupon.setCouponPic(urlStr);
            shopCoupon.setCouponName(nameStr);
            int j = shopCouponMapper.insert(shopCoupon);
            if (j<=0){
                new RuntimeException("新增记录失败");
            }
        }
        Map map = new HashMap();
        map.put("code","1");
        map.put("msg","成功");
        return map;
    }
}
