package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    //使用@SerializedName注解，可以将自定义的字段名与json数据里面的字段对应起来；
    @SerializedName("resultCode")
    private String  code;

    @SerializedName("data")
    private String data;

    @SerializedName("message")
    private String msg;

    public RegisterResponse(String code, String data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

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
}
