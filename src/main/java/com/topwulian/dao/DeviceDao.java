package com.topwulian.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.topwulian.model.Device;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeviceDao {

    @Select("select * from t_device t where t.id = #{id}")
    Device getById(Long id);

    @Delete("delete from t_device where id = #{id}")
    int delete(Long id);

    int update(Device device);
    
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_device(sn, name, typeId, location, image, installTime, state, standard, manufacturer, userId, remark, softwareVersion, thresholdMin, thresholdMax,farmId) values(#{sn}, #{name}, #{typeId}, #{location}, #{image}, #{installTime}, #{state}, #{standard}, #{manufacturer}, #{userId}, #{remark}, #{softwareVersion}, #{thresholdMin}, #{thresholdMax},#{farmId})")
    int save(Device device);
    
    int count(@Param("params") Map<String, Object> params);

    List<Device> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    @Select("select * from t_device where farmId=#{farmId}")
    List<Device> getByFarmId(@Param("farmId") Long farmId);


    /**
     * 通过基地ID 用户id 名称查询是否存在
     */
    @Select("select * from t_device where farmId=#{farmId} and userId = #{userId} and name=#{name}")
    List<Device> getByInfo(@Param("farmId") Integer farmId,@Param("userId") Integer userId,@Param("name") String name);

    /**
     * 添加
     * @param device
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "device.id")
    @Insert("insert into t_device(sn, name, typeId, location, image, installTime, state, standard, manufacturer, userId, remark, softwareVersion, thresholdMin, thresholdMax,farmId,createtime,updatetime,operator) values(#{device.sn}, #{device.name}, #{device.typeId}, #{device.location}, #{device.image}, #{device.installTime}, #{device.state}, #{device.standard}, #{device.manufacturer}, #{device.userId}, #{device.remark}, #{device.softwareVersion}, #{device.thresholdMin}, #{device.thresholdMax},#{device.farmId},now(),now(),#{device.operator})")
    int add(@Param("device") Device device);

    int countMonpoints(@Param("params") Map<String, Object> params);

    List<Device> listMonpoints(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);


    @Select("select d.* from t_monpoints_device m inner join t_device d on m.deviceid = d.id where m.mid = #{mid}")
    List<Device> getByMidDeviceInfo(@Param("mid") String mid);





}
