package com.mediading.stealthystriver.model;

import com.google.gson.annotations.SerializedName;

public class InfoResponse {
    @SerializedName("email")
    private String email;

    @SerializedName("nickName")
    private String nickName;

    @SerializedName("password")
    private String password;

    @SerializedName("phone")
    private String phone;

    @SerializedName("introduceSign")
    private String sign;

    @SerializedName("sex")
    private byte sex;

    @SerializedName("headImg")
    private String headImg;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InfoResponse(String email, String nickName, String password, String phone, String sign, byte sex, String headImg) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.phone = phone;
        this.sign = sign;
        this.sex = sex;
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sign='" + sign + '\'' +
                ", sex=" + sex +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
