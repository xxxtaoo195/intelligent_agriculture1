package com.topwulian.dao;

import com.topwulian.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface InputsOutDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inputs_out(batchId, outStockNumber,outStockDate,username,remarks,makeArea,makeMethod,makeMassif)values(#{batchId}, #{outStockNumber},#{outStockDate},#{username},#{remarks},#{makeArea},#{makeMethod},#{makeMassif})")
    int save(InputsOut inputsOut);


    //k入库
    int count(@Param("params") Map<String, Object> params);

    List<InputsOut> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
