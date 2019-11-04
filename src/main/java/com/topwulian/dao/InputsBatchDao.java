package com.topwulian.dao;

import com.topwulian.model.InputsBatch;
import com.topwulian.model.TTask;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface InputsBatchDao {


    @Insert("insert into t_inputs_batch(batchId, inputsId, stockNumber,farmId,fid) values(#{batchId}, #{inputsId}, #{stockNumber},#{farmId},#{fid})")
    int save(InputsBatch InputsBatch);


    @Select("select * from t_inputs_batch where farmId = #{farmId}  and inputsId = #{inputsId} ")
    List<InputsBatch> getBatchList(@Param("farmId") Long farmId, @Param("inputsId") Long inputsId);


    @Update("update t_inputs_batch set stockNumber = #{stockNumber} where batchId = #{batchId}")
    int updateInfo(InputsBatch InputsBatch);

    @Select("select b.*,t.inputsName,f.factoryName from t_inputs_batch b inner join t_inputs_type t on b.inputsId = t.inputsId inner join t_inputs_factory f on b.fid = f.id where b.farmId = #{farmId}")
    List<InputsBatch> getBatchListInfo(@Param("farmId") Long farmId);

    @Select("select * from t_inputs_batch t where t.batchId = #{batchId}")
    InputsBatch getById(String batchId);


}
