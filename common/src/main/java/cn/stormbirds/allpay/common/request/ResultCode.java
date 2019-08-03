package cn.stormbirds.allpay.common.request;

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
    SUCCESS(1, "成功",null),
    FAILURE(0, "失败",null);
    private int code;
    private String msg;
    private Object data;

    ResultCode(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultCode getSuccessCode(String msg, Object data){

        return SUCCESS.setValue(0,msg,data);

    }

    public static ResultCode getFailureCode(String msg, Object data){

        return FAILURE.setValue(0,msg,data);

    }

    private ResultCode setValue(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
