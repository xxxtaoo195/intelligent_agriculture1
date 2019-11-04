package com.topwulian.dao;

import com.topwulian.model.ProberDeviceInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProberDeviceInfoDao {

    @Select("select count(1) from prober_device_info where farmid=#{params.farm_id}")
    int count(@Param("params") Map<String, Object> params);

    @Select("select * from prober_device_info where farmid=#{params.farm_id} limit #{offset},#{limit}")
    List<ProberDeviceInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Insert("insert into prober_device_info(CheckID, testItem, farmid, sampleName, detDate, detResult, testMethod, testBasis, testValueCom, testCom, inspector, auditors, referValue, createtime, createuser, updatetime, updateuser) values( #{CheckID}, #{testItem}, #{farmid}, #{sampleName}, #{detDate}, #{detResult}, #{testMethod}, #{testBasis}, #{testValueCom}, #{testCom}, #{inspector}, #{auditors}, #{referValue}, now(), #{createuser}, now(), #{updateuser})")
    int save(ProberDeviceInfo proberDeviceInfo);
}
