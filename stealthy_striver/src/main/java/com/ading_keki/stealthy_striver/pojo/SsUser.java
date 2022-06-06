package com.ading_keki.stealthy_striver.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 17:09
 * Author: medi
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SsUser {
    private int userId;

    private String nickName;

    private String password;

    private String phone;

    private String email;

    private Byte sex;

    private String introduceSign;

    private String headImg;

    private Byte isDeleted;

    @Override
    public String toString() {
        return "SsUser{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", introduceSign='" + introduceSign + '\'' +
                ", headImg='" + headImg + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
