package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

public class InfoUpdateResponse {

    @SerializedName("data")
    private String data;
    @SerializedName("message")
    private String msg;
    @SerializedName("resultCode")
    private String code;

    public InfoUpdateResponse(String data, String msg, String code) {
        this.data = data;
        this.msg = msg;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "InfoUpdate{" +
                "data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

