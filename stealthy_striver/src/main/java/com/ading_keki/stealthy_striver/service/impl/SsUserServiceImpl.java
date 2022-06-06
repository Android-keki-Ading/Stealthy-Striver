package com.ading_keki.stealthy_striver.service.impl;

import com.ading_keki.stealthy_striver.api.param.SsUserUpdateParam;
import com.ading_keki.stealthy_striver.common.Constants;
import com.ading_keki.stealthy_striver.common.ServiceResultEnum;
import com.ading_keki.stealthy_striver.config.ExceptionConfig;
import com.ading_keki.stealthy_striver.mapper.SsRecordMapper;
import com.ading_keki.stealthy_striver.mapper.SsUserMapper;
import com.ading_keki.stealthy_striver.mapper.SsUserTokenMapper;
import com.ading_keki.stealthy_striver.pojo.SsUser;
import com.ading_keki.stealthy_striver.pojo.SsUserToken;
import com.ading_keki.stealthy_striver.service.SsUserService;
import com.ading_keki.stealthy_striver.util.CheckUtil;
import com.ading_keki.stealthy_striver.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 18:03
 * Author: medi
 */

@Service
public class SsUserServiceImpl implements SsUserService {
    @Autowired
    private SsUserMapper ssUserMapper;

    @Autowired
    private SsUserTokenMapper ssUserTokenMapper;

    @Autowired
    private SsRecordMapper ssRecordMapper;

    @Override
    public String login(String logName, String logPassword) {
        SsUser loginUser = ssUserMapper.selectUserByLoginParams(logName, logPassword);
        if(loginUser != null){
//            新的token
            String token = TokenUtil.getToken(System.currentTimeMillis() + "", loginUser.getUserId());
            SsUserToken userToken = ssUserTokenMapper.selectTokenById(loginUser.getUserId());
            Date now = new Date();
            Date expiredTime = TokenUtil.getExpiredTime(now);
            if(userToken == null){  // 插入新的token
                userToken = new SsUserToken(loginUser.getUserId(), token, now, expiredTime);
                //新增一条token数据
                if (ssUserTokenMapper.insertSelective(userToken) > 0) {
                    //新增成功后返回
                    return token;
                }
            }else { // 更新token
                userToken.setToken(token);
                userToken.setUpdateTime(now);
                userToken.setExpireTime(expiredTime);
                //更新
                if (ssUserTokenMapper.updateByPrimaryKeySelective(userToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }
        }
        return ServiceResultEnum.LOGIN_ERROR.getDescription();
    }

    @Override
    public String register(String regName, String regPassword) {
        SsUser registerUser = new SsUser();
//        System.out.println(CheckUtil.isLogName(regName));
        if(!CheckUtil.isLogName(regName) || regName == null){ // 手机号或者邮箱格式不正确
            System.out.println("SsUserServiceImpl -> ");
            return ServiceResultEnum.LOGIN_NAME_IS_INVALID.getDescription();
        }
        registerUser.setPassword(regPassword);

        if(CheckUtil.isPhone(regName)) { // 用户以手机号注册
            System.out.println("ss" + ssUserMapper);
            if(ssUserMapper.selectUserByPhone(regName) != null){ // 该手机号已经被注册使用
                System.out.println(ssUserMapper);
                return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getDescription();
            }else{ // 手机号可以成功注册
                registerUser.setPhone(regName);
                registerUser.setEmail("");
            }
        }else { // 用户以邮箱注册
            if(ssUserMapper.selectUserByEmail(regName) != null){ // 该邮箱已经被注册使用
                return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getDescription();
            }else { // 邮箱可以成功注册
                registerUser.setEmail(regName);
                registerUser.setPhone("");
            }
        }
        registerUser.setNickName(Constants.DEFAULT_NICK_NAME);
        registerUser.setHeadImg(Constants.DEFAULT_HEAD_IMG);
        registerUser.setIntroduceSign(Constants.DEFAULT_INTRODUCE_SIGN);
        System.out.println(registerUser);
        if(ssUserMapper.insertUser(registerUser) > 0){
            return ServiceResultEnum.REGISTER_SUCCESS.getDescription();
        }
        return ServiceResultEnum.DB_ERROR.getDescription();
    }

    @Override
    public Boolean updateUserInfo(SsUserUpdateParam user, int userId) {
        SsUser ssUser = ssUserMapper.selectUserById(userId);
        System.out.println(ssUser);
        if (ssUser == null) {
            ExceptionConfig.fail(ServiceResultEnum.DATA_NOT_EXIST.getDescription());
        }

        if(user.getPassword().equals("") || user.getPassword() == null){
            ssUser.setPassword(ssUser.getPassword());
        }else {
            ssUser.setPassword(user.getPassword());
        }

        if(user.getPhone().equals("") || user.getPhone() == null){
            ssUser.setPhone(ssUser.getPhone());
        }else {
            ssUser.setPhone(user.getPhone());
        }

        if (user.getEmail().equals("") || user.getEmail() == null){
            ssUser.setEmail(ssUser.getEmail());
        }else{
            ssUser.setEmail(user.getEmail());
        }

        if(user.getHeadImg().equals("") || user.getHeadImg() == null){
            ssUser.setHeadImg(ssUser.getHeadImg());
        }else {
            ssUser.setHeadImg(user.getHeadImg());
        }

        if(user.getNickName().equals("") || user.getNickName() == null){
            ssUser.setNickName(ssUser.getNickName());
        }else {
            ssUser.setNickName(user.getNickName());
        }

        ssUser.setIntroduceSign(user.getIntroduceSign());
        ssUser.setSex(user.getSex());

        return ssUserMapper.updateUser(ssUser) > 0;

    }

//    登出
    @Override
    public Boolean logout(int userId) {
        return ssUserTokenMapper.deleteTokenById(userId) > 0;
    }

//    注销
    @Override
    public Boolean logoff(int userId) {
        int a = ssUserTokenMapper.deleteTokenById(userId); // 销毁token
//        int b = ssRecordMapper.deleteRecordById(userId); // 删除用户的偷博记录
        int c = ssUserMapper.deleteUserById(userId); // 删除用户记录
        if(a > 0 && c > 0){
            return true;
        }else{
            return false;
        }
    }

}
