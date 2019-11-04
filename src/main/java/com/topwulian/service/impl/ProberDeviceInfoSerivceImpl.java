package com.topwulian.service.impl;

import com.topwulian.dao.ProberDeviceInfoDao;
import com.topwulian.model.ProberDeviceInfo;
import com.topwulian.service.ProberDeviceInfoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ProberDeviceInfoSerivceImpl implements ProberDeviceInfoSerivce {

    @Autowired
    private ProberDeviceInfoDao proberDeviceInfoDao;

    @Override
    public int count(Map<String, Object> params) {
        return proberDeviceInfoDao.count(params);
    }

    @Override
    public List<ProberDeviceInfo> list(Map<String, Object> params, Integer offset, Integer limit) {
        return proberDeviceInfoDao.list(params,offset,limit);
    }

    @Override
    public int save(ProberDeviceInfo proberDeviceInfo) {
        return proberDeviceInfoDao.save(proberDeviceInfo);
    }
}
