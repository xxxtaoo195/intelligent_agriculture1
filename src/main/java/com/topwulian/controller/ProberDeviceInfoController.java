package com.topwulian.controller;


import com.topwulian.model.ProberDeviceInfo;
import com.topwulian.model.TProducter;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.service.ProberDeviceInfoSerivce;
import com.topwulian.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proberDevice")
public class ProberDeviceInfoController {



    @Autowired
    private ProberDeviceInfoSerivce proberDeviceInfoSerivce;


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
       return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                //获取基地id
                int farmId = UserUtil.getUserFarmId();
                Map<String,Object> map = new HashMap();
                map.put("farm_id",farmId);
                request.setParams(map);
                return proberDeviceInfoSerivce.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<ProberDeviceInfo> list(PageTableRequest request) {
                //获取基地id
                int farmId = UserUtil.getUserFarmId();
                Map<String,Object> map = new HashMap();
                map.put("farm_id",farmId);
                request.setParams(map);
                return proberDeviceInfoSerivce.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);


    }

}
