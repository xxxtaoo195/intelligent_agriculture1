package com.topwulian.controller;


import com.topwulian.dao.FarmDao;
import com.topwulian.dao.InputsBatchDao;
import com.topwulian.dao.InputsOutDao;
import com.topwulian.dao.InputsStockDao;
import com.topwulian.model.*;
import com.topwulian.page.table.PageTableHandler;
import com.topwulian.page.table.PageTableRequest;
import com.topwulian.page.table.PageTableResponse;
import com.topwulian.service.InputsActService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/inputsOut")
public class InputsOutController {


    @Autowired
    private FarmDao farmDao;


    @Autowired
    private InputsOutDao inputsOutDao;




    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {

        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        request.getParams().put("farmId",farmList.get(0).getId());

        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return inputsOutDao.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<InputsOut> list(PageTableRequest request) {
                return inputsOutDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequestMapping("/addInputsStock")
    @ApiOperation(value = "新增")
    public RespEntiry addInputsStock(HttpServletRequest request) {

        String inputsId = request.getParameter("inputsId");//投入品ID
        String manufactor = request.getParameter("manufactor");//生产厂家
        String supplier = request.getParameter("supplier");//供应商
        String manuDate = request.getParameter("manuDate");//生产时间
        String username = request.getParameter("username");//生产时间
        String purchaseNumber = request.getParameter("purchaseNumber");//数量




        return RespEntiry.success();
    }

    @GetMapping("/getStockInfo")
    @ApiOperation(value = "查询")
    public InputsDto getStockInfo() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        InputsDto inputsDto = new InputsDto();



        return inputsDto;
    }

    @GetMapping("/getFactoryInfo/{inputId}")
    @ApiOperation(value = "查询")
    public InputsDto getFactoryInfo(@PathVariable Long inputId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        InputsDto inputsDto = new InputsDto();



        return inputsDto;
    }


}
