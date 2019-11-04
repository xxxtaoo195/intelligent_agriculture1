package com.topwulian.dao;

import com.topwulian.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface InputsStockDao {

    @Insert("insert into t_inputs_stock(batchId, purchaseNumber,purchaseDate,remainNumber,username,manuDate,supplier,remarks)values(#{batchId}, #{purchaseNumber},#{purchaseDate},#{remainNumber},#{username},#{manuDate},#{supplier},#{remarks})")
    int save(InputsStock InputsStock);

    @Update("update t_inputs_stock set remainNumber = #{remainNumber} where batchId = #{batchId}")
    int updateInfo(InputsStock InputsBatch);


    @Select("SELECT * FROM t_inputs_type")
    List<InputsType> listInputsType();

    @Select("select f.* from t_inputs_factory f inner join ( select * from t_inputs_factory_act where inputsId= #{inputsId} ) a on f.id = a.fid")
    List<InputsFactory> listInputsFactory(@Param("inputsId") Long inputsId);


    @Select("SELECT * FROM t_inputs_type where inputsId = #{inputsId}")
    InputsType getByTypeId(@Param("inputsId") Long inputsId);


    //k入库
    int count(@Param("params") Map<String, Object> params);

    List<InputsAct> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}
