package com.topwulian.dao;

import com.topwulian.model.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface InputsActDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inputs_act(farmId, inputsId, isToxicity, qualityMonth, inputsUnit,fid) values(#{farmId}, #{inputsId}, #{isToxicity},  #{qualityMonth}, #{inputsUnit}, #{fid})")
    int save(InputsAct InputsAct);


    //批次显示
    int count(@Param("params") Map<String, Object> params);

    List<InputsAct> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);


    //台账记录
    int actcount(@Param("params") Map<String, Object> params);
    List<InputsAct> actlist(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);




    @Options(useGeneratedKeys = true, keyProperty = "inputsId")
    @Insert("insert into t_inputs_type(inputsId, inputsName, inputsType) values(#{inputsId}, #{inputsName}, #{inputsType})")
    int saveType(InputsType inputsType);

    @Select("select * from t_inputs_type where inputsName=#{inputsName}")
    List<InputsType> getByNameTypes(@Param("inputsName") String inputsName);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inputs_factory(factoryName, factoryLicense) values(#{factoryName}, #{factoryLicense})")
    int saveFactory(InputsFactory inputsFactory);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_inputs_factory_act(fid, inputsId) values(#{fid}, #{inputsId})")
    int saveFactoryAct(InputsFactoryAct inputsFactoryAct);


    List<InputsAct> getByBatchIdInfo(@Param("batchId") String batchId);

    List<InputsAct> getActInfoList();










}
