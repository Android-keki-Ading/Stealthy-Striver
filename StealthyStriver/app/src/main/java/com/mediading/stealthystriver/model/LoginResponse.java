package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("resultCode")
    private String  code;

    @SerializedName("data")
    private String data;

    @SerializedName("message")
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginResponse(String code, String data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
