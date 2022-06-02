package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class UserRegister{

    /**
     * 电子邮箱
     */
    @SerializedName("loginName")
    private String eMail;

    /**
     * 密码
     */
    @SerializedName("password")
    private String password;

    public UserRegister(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }


    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
