package com.ading_keki.stealthy_striver.api;

import com.ading_keki.stealthy_striver.api.param.SsUserUpdateParam;
import com.ading_keki.stealthy_striver.api.vo.SsUserInfoVo;
import com.ading_keki.stealthy_striver.config.annotation.TokenToSsUser;
import com.ading_keki.stealthy_striver.pojo.SsUser;
import com.ading_keki.stealthy_striver.service.SsUserService;
import com.ading_keki.stealthy_striver.util.BeanUtil;
import com.ading_keki.stealthy_striver.util.ResultJSON;
import com.ading_keki.stealthy_striver.util.ResultJSONGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 21:11
 * Author: medi
 */
@RestController
@Api(value = "v2", tags = "v2-个人信息模块")
@RequestMapping("/api/v2")
public class SsUserPersonalAPI {
    @Resource
    private SsUserService ssUserService;

    @GetMapping("/user/info")
    @ApiOperation(value = "获取用户信息", notes = "")
    public ResultJSON<SsUserInfoVo> getUserDetail(@TokenToSsUser SsUser loggedUser) {
        SsUserInfoVo ssUserInfoVo = new SsUserInfoVo();
        BeanUtil.copyProperties(loggedUser, ssUserInfoVo);
        return ResultJSONGenerator.getSuccessResultJSON(ssUserInfoVo);
    }


    @PutMapping("/user/info")
    @ApiOperation(value = "修改个人信息", notes = "")
    public ResultJSON updateInfo(@RequestBody @ApiParam("用户信息") SsUserUpdateParam userUpdateParam, @TokenToSsUser SsUser loggedUser) {
        System.out.println("SsUserPersonalAPI -> " + loggedUser.getUserId());
        Boolean flag = ssUserService.updateUserInfo(userUpdateParam, loggedUser.getUserId());
        if (flag) {
            //返回成功
            ResultJSON result = ResultJSONGenerator.getSuccessResultJSON("修改成功");
            return result;
        } else {
            //返回失败
            ResultJSON result = ResultJSONGenerator.getFailResultJSON("修改失败");
            return result;
        }
    }

}
