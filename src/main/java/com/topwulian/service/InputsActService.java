package com.topwulian.service;


import com.topwulian.model.InputsAct;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

/**
 * @Author: szz
 * @Date: 2018/12/26 下午2:01
 * @Version 1.0
 */
public interface InputsActService{

    int save(InputsAct InputsAct);

    int count(Map<String, Object> params);

    List<InputsAct> list(Map<String, Object> params, Integer offset, Integer limit);


    int actcount(Map<String, Object> params);

    List<InputsAct> actlist(Map<String, Object> params,Integer offset, Integer limit);

    List<InputsAct> getByBatchIdInfo(String batchId);

    List<InputsAct> getActInfoList();
}
