package com.ading_keki.stealthy_striver.api;

import com.ading_keki.stealthy_striver.api.param.SsUserLoginParam;
import com.ading_keki.stealthy_striver.api.param.SsUserRegisterParam;
import com.ading_keki.stealthy_striver.common.Constants;
import com.ading_keki.stealthy_striver.common.ServiceResultEnum;
import com.ading_keki.stealthy_striver.config.annotation.TokenToSsUser;
import com.ading_keki.stealthy_striver.pojo.SsUser;
import com.ading_keki.stealthy_striver.service.SsUserService;
import com.ading_keki.stealthy_striver.util.ResultJSON;
import com.ading_keki.stealthy_striver.util.ResultJSONGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 18:10
 * Author: medi
 */

@RestController
@Api(value = "v1", tags = "v1-登录/注册模块")
@RequestMapping("/api/v1")
public class SsUserAPI {
    @Resource
    private SsUserService ssUserService;
    private static final Logger logger = LoggerFactory.getLogger(SsUserAPI.class);

    @PostMapping("/user/login")
    @ApiOperation(value = "登录接口", notes = "返回token")
    public ResultJSON<String> login(@RequestBody @Valid SsUserLoginParam userLoginParam) {
        String loginResult = ssUserService.login(userLoginParam.getLogName(), userLoginParam.getLogPassword());
        logger.info("login api,loginName={},loginResult={}", userLoginParam.getLogName(), loginResult);

        if(!loginResult.isEmpty() && loginResult.length()==Constants.TOKEN_LENGTH){
            ResultJSON resultJSON = ResultJSONGenerator.getSuccessResultJSON("登录成功");
//            传递token
            resultJSON.setData(loginResult);
            return resultJSON;
        }

//        登录失败
        return ResultJSONGenerator.getFailResultJSON("登录失败");
    }

    @PostMapping("/user/register")
    @ApiOperation(value = "用户注册", notes = "")
    public ResultJSON register(@RequestBody @Valid SsUserRegisterParam userRegisterParam) {
        String registerResult = ssUserService.register(userRegisterParam.getRegName(), userRegisterParam.getRegPassword());
        logger.info("register api,loginName={},loginResult={}", userRegisterParam.getRegName(), registerResult);

//        注册成功
        if(ServiceResultEnum.REGISTER_SUCCESS.getDescription().equals(registerResult)){
            return ResultJSONGenerator.getSuccessResultJSON("注册成功！");
        }
        return ResultJSONGenerator.getFailResultJSON("注册失败！");
    }

    @PostMapping("/user/logout")
    @ApiOperation(value = "登出接口", notes = "清除token")
    public ResultJSON<String> logout(@TokenToSsUser SsUser loggedUser) {
        Boolean logoutResult = ssUserService.logout(loggedUser.getUserId());
        logger.info("logout api,loginMallUser={}", loggedUser.getUserId());

        //登出成功
        if (logoutResult) {
            return ResultJSONGenerator.getSuccessResultJSON("退出成功");
        }
        //登出失败
        return ResultJSONGenerator.getFailResultJSON("退出失败");
    }

    @PostMapping("/user/logoff")
    @ApiOperation(value = "注销接口", notes = "删除与用户相关的所有记录")
    public ResultJSON<String> logoff(@TokenToSsUser SsUser loggedUser) {
        Boolean logoffResult = ssUserService.logoff(loggedUser.getUserId());
        logger.info("logout api,loginMallUser={}", loggedUser.getUserId());

        //登出成功
        if (logoffResult) {
            return ResultJSONGenerator.getSuccessResultJSON("注销成功");
        }
        //登出失败
        return ResultJSONGenerator.getFailResultJSON("注销失败");
    }

}
