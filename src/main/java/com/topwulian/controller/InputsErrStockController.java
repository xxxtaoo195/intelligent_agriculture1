package com.topwulian.controller;


import com.aliyun.openservices.iot.api.util.StringUtil;
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
import java.util.Map;

@RestController
@RequestMapping("/inputsErrStock")
public class InputsErrStockController {

    @Autowired
    private InputsActService inputsActService;

    @Autowired
    private FarmDao farmDao;

    @Autowired
    private InputsBatchDao inputsBatchDao;

    @Autowired
    private InputsStockDao inputsStockDao;

    @Autowired
    private InputsErrStockDao inputsErrStockDao;




    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {


        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        request.getParams().put("farmId",farmList.get(0).getId());

        return new PageTableHandler(new PageTableHandler.CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return inputsErrStockDao.count(request.getParams());
            }
        }, new PageTableHandler.ListHandler() {

            @Override
            public List<InputsErrStock> list(PageTableRequest request) {
                return inputsErrStockDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @RequestMapping("/addInputsErrStock")
    @ApiOperation(value = "新增")
    public RespEntiry addInputsStock(HttpServletRequest request) {

        String inputsId = request.getParameter("inputsId");//投入品ID
        String fid = request.getParameter("fid");//生产厂家
        String username = request.getParameter("username");//供应商addInputsErrOut

        String manuDate = request.getParameter("manuDate");//生产时间
        String purchaseNumber = request.getParameter("purchaseNumber");//生产时间
        String inputsType = request.getParameter("inputsType");//操作类型
        String supplier = request.getParameter("supplier");//供应商
        String remarks = request.getParameter("remarks");//备注
        String batchId = request.getParameter("batchId");//批次号



        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        SimpleDateFormat bdf=new SimpleDateFormat("yyyyMMddhhmmss");

        if(StringUtil.isEmpty(batchId)){
            batchId = bdf.format(new Date());
            if (inputsType.equals("1")) {
                batchId = "R"+"NY" + batchId; //农药
            } else if (inputsType.equals("2")) {
                batchId = "R"+"ZZ" + batchId; //种子
            } else if (inputsType.equals("3")) {
                batchId = "R"+"FL" + batchId; //肥料
            } else if (inputsType.equals("4")) {
                batchId = "R"+"SY" + batchId; //兽药
            } else if (inputsType.equals("5")) {
                batchId = "R"+"SL" + batchId; //饲料
            }

            InputsBatch inputsBatch = new InputsBatch();

            inputsBatch.setBatchId(batchId);
            inputsBatch.setInputsId(inputsId);
            inputsBatch.setStockNumber(purchaseNumber);
            inputsBatch.setFid(Long.parseLong(fid));
            inputsBatch.setFarmId(farmList.get(0).getId());
            inputsBatchDao.save(inputsBatch);
        }


        if(isNumericZidai(purchaseNumber)){
            //修改批次数量
            InputsBatch inputsBatch = inputsBatchDao.getById(batchId);
            Double stockNumber =  Double.parseDouble(inputsBatch.getStockNumber());
            Double number =  Double.parseDouble(purchaseNumber);
            Double snumber = stockNumber + number;
            inputsBatch.setStockNumber(snumber+"");
            inputsBatchDao.updateInfo(inputsBatch);

            //修改库存记录剩余数量
            InputsStock stock = new InputsStock();
            stock.setBatchId(batchId);
            stock.setRemainNumber(snumber+"");
            inputsStockDao.updateInfo(stock);
        }


        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        //添加异常出入库记录
        InputsErrStock inputsErrStock = new InputsErrStock();
        inputsErrStock.setBatchId(batchId);
        inputsErrStock.setOperatingNumber(purchaseNumber);
        inputsErrStock.setOperatingTime(new Date());
        inputsErrStock.setUsername(username);
       /* try {
            inputsErrStock.setManuDate(df.parse(manuDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        inputsErrStock.setRemarks(remarks);
        inputsErrStock.setType(2);//入库
        inputsErrStock.setSupplier(supplier);
        inputsErrStockDao.save(inputsErrStock);

        return RespEntiry.success();
    }

    @RequestMapping("/addInputsErrOut")
    @ApiOperation(value = "新增")
    public RespEntiry addInputsErrOut(HttpServletRequest request) {

        String inputsId = request.getParameter("inputsId");//投入品ID
        String username = request.getParameter("username");//供应商
        String purchaseNumber = request.getParameter("purchaseNumber");//生产时间
        String supplier = request.getParameter("supplier");//供应商
        String remarks = request.getParameter("remarks");//备注
        String batchId = request.getParameter("batchId");//批次号



        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        SimpleDateFormat bdf=new SimpleDateFormat("yyyyMMddhhmmss");


        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");


        //添加异常出入库记录
        InputsErrStock inputsErrStock = new InputsErrStock();
        inputsErrStock.setBatchId(batchId);
        inputsErrStock.setOperatingNumber(purchaseNumber);
        inputsErrStock.setOperatingTime(new Date());
        inputsErrStock.setUsername(username);
/*        try {
            inputsErrStock.setManuDate(df.parse(manuDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        inputsErrStock.setRemarks(remarks);
        inputsErrStock.setType(1);//出库
        inputsErrStock.setSupplier(supplier);
        inputsErrStockDao.save(inputsErrStock);


        if(isNumericZidai(purchaseNumber)){
            //修改批次数量
            InputsBatch inputsBatch = inputsBatchDao.getById(batchId);
            Double stockNumber =  Double.parseDouble(inputsBatch.getStockNumber());
            Double number =  Double.parseDouble(purchaseNumber);
            Double snumber;
            if(number >= stockNumber){
                inputsBatch.setStockNumber("0");
                snumber = 0.0;
            }else{
                snumber = stockNumber - number;
                inputsBatch.setStockNumber(snumber+"");
            }
            inputsBatchDao.updateInfo(inputsBatch);

            //修改库存记录剩余数量
            InputsStock stock = new InputsStock();
            stock.setBatchId(batchId);
            stock.setRemainNumber(snumber+"");
            inputsStockDao.updateInfo(stock);
        }

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

    @GetMapping("/getBatchList")
    @ApiOperation(value = "查询")
    public List<InputsBatch> getBatchList() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());


        return inputsBatchDao.getBatchListInfo(farmList.get(0).getId());
    }



    @GetMapping("/getInputActInfo/{batchId}")
    @ApiOperation(value = "查询")
    public InputsAct getInputActInfo(@PathVariable String batchId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        return inputsActService.getByBatchIdInfo(batchId).get(0);
    }


    @GetMapping("/getInputActInfo")
    @ApiOperation(value = "查询")
    public List<InputsAct> getInputActInfo() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        List<Farm> farmList = farmDao.listByUserId(user.getId());

        return inputsActService.getActInfoList();
    }

    public boolean isNumericZidai(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


}
