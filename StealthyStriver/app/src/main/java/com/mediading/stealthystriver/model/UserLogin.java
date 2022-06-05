package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

public class UserLogin {

    /**
     * 登录名
     */
    @SerializedName("logName")
    private String loginName;

    /**
     * 密码
     */
    @SerializedName("logPassword")
    private String password;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLogin(String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }
}
