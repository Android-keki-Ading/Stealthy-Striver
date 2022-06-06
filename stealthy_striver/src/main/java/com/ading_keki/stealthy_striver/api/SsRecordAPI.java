package com.ading_keki.stealthy_striver.api;

import io.swagger.annotations.*;
import com.ading_keki.stealthy_striver.api.vo.SsUserInfoVo;
import com.ading_keki.stealthy_striver.config.annotation.TokenToSsUser;
import com.ading_keki.stealthy_striver.pojo.SsUser;
import com.ading_keki.stealthy_striver.util.ResultJSON;
import com.ading_keki.stealthy_striver.util.ResultJSONGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 21:10
 * Author: medi
 */
@RestController
@Api(value = "v3", tags = "v3-偷博历程模块")
@RequestMapping("/api/v3")
public class SsRecordAPI {
    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public ResultJSON<SsUserInfoVo> getUserDetail(@TokenToSsUser SsUser loggedUser) {
        return ResultJSONGenerator.getSuccessResultJSON();
    }



}
