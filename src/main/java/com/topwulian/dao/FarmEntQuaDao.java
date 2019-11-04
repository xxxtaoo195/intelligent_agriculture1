package com.topwulian.dao;

import com.topwulian.model.Farm;
import com.topwulian.model.FarmEntQua;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FarmEntQuaDao {


    @Delete("delete from t_farm where farmid = #{farmid}")
    int deleteFramId(Long farmid);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_farm_entqua(farmid, imgURL,createTime) values(#{farmid}, #{imgURL},now())")
    int save(FarmEntQua farmEntQua);


    @Select("select * from t_farm_entqua where farmid=#{farmid}")
    List<FarmEntQua> getImgInfoByFramID(@Param("farmid") Long farmid);




}
