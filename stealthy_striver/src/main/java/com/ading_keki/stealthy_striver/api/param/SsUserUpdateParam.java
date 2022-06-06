package com.ading_keki.stealthy_striver.api.param;

import lombok.Data;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 19:07
 * Author: medi
 */

@Data
public class SsUserUpdateParam {
    private String nickName;

    private String password;

    private String phone;

    private String email;

    private String introduceSign;

    private Byte sex;

    private String headImg;

}
