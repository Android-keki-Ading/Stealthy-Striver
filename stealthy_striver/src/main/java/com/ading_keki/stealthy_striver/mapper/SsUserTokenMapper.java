package com.ading_keki.stealthy_striver.mapper;

import com.ading_keki.stealthy_striver.pojo.SsUserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 18:13
 * Author: medi
 */

@Mapper
public interface SsUserTokenMapper {
    int deleteTokenById(@Param("userId") int id);

    int insert(SsUserToken record);

    int insertSelective(SsUserToken record);

    SsUserToken selectTokenById(@Param("userId") int id);

    SsUserToken selectByToken(@Param("token") String token);

    int updateByPrimaryKeySelective(SsUserToken record);

    int updateByPrimaryKey(SsUserToken record);
}
