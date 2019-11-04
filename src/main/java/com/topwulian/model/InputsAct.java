package com.topwulian.model;

import java.util.Date;

/**
 * 投入品管理
 */
public class InputsAct extends BaseEntity<Long>{

    private Integer farmId;  //基地编号
    private Integer inputsId; //投入品编号
    private Integer isToxicity; //毒性 (是否无毒,农药特有属性)
    private Integer qualityMonth;//保质期 (月)
    private String inputsUnit;//规格
    private Long fid;//厂家


    private Integer inputsType; //投入品种类 :1:农药 2:种子 3:肥料 4:兽药 5 饲料


    /**
     * 显示字段inputsName
     */
    private String batchId; //批次编号
    private String purNum; //结算库存 暂不使用
    private String purchaseDate;//最后入库时间
    private String inputsName; //投入品名称
    private String stockNumber; //库存
    private String remainNumber;// 剩余库存


    //生产厂家表
    private String manufactor;//生产厂家
    private String factoryLicense;//登记证号
    private String factoryName;

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getFactoryLicense() {
        return factoryLicense;
    }

    public void setFactoryLicense(String factoryLicense) {
        this.factoryLicense = factoryLicense;
    }

    public String getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(String remainNumber) {
        this.remainNumber = remainNumber;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getPurNum() {
        return purNum;
    }

    public void setPurNum(String purNum) {
        this.purNum = purNum;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getInputsName() {
        return inputsName;
    }

    public void setInputsName(String inputsName) {
        this.inputsName = inputsName;
    }

    public Integer getInputsId() {
        return inputsId;
    }

    public void setInputsId(Integer inputsId) {
        this.inputsId = inputsId;
    }

    public Integer getFarmId() {
        return farmId;
    }

    public void setFarmId(Integer farmId) {
        this.farmId = farmId;
    }


    public Integer getInputsType() {
        return inputsType;
    }

    public void setInputsType(Integer inputsType) {
        this.inputsType = inputsType;
    }

    public Integer getIsToxicity() {
        return isToxicity;
    }

    public void setIsToxicity(Integer isToxicity) {
        this.isToxicity = isToxicity;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }


    public Integer getQualityMonth() {
        return qualityMonth;
    }

    public void setQualityMonth(Integer qualityMonth) {
        this.qualityMonth = qualityMonth;
    }

    public String getInputsUnit() {
        return inputsUnit;
    }

    public void setInputsUnit(String inputsUnit) {
        this.inputsUnit = inputsUnit;
    }
}
