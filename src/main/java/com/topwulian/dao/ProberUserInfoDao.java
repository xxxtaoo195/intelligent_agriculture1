package com.topwulian.dao;

import com.topwulian.model.ProberUserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ProberUserInfoDao {



    @Update("update prober_user_info t set token = ${token} , tokendate = ${tokendate}")
    int update(ProberUserInfo info);

    @Select("select * from prober_user_info where username = #{username}")
    List<ProberUserInfo> getByUserInfo(@Param("username") String username);

    @Select("select * from prober_user_info where token = #{token}")
    List<ProberUserInfo> getByTokenInfo(@Param("token") String token);

}
