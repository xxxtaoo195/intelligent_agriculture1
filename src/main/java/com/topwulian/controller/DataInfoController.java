package com.topwulian.controller;


import com.aliyun.openservices.iot.api.util.StringUtil;
import com.topwulian.dao.FarmDao;
import com.topwulian.dao.InputsOutDao;
import com.topwulian.dao.MonpointsDao;
import com.topwulian.dto.DeviceGatherCharts;
import com.topwulian.dto.FarmDto;
import com.topwulian.model.*;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.service.DeviceService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dataInfo")
public class DataInfoController {



    @Autowired
    private DeviceService deviceService;

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private MonpointsDao monpointsDao;

    @GetMapping("/data")
    @ApiOperation(value = "我的基地页面显示各种详情")
    public FarmDto data(){
        FarmDto farmDto=new FarmDto();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setPassword(null);
        farmDto.setUser(user);

        //设备信息
        //基地id默认就是当前拥有的第一个基地,根据前端传递过来的farmId进行切换
        //根据用户获取拥有的农场
        Long farmId = null;
        List<Farm> farmList = farmDao.listByUserId(user.getId());
        if (farmList != null && farmList.size() > 0) {
            Farm farm = farmList.get(0);
            farmId=farm.getId();
            farmDto.setFarm(farm);
            //查询基地监测点
            List<Monpoints> listmonpoints = monpointsDao.getByUserAndFarmInfo(user.getId()+"",farmId+"");
            farmDto.setMonpointsList(listmonpoints);
        }

        return farmDto;
    }


    @RequestMapping("/echartsShow")
    @ApiOperation(value = "echarts数据展示,要求按年月日")
    public Map<Long,List<DeviceGatherCharts> > echartsShow(PageTableRequest pageTableRequest){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setPassword(null);
        //按照farm和设备分别封装数据,一个农场的一个设备为一组数据
        return deviceService.echartsDeviceShow(pageTableRequest);
    }


    @RequestMapping("/getDeviceList")
    public FarmDto getGatherInfo(HttpServletRequest request) {
        String monpointsid = request.getParameter("monpointsid");
        FarmDto farmDto = new FarmDto();

        List<Device>  listdevice = deviceService.getByMidDeviceInfo(monpointsid);
        farmDto.setDeviceList(listdevice);
        return farmDto;
    }

}
