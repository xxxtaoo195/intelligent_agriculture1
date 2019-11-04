package com.topwulian.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.topwulian.model.Producter;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProducterDao {

    @Select("select * from t_producter t where t.id = #{id}")
    Producter getById(Long id);

    @Delete("delete from t_producter where id = #{id}")
    int delete(Long id);

    int update(Producter producter);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_producter(isactive, username, phone, wechat, usercard, createdate, sys_user_id, producerTypeId, password) values(#{isactive}, #{username}, #{phone}, #{wechat}, #{usercard}, #{createdate}, #{sysUserId}, #{producerTypeId}, #{password})")
    int save(Producter producter);
    
    int count(@Param("params") Map<String, Object> params);

    List<Producter> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
