package com.topwulian.dao;

import com.topwulian.model.SysStTokenInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysStTokenInfoDao {


    /**
     * 添加
     * @param sysStTokenInfo
     * @return
     */
    @Insert("insert into sys_st_tokenInfo(uuid, username,password,token, areaid, createtime) values(#{uuid}, #{password},#{token},#{token}, #{areaid}, now())")
    int save(SysStTokenInfo sysStTokenInfo);

    /**
     * 删除所有信息
     * @return
     */
    @Delete("delete from sys_st_tokenInfo")
    int deleteInfo();

    /**
     * 查询所有信息
     * @return
     */
    @Select("select * from sys_st_tokenInfo")
    List<SysStTokenInfo> getByTokenInfo();

    /**
     * 修改
     * @param   sysStTokenInfo
     * @return
     */
    @Update("update sys_st_tokenInfo set token=#{token} , createtime = now() where uuid=#{uuid}")
    int updateTokenInfo(SysStTokenInfo sysStTokenInfo);



}
