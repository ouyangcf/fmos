package com.unicom.fmos.dto;

/**
 * Created by zhaojb on 2016/12/6.
 */
public class Result {

    private int retCode;//返回码	1：成功，-1：失败
    private String errCode;//错误码	根据业务系统具体定义
    private String errDesc;//错误消息
    private String map;//如果成功，则返回相关数据封装在map之中返回

    public Result() {
    }

    public Result(int retCode, String errCode, String errDesc, String map) {
        this.retCode = retCode;
        this.errCode = errCode;
        this.errDesc = errDesc;
        this.map = map;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
