package cn.stormbirds.allpay.common.base;


import cn.stormbirds.allpay.common.request.ResultCode;
import cn.stormbirds.allpay.common.request.ResultJson;

/**
 * <p>
 * cn.stormbirds.allpaycommon.base
 * </p>
 *
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/2 17:03
 */


public class BaseController {
    public ResultJson showJsonResult(boolean code, String msg, Object data){
        if(code){
            return ResultJson.ok(ResultCode.getSuccessCode(msg,data));
        }
        return ResultJson.failure(ResultCode.getFailureCode(msg,data));
    }
}
