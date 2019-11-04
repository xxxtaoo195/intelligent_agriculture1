package com.topwulian.service;

import com.topwulian.model.ProberUserInfo;

import java.util.List;


public interface ProberUserInfoService {

    int update(ProberUserInfo info);

    List<ProberUserInfo> getByUserInfo(String username);

    List<ProberUserInfo> getByTokenInfo(String token);
}
