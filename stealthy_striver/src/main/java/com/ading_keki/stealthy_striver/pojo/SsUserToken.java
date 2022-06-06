package com.ading_keki.stealthy_striver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 17:15
 * Author: medi
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsUserToken {
    private int userId;

    private String token;

    private Date updateTime;

    private Date expireTime;

    @Override
    public String toString() {
        return "SsUserToken{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", updateTime=" + updateTime +
                ", expireTime=" + expireTime +
                '}';
    }
}
