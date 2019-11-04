package com.topwulian.model;

import java.util.Date;
import java.util.List;

/**
 * 监测点
 */
public class Monpoints extends BaseEntity<Long>{

    private String monpointsName;  //监测点名称
    private String monpointsAddress; //监测点地址
    private String monpointsid; //监测点标识
    private String monpointstype; //检测点类型
    private String monpointsDeviceName;//控制设备别名
    private String userid;  //所属用户
    private String farmid;  //所属基地
    private Date createtime;
    private String createuser;
    private Date updatetime;
    private String updateuser;

    private List<DeviceCon> deviceConList;

    private List<DeviceGather> deviceGatherList;


    public List<DeviceGather> getDeviceGatherList() {
        return deviceGatherList;
    }

    public void setDeviceGatherList(List<DeviceGather> deviceGatherList) {
        this.deviceGatherList = deviceGatherList;
    }

    public List<DeviceCon> getDeviceConList() {
        return deviceConList;
    }

    public void setDeviceConList(List<DeviceCon> deviceConList) {
        this.deviceConList = deviceConList;
    }

    public String getMonpointsDeviceName() {
        return monpointsDeviceName;
    }

    public void setMonpointsDeviceName(String monpointsDeviceName) {
        this.monpointsDeviceName = monpointsDeviceName;
    }

    public String getFarmid() {
        return farmid;
    }

    public void setFarmid(String farmid) {
        this.farmid = farmid;
    }

    public String getMonpointsName() {
        return monpointsName;
    }

    public void setMonpointsName(String monpointsName) {
        this.monpointsName = monpointsName;
    }

    public String getMonpointsAddress() {
        return monpointsAddress;
    }

    public void setMonpointsAddress(String monpointsAddress) {
        this.monpointsAddress = monpointsAddress;
    }

    public String getMonpointsid() {
        return monpointsid;
    }

    public void setMonpointsid(String monpointsid) {
        this.monpointsid = monpointsid;
    }

    public String getMonpointstype() {
        return monpointstype;
    }

    public void setMonpointstype(String monpointstype) {
        this.monpointstype = monpointstype;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
}
