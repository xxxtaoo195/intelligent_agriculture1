package com.topwulian.service;

import com.topwulian.model.ProberDeviceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ProberDeviceInfoSerivce {

    int count(Map<String, Object> params);

    List<ProberDeviceInfo> list(Map<String, Object> params, Integer offset, Integer limit);

    int save(ProberDeviceInfo proberDeviceInfo);


}
