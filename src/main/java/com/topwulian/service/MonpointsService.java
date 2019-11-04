package com.topwulian.service;

import com.topwulian.model.Monpoints;

import java.util.List;
import java.util.Map;

public interface MonpointsService {

    Monpoints getById(Long id);


    int delete(Long id);

    int update(Monpoints device);

    int save(Monpoints device);

    List<Monpoints> list(Map<String, Object> params, Integer offset, Integer limit);

    int count(Map<String, Object> params);
}
