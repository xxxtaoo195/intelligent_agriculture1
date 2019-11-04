package com.topwulian.dao;

import com.topwulian.model.Monpoints;
import com.topwulian.model.MonpointsDevice;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface MonpointsDao {

    @Select("select * from t_monpoints t where t.id = #{id}")
    Monpoints getById(Long id);

    @Delete("delete from t_monpoints where id = #{id}")
    int delete(Long id);

    int update(Monpoints device);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_monpoints(monpointsName,monpointsAddress,monpointsid,monpointstype,monpointsDeviceName,userid,farmid,createtime,createuser) values(#{monpointsName}, #{monpointsAddress}, #{monpointsid}, #{monpointstype},#{monpointsDeviceName}, #{userid}, #{farmid},now(), #{createuser})")
    int save(Monpoints device);

    List<Monpoints> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

    int count(@Param("params") Map<String, Object> params);


    @Select("select * from t_monpoints t where t.userid = #{userid} and t.farmid = #{farmid} and t.monpointstype = #{monpointstype}")
    List<Monpoints> getByMonInfo(@Param("userid") String userid,@Param("farmid") String farmid,@Param("monpointstype") String monpointstype);

    @Select("select * from t_monpoints t left join t_monpoints_device d on t.id = d.mid  where t.farmid = #{farmid} ")
    List<Monpoints> getListMonDeviceInfo(@Param("farmid") String farmid);

    @Select("select * from t_monpoints where userid = #{userid} and farmid = #{farmid}")
    List<Monpoints> getByUserAndFarmInfo(@Param("userid") String userid,@Param("farmid") String farmid);


    @Select("select * from t_monpoints t where t.monpointsid = #{monpointsid}")
    List<Monpoints> getByMid(@Param("monpointsid") String monpointsid);



}

