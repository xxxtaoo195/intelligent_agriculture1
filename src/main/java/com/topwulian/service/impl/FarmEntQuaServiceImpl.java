package com.topwulian.service.impl;


import com.topwulian.dao.FarmEntQuaDao;
import com.topwulian.model.Farm;
import com.topwulian.model.FarmEntQua;
import com.topwulian.service.FarmEntQuaService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmEntQuaServiceImpl implements FarmEntQuaService {

    @Autowired
    private FarmEntQuaDao FarmEntQuaDao;

    @Override
    public int deleteFramId(Long farmid) {
        return FarmEntQuaDao.deleteFramId(farmid);
    }

    @Override
    public int save(FarmEntQua farmEntQua) {
        return FarmEntQuaDao.save(farmEntQua);
    }

    @Override
    public List<FarmEntQua> getImgInfoByFramID(Long farmid) {
        return FarmEntQuaDao.getImgInfoByFramID(farmid);
    }
}
