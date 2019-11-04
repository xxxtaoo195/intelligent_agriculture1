package com.topwulian.dao;


import com.topwulian.model.AliyunDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AliyunDeivceDao {

    @Select("select * from aliyun_device where deviceName = #{deviceName} and productKey = #{productKey}")
    List<AliyunDevice> getByNameAndKey(@Param("deviceName") String  deviceName, @Param("productKey") String productKey);


    @Select("select d.* from aliyun_device d inner join t_monpoints m on d.deviceName = m.monpointsid WHERE m.id = #{id}")
    List<AliyunDevice> getByDeviceInfoMid(@Param("id") String  id);


}
