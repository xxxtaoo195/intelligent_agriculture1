package com.topwulian.dao;

import com.topwulian.model.Monpoints;
import com.topwulian.model.MonpointsDevice;
import com.topwulian.model.MonpointsDeviceCon;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface MonpointsDeviceDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_monpoints_device(mid,deviceid) values(#{mid}, #{deviceid})")
    int saveMonDevice(MonpointsDevice device);

    @Select("select * from t_monpoints_device where mid = #{mid} and deviceid = #{deviceid}")
    List<MonpointsDevice> getByDeviceId(@Param("mid") String mid,@Param("deviceid") String deviceid);


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_monpoints_device_con(mid,deviceid) values(#{mid}, #{deviceid})")
    int saveMonDeviceCon(MonpointsDeviceCon device);


    @Select("select * from t_monpoints_device_con where mid = #{mid} and deviceid = #{deviceid}")
    List<MonpointsDeviceCon> getByDeviceConId(@Param("mid") String mid,@Param("deviceid") String deviceid);




}

