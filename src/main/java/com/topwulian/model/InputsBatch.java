package com.topwulian.model;

import io.swagger.models.auth.In;

import java.util.Date;

/**
 * 投入品批次管理
 */
public class InputsBatch {

    private String batchId;//批次编号
    private String inputsId;//投入品编号
    private String stockNumber;//库存数量
    private Long farmId;//基地编号
    private Long fid;

    private String inputsName;
    private String factoryName;

    public String getInputsName() {
        return inputsName;
    }

    public void setInputsName(String inputsName) {
        this.inputsName = inputsName;
    }

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

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getInputsId() {
        return inputsId;
    }

    public void setInputsId(String inputsId) {
        this.inputsId = inputsId;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }
}
