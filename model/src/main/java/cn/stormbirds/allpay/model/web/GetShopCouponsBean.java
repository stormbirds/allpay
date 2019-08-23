package cn.stormbirds.allpay.model.web;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * cn.stormbirds.allpay.model.web
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/16 10:39
 */

@Data
public class GetShopCouponsBean implements Serializable {
    private String msg;
    private int code;
    private List<ShopCoupon> data;
}
