package com.topwulian.controller;


import com.topwulian.dao.FarmDao;
import com.topwulian.dao.InputsBatchDao;
import com.topwulian.dao.InputsErrStockDao;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inputsStock")
public class InputsStockController {

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

        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        request.getParams().put("farmId",farmList.get(0).getId());

        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return inputsStockDao.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<InputsAct> list(PageTableRequest request) {
                return inputsStockDao.list(request.getParams(), request.getOffset(), request.getLimit());
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


        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        InputsType type = inputsStockDao.getByTypeId(Long.parseLong(inputsId));

        SimpleDateFormat bdf=new SimpleDateFormat("yyyyMMddhhmmss");

        String batchId = bdf.format(new Date());
        if (type.getInputsType() == 1) {
            batchId = "R"+"NY" + batchId; //农药
        } else if (type.getInputsType() == 2) {
            batchId = "R"+"ZZ" + batchId; //种子
        } else if (type.getInputsType() == 3) {
            batchId = "R"+"FL" + batchId; //肥料
        } else if (type.getInputsType() == 4) {
            batchId = "R"+"SY" + batchId; //兽药
        } else if (type.getInputsType() == 5) {
            batchId = "R"+"SL" + batchId; //饲料
        }

        InputsBatch inputsBatch = new InputsBatch();

        inputsBatch.setBatchId(batchId);
        inputsBatch.setInputsId(inputsId);
        inputsBatch.setStockNumber(purchaseNumber);
        inputsBatch.setFid(Long.parseLong(manufactor));
        inputsBatch.setFarmId(farmList.get(0).getId());
        inputsBatchDao.save(inputsBatch);

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        //添加入库记录
        InputsStock inputsStock = new InputsStock();
        inputsStock.setBatchId(batchId);
        inputsStock.setPurchaseNumber(purchaseNumber);
        inputsStock.setPurchaseDate(new Date());
        inputsStock.setRemainNumber(purchaseNumber);
        inputsStock.setUsername(username);
        try {
            inputsStock.setManuDate(df.parse(manuDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        inputsStock.setSupplier(supplier);
        inputsStock.setRemarks("入库");

        inputsStockDao.save(inputsStock);

        return RespEntiry.success();
    }

    @GetMapping("/getStockInfo")
    @ApiOperation(value = "查询")
    public InputsDto getStockInfo() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        InputsDto inputsDto = new InputsDto();

        inputsDto.setInputsTypeList(inputsStockDao.listInputsType());

        return inputsDto;
    }

    @GetMapping("/getFactoryInfo/{inputId}")
    @ApiOperation(value = "查询")
    public InputsDto getFactoryInfo(@PathVariable Long inputId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        InputsDto inputsDto = new InputsDto();

        inputsDto.setInputsFactoryList(inputsStockDao.listInputsFactory(inputId));

        return inputsDto;
    }


}
