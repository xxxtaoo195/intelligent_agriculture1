package com.topwulian.controller;

import com.topwulian.dao.DeviceThresholdDao;
import com.topwulian.dao.DeviceTypeDao;
import com.topwulian.dao.FarmDao;
import com.topwulian.dao.UserDao;
import com.topwulian.model.*;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableHandler.CountHandler;
import com.topwulian.page.table.PageTableHandler.ListHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/thresholdInfo")
public class DeviceThresholdController {

    @Autowired
    private DeviceTypeDao deviceTypeDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private DeviceThresholdDao deviceThresholdDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public DeviceType save(@RequestBody DeviceType deviceType) {
        deviceTypeDao.save(deviceType);
        return deviceType;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public DeviceType get(@PathVariable Long id) {
        return deviceTypeDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public DeviceType update(@RequestBody DeviceType deviceType) {
        deviceTypeDao.update(deviceType);
        return deviceType;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {

        User User = (User) SecurityUtils.getSubject().getPrincipal();
        Long userId = User.getId();
        Long farmId = 0L;
        //根据用户获取拥有的农场
        List<Farm> farmList = farmDao.listByUserId(userId);
        if (farmList != null && farmList.size() > 0) {
            farmId=farmList.get(0).getId();
        }

        request.getParams().put("userId",userId);
        request.getParams().put("farmId",farmId);

        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return deviceThresholdDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<DeviceThreshold> list(PageTableRequest request) {
                return deviceThresholdDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        deviceTypeDao.delete(id);
    }

    @RequestMapping("/updateDeviceThreshold")
    @ApiOperation(value = "新增")
    public RespEntiry updateFarmInfo(HttpServletRequest request) {

        String id = request.getParameter("id");

        DeviceThreshold deviceThreshold =  deviceThresholdDao.getById(Long.parseLong(id));

        deviceThresholdDao.updateThresholdInfo(deviceThreshold);
        return RespEntiry.success();
    }
}
