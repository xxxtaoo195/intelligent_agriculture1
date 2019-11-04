package com.topwulian.model;

import java.io.Serializable;
import java.util.Date;

public class ProberDeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String CheckID;     //记录唯一标识
    private String testItem;    //	检测项目
    private String sampleName;  //	样品名称
    private Date detDate;     //	检测时间
    private String detResult;   //	检测结果
    private String testMethod;  //	检测方法
    private String testBasis;   //	检测依据
    private String testValueCom; //	检测值单位
    private String testCom;     //	被检单位
    private String inspector;   //	检测人员
    private String auditors;    //	审核人员
    private String referValue;  //	对照值

    private String farmid; //基地

    private String deviceId; //设备序列号


    private Date createtime;
    private String createuser;
    private Date updatetime;
    private String updateuser;


    public String getFarmid() {
        return farmid;
    }

    public void setFarmid(String farmid) {
        this.farmid = farmid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCheckID() {
        return CheckID;
    }

    public void setCheckID(String checkID) {
        CheckID = checkID;
    }

    public String getTestItem() {
        return testItem;
    }

    public void setTestItem(String testItem) {
        this.testItem = testItem;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public Date getDetDate() {
        return detDate;
    }

    public void setDetDate(Date detDate) {
        this.detDate = detDate;
    }

    public String getDetResult() {
        return detResult;
    }

    public void setDetResult(String detResult) {
        this.detResult = detResult;
    }

    public String getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(String testMethod) {
        this.testMethod = testMethod;
    }

    public String getTestBasis() {
        return testBasis;
    }

    public void setTestBasis(String testBasis) {
        this.testBasis = testBasis;
    }

    public String getTestValueCom() {
        return testValueCom;
    }

    public void setTestValueCom(String testValueCom) {
        this.testValueCom = testValueCom;
    }

    public String getTestCom() {
        return testCom;
    }

    public void setTestCom(String testCom) {
        this.testCom = testCom;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getAuditors() {
        return auditors;
    }

    public void setAuditors(String auditors) {
        this.auditors = auditors;
    }

    public String getReferValue() {
        return referValue;
    }

    public void setReferValue(String referValue) {
        this.referValue = referValue;
    }
}
