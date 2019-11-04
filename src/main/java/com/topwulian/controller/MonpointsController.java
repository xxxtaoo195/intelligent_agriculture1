package com.topwulian.controller;

import com.topwulian.dao.DeviceDao;
import com.topwulian.dao.FarmDao;
import com.topwulian.model.*;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableHandler.CountHandler;
import com.topwulian.page.table.PageTableHandler.ListHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.service.DeviceService;
import com.topwulian.service.MonpointsService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monpoints")
public class MonpointsController {



    @Autowired
    private MonpointsService monpointsService;

    @GetMapping("/getInit")
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return monpointsService.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<Monpoints> list(PageTableRequest request) {
                return monpointsService.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }



    @PutMapping
    @ApiOperation(value = "修改")
    public Monpoints update(@RequestBody Monpoints monpoints) {
        monpointsService.update(monpoints);
        return monpoints;
    }


}
