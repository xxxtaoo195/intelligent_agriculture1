package com.topwulian.dao;

import com.topwulian.model.DeviceThreshold;
import com.topwulian.model.DeviceType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DeviceThresholdDao {

    @Select("select * from t_device_threshold_info t where t.id = #{id}")
    DeviceThreshold getById(Long id);

    @Select("select * from t_device_threshold_info t where t.deviceId = #{deviceId}")
    List<DeviceThreshold>  selectByDidTypeInfo(String deviceId);


    @Delete("delete from t_device_threshold_info where id = #{id}")
    int delete(Long id);

    int update(DeviceThreshold deviceType);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_device_threshold_info(minthreshold,maxthreshold,value, mid, deviceId, remindDate, status,remark) values(#{minthreshold}, #{maxthreshold},#{value},#{mid}, #{deviceId}, #{remindDate}, #{status},#{remark})")
    int save(DeviceThreshold deviceType);

    
    int count(@Param("params") Map<String, Object> params);

    List<DeviceThreshold> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Update("update t_device_threshold_info set status = '2' where mid = #{mid} and deviceId = #{deviceId}")
    int updateThresholdInfo(DeviceThreshold deviceType);


    List<DeviceThreshold> listThresholdInfo(@Param("farmId") String farmId,@Param("userId") String userId);


}
