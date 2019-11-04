package com.topwulian.dao;


import com.topwulian.model.AliyunDevice;
import com.topwulian.model.AliyunWlDeivceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AliyunWlDeviceInfoDao {

    @Select("select w.* from aliyun_wl_device_info w INNER JOIN (select deviceid,devicewlid from aliyun_device_wl where deviceid = #{deviceid} ) t on  w.id = t.devicewlid where w.device_type = #{type}")
    List<Map<String,Object>> getByDeviceId(@Param("deviceid") String  deviceid,@Param("type") String  type);
}
