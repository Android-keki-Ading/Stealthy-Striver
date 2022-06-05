package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {
    @SerializedName("data")
    private InfoResponse data;

    @SerializedName("message")
    private String msg;

    @SerializedName("resultCode")
    private String code;

    public UserInfoResponse(InfoResponse data, String msg, String code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public InfoResponse getData() {
        return data;
    }

    public void setData(InfoResponse data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
