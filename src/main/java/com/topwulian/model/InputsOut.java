package com.topwulian.model;

import java.util.Date;

/**
 * 投入品管理
 */
public class InputsOut extends BaseEntity<Long>{

    private String batchId;
    private String outStockNumber;
    private Date outStockDate;
    private String username;
    private String remarks;

    private String makeArea;//使用面积
    private String makeMethod;//使用说明
    private String makeMassif;//使用地块

    private Date   manuDate;//生产日期
    private String supplier;//供应商


    private String stockNumber; //总库存
    private String isToxicity;//毒性
    private String qualityMonth; //保质期
    private String inputsUnit; //规格
    private String inputsName; //投入品名称
    private String inputsType; //投入品类型
    private String factoryName; //生产厂家


    public String getMakeMassif() {
        return makeMassif;
    }

    public void setMakeMassif(String makeMassif) {
        this.makeMassif = makeMassif;
    }

    public String getMakeArea() {
        return makeArea;
    }

    public void setMakeArea(String makeArea) {
        this.makeArea = makeArea;
    }

    public String getMakeMethod() {
        return makeMethod;
    }

    public void setMakeMethod(String makeMethod) {
        this.makeMethod = makeMethod;
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

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getOutStockNumber() {
        return outStockNumber;
    }

    public void setOutStockNumber(String outStockNumber) {
        this.outStockNumber = outStockNumber;
    }

    public Date getOutStockDate() {
        return outStockDate;
    }

    public void setOutStockDate(Date outStockDate) {
        this.outStockDate = outStockDate;
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
}
