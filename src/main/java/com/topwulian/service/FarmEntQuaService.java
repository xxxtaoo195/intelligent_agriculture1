package com.topwulian.service;

import com.topwulian.model.Farm;
import com.topwulian.model.FarmEntQua;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FarmEntQuaService {

    int deleteFramId(Long farmid);

    int save(FarmEntQua farmEntQua);

    List<FarmEntQua> getImgInfoByFramID(Long farmid);
}
