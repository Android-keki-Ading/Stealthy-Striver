package com.ading_keki.stealthy_striver.mapper;

import com.ading_keki.stealthy_striver.pojo.SsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 17:17
 * Author: medi
 */

@Mapper
public interface SsUserMapper {
    int insertUser(SsUser user);

    int deleteUserById(@Param("userId") int id);

    SsUser selectUserById(@Param("userId") int id);

    SsUser selectUserByPhone(@Param("userPhone") String phone);

    SsUser selectUserByEmail(@Param("userEmail") String phone);

    SsUser selectUserByLoginParams(@Param("logName") String loginName, @Param("logPassword") String password);

    List<SsUser> queryAllUser();

//    根据不同的字段，更新用户信息（）
    int updateUser(SsUser user);

}