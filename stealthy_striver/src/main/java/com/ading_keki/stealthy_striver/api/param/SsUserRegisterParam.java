package com.ading_keki.stealthy_striver.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 18:59
 * Author: medi
 */
@Data
public class SsUserRegisterParam {
    @NotEmpty
    private String regName;

    @NotEmpty
    private String regPassword;

}
