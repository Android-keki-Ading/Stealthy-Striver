package com.ading_keki.stealthy_striver.api.vo;

import lombok.Data;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 21:18
 * Author: medi
 */

@Data
public class SsUserInfoVo {
    private String nickName;

    private String password;

    private String phone;

    private String introduceSign;

    private byte sex;

    private String headImg;

}
