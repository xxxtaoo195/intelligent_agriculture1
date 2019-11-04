package com.topwulian.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputsErrStock extends BaseEntity<Long>{

    private String batchId;//批次编号
    private String operatingNumber; //操作数量
    private Date operatingTime; //操作时间
    private Integer type;//操作类型
    private String username;//操作人
    private String remarks;//备注



    private Date   manuDate;//生产日期
    private String supplier;//供应商


    private String stockNumber; //总库存
    private String isToxicity;//毒性
    private String qualityMonth; //保质期
    private String inputsUnit; //规格
    private String inputsName; //投入品名称
    private String inputsType; //投入品类型
    private String factoryName; //生产厂家

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getOperatingNumber() {
        return operatingNumber;
    }

    public void setOperatingNumber(String operatingNumber) {
        this.operatingNumber = operatingNumber;
    }

    public Date getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(Date operatingTime) {
        this.operatingTime = operatingTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}
