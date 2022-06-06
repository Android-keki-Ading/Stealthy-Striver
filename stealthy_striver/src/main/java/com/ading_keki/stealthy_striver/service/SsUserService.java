package com.ading_keki.stealthy_striver.service;

import com.ading_keki.stealthy_striver.api.param.SsUserUpdateParam;
import com.ading_keki.stealthy_striver.pojo.SsUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 18:02
 * Author: medi
 */

public interface SsUserService {
//    登录
    String login(String logName, String regPassword);

//    注册
    String register(String regName, String regPassword);

//    登出
    Boolean logout(int userId);

//    注销
    Boolean logoff(int userId);

//    修改信息
    Boolean updateUserInfo(SsUserUpdateParam user, int userId);
}
