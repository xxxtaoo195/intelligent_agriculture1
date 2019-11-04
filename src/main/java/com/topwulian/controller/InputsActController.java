package com.topwulian.controller;


import com.topwulian.dao.FarmDao;
import com.topwulian.dao.InputsActDao;
import com.topwulian.dao.InputsBatchDao;
import com.topwulian.dao.InputsStockDao;
import com.topwulian.model.*;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.service.InputsActService;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inputs")
public class InputsActController {

    @Autowired
    private InputsActService inputsActService;

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private InputsBatchDao inputsBatchDao;

    @Autowired
    private InputsStockDao inputsStockDao;


    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return inputsActService.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<InputsAct> list(PageTableRequest request) {
                List<InputsAct> lists = inputsActService.list(request.getParams(), request.getOffset(), request.getLimit());
                return lists;
            }
        }).handle(request);
    }

    @GetMapping("/actInfoList")
    @ApiOperation(value = "列表")
    public PageTableResponse actInfoList(PageTableRequest request) {
        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return inputsActService.actcount(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<InputsAct> list(PageTableRequest request) {
                return inputsActService.actlist(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequestMapping("/addInputsAct")
    @ApiOperation(value = "新增")
    public RespEntiry updateFarmInfo(HttpServletRequest request) {

        String inputsType = request.getParameter("inputsType");//投入品类型
        String inputsName = request.getParameter("inputsName");  //投入品名称
        String isToxicity = request.getParameter("isToxicity"); //毒性
        String manufactor = request.getParameter("manufactor"); //生产厂家
        String factoryLicense = request.getParameter("factoryLicense"); //登记证号
        String qualityMonth = request.getParameter("qualityMonth"); //保质期
        String inputsUnit = request.getParameter("inputsUnit"); //规格

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Farm> farmList = farmDao.listByUserId(user.getId());




        InputsAct act = new InputsAct();
        act.setFarmId(Integer.parseInt(farmList.get(0).getId()+""));
        act.setInputsType(Integer.parseInt(inputsType));
        act.setInputsName(inputsName);
        act.setIsToxicity(Integer.parseInt(isToxicity));
        act.setManufactor(manufactor);
        act.setFactoryLicense(factoryLicense);
        act.setQualityMonth(Integer.parseInt(qualityMonth));
        act.setInputsUnit(inputsUnit);
        inputsActService.save(act);


        return RespEntiry.success();
    }


}
