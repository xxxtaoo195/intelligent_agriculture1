package com.topwulian.dao;
import com.topwulian.model.DeviceCon;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface DeviceConDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_device_con(deviceCon, deviceName, farmId, userId, status, optime, createtime, createuser) values(#{deviceCon}, #{deviceName}, #{farmId}, #{userId}, #{status}, #{optime}, now(), #{createuser})")
    int save(DeviceCon device);


    /**
     * 通过基地ID 用户id 名称查询是否存在
     */
    @Select("select * from t_device_con where farmId=#{farmId} and userId = #{userId} and deviceCon=#{deviceCon}")
    List<DeviceCon> getByInfo(@Param("farmId") Integer farmId, @Param("userId") Integer userId, @Param("deviceCon") String device_con);

    @Update("update t_device_con set status = #{status} , optime = #{optime} where deviceCon = #{deviceCon} and farmId=#{farmId} and userId = #{userId} ")
    int update(DeviceCon deviceCon);

    List<DeviceCon> listDeviceCon(@Param("monpointsid") String monpointsid);

    @Select("select * from t_device_con t where t.id = #{id}")
    DeviceCon getById(Long id);

}
