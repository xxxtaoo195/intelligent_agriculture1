package com.topwulian.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputsStock extends BaseEntity<Long>{

    private String batchId;//批次编号
    private Long inputActId; //投入品编号
    private String purchaseNumber; //进货数量
    private Date purchaseDate; //进货时间
    private String remainNumber;//剩余数量

    private String username;//操作人
    private Date   manuDate;//生产日期
    private String supplier;//供应商
    private String remarks;//备注


    private String stockNumber; //总库存
    private String isToxicity;//毒性
    private String qualityMonth; //保质期
    private String inputsUnit; //规格
    private String inputsName; //投入品名称
    private String inputsType; //投入品类型
    private String factoryName; //生产厂家


    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getIsToxicity() {
        return isToxicity;
    }

    public void setIsToxicity(String isToxicity) {
        this.isToxicity = isToxicity;
    }

    public String getQualityMonth() {
        return qualityMonth;
    }

    public void setQualityMonth(String qualityMonth) {
        this.qualityMonth = qualityMonth;
    }

    public String getInputsUnit() {
        return inputsUnit;
    }

    public void setInputsUnit(String inputsUnit) {
        this.inputsUnit = inputsUnit;
    }

    public String getInputsName() {
        return inputsName;
    }

    public void setInputsName(String inputsName) {
        this.inputsName = inputsName;
    }

    public String getInputsType() {
        return inputsType;
    }

    public void setInputsType(String inputsType) {
        this.inputsType = inputsType;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getManuDate() {
        return manuDate;
    }

    public void setManuDate(Date manuDate) {
        this.manuDate = manuDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public String getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(String remainNumber) {
        this.remainNumber = remainNumber;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Long getInputActId() {
        return inputActId;
    }

    public void setInputActId(Long inputActId) {
        this.inputActId = inputActId;
    }

    public String getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
