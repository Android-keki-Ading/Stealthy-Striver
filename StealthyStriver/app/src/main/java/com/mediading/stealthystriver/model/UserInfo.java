package com.mediading.stealthystriver.model;

public class UserInfo {
    private String nickName;
    private String id;
    private String sign;
    private String sex;

    public UserInfo(String nickName, String id, String sign) {
        this.nickName = nickName;
        this.id = id;
        this.sign = sign;
        this.sex = "秘密";
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "nickName='" + nickName + '\'' +
                ", id='" + id + '\'' +
                ", sign='" + sign + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
