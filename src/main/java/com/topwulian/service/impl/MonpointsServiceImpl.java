package com.topwulian.service.impl;

import com.topwulian.dao.MonpointsDao;
import com.topwulian.model.Monpoints;
import com.topwulian.service.MonpointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class MonpointsServiceImpl implements MonpointsService {

    @Autowired
    private MonpointsDao monpointsDao;

    @Override
    public Monpoints getById(Long id) {
        return monpointsDao.getById(id);
    }

    @Override
    public int delete(Long id) {
        return monpointsDao.delete(id);
    }

    @Override
    public int update(Monpoints device) {
        return monpointsDao.update(device);
    }

    @Override
    public int save(Monpoints device) {
        return monpointsDao.save(device);
    }

    @Override
    public List<Monpoints> list(Map<String, Object> params, Integer offset, Integer limit) {
        return monpointsDao.list(params,offset,limit);
    }

    @Override
    public int count(Map<String, Object> params) {
        return monpointsDao.count(params);
    }
}
