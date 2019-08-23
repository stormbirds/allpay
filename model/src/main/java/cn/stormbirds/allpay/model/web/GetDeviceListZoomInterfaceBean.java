package cn.stormbirds.allpay.model.web;

import java.util.ArrayList;

public class GetDeviceListZoomInterfaceBean {
    private String msg;
    private int code;
    private ArrayList<CustomFindModel> data;


    public ArrayList<CustomFindModel> getData() {
        return data;
    }

    public void setData(ArrayList<CustomFindModel> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
