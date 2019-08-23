package cn.stormbirds.allpay.payorder.controller;

import api.CitconPayConfigStorage;
import api.CitconPayService;
import bean.CitconTransactionType;
import cn.stormbirds.payservice.common.bean.DefaultCurType;
import cn.stormbirds.payservice.common.bean.PayOrder;
import cn.stormbirds.payservice.common.http.HttpConfigStorage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * <p>
 * cn.stormbirds.allpay.payorder.controller
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/23 18:53
 */
@Api()
@Slf4j
@RestController(value = "/api/v1/citcon")
public class CitconController {

    private CitconPayService payService;
    @PostConstruct
    public void init(){
        CitconPayConfigStorage payConfigStorage = new CitconPayConfigStorage();
        payConfigStorage.setToken("793A551B989F4E529F113DB1FE8A0FC9");
        payConfigStorage.setNotifyUrl("https://www.baidu.com");
        payConfigStorage.setReturnUrl("https://www.baidu.com");

        //请求连接池配置
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        //最大连接数
        httpConfigStorage.setMaxTotal(20);
        //默认的每个路由的最大连接数
        httpConfigStorage.setDefaultMaxPerRoute(10);

        payService = new CitconPayService(payConfigStorage,httpConfigStorage);
    }

    @ApiOperation(value = "获取二维码")
    @GetMapping(value = "/getqr")
    public String getQr(@RequestParam BigDecimal price){
        PayOrder payOrder = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, System.currentTimeMillis()+"", CitconTransactionType.APP);
        payOrder.setCurType(DefaultCurType.USD);
        return payService.getQrPay( payOrder);
    }
}
