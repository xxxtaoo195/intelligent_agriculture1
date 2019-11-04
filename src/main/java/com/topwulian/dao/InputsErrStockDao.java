package com.topwulian.dao;

import com.topwulian.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface InputsErrStockDao {


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inputs_errstock(batchId, operatingNumber,operatingTime,type,supplier,username,remarks)values(#{batchId},#{operatingNumber},#{operatingTime},#{type},#{supplier},#{username},#{remarks})")
    int save(InputsErrStock inputsErrStock);


    //入库
    int count(@Param("params") Map<String, Object> params);

    List<InputsErrStock> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
