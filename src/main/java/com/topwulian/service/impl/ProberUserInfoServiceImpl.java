package com.topwulian.service.impl;

import com.topwulian.dao.ProberUserInfoDao;
import com.topwulian.model.ProberUserInfo;
import com.topwulian.service.ProberUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProberUserInfoServiceImpl implements ProberUserInfoService {

    @Autowired
    private ProberUserInfoDao proberUserInfoDao;

    @Override
    public int update(ProberUserInfo info) {
        return proberUserInfoDao.update(info);
    }

    @Override
    public List<ProberUserInfo> getByUserInfo(String username) {
        return proberUserInfoDao.getByUserInfo(username);
    }

    @Override
    public List<ProberUserInfo> getByTokenInfo(String token) {
        return proberUserInfoDao.getByTokenInfo(token);
    }
}
