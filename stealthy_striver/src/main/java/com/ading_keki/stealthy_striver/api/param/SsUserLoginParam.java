package com.ading_keki.stealthy_striver.api.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 18:30
 * Author: medi
 */

@Data
public class SsUserLoginParam {

    @NotEmpty
    private String logName;

    @NotEmpty
    private String logPassword;

}
