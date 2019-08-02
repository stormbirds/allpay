package cn.stormbirds.allpaymodel.request;

import org.springframework.http.HttpStatus;
import sun.security.provider.certpath.OCSPResponse;

/**
 *
 * <p> ResultCode.java
 * </p>
 * @author StormBirds Email：xbaojun@gmail.com
 * @since 2019/8/2 17:23
 *
 */
public enum ResultCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"),
    FAILURE(400, "失败");
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCode getSuccessCode(int code, String msg){
        if((code>>1)== HttpStatus.CONTINUE.value()){
            return SUCCESS.setValue(code,msg);
        }else{
            return FAILURE.setValue(code,msg);
        }
    }

    public static ResultCode getFailureCode(int code, String msg){
        if((code>>1)== HttpStatus.CONTINUE.value()){
            return SUCCESS.setValue(code,msg);
        }else{
            return FAILURE.setValue(code,msg);
        }
    }

    private ResultCode setValue(int code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
