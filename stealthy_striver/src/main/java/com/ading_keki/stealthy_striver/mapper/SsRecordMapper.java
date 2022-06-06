package com.ading_keki.stealthy_striver.mapper;

import com.ading_keki.stealthy_striver.pojo.SsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 21:07
 * Author: medi
 */

@Mapper
public interface SsRecordMapper {
    int deleteRecordById(@Param("userId") int id);
}

