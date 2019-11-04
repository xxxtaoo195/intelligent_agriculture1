package com.topwulian.dto;

import com.topwulian.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: szz
 * @Date: 2018/12/30 下午9:19
 * @Version 1.0
 * myFarm.html页面需要的各种数据
 */
public class FarmDto{
    User user;
    Farm farm;
    //农情通知
    List<Notice> recentlyNoticeList;
    //设备报警
    List<DeviceLog> deviceNoticeList;
    //当前农场的设备列表信息
    List<Device> deviceList;
    //设备实时数据
    List<DeviceGather> realTimeDataList;
    //摄像头列表
    List<Vedio> vedioList;

    List<Farm> farmList;

    List<Monpoints> monpointsList;

    List<DeviceCon> deviceConsList;


    List<DeviceThreshold> deviceThresholdList;

    public List<DeviceThreshold> getDeviceThresholdList() {
        return deviceThresholdList;
    }

    public void setDeviceThresholdList(List<DeviceThreshold> deviceThresholdList) {
        this.deviceThresholdList = deviceThresholdList;
    }

    Map<Long,List<DeviceGather>> deviceGatherMap=new HashMap<>();

    public List<DeviceCon> getDeviceConsList() {
        return deviceConsList;
    }

    public void setDeviceConsList(List<DeviceCon> deviceConsList) {
        this.deviceConsList = deviceConsList;
    }

    public List<Monpoints> getMonpointsList() {
        return monpointsList;
    }

    public void setMonpointsList(List<Monpoints> monpointsList) {
        this.monpointsList = monpointsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<Notice> getRecentlyNoticeList() {
        return recentlyNoticeList;
    }

    public void setRecentlyNoticeList(List<Notice> recentlyNoticeList) {
        this.recentlyNoticeList = recentlyNoticeList;
    }

    public List<DeviceLog> getDeviceNoticeList() {
        return deviceNoticeList;
    }

    public void setDeviceNoticeList(List<DeviceLog> deviceNoticeList) {
        this.deviceNoticeList = deviceNoticeList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<DeviceGather> getRealTimeDataList() {
        return realTimeDataList;
    }

    public void setRealTimeDataList(List<DeviceGather> realTimeDataList) {
        this.realTimeDataList = realTimeDataList;
    }

    public List<Vedio> getVedioList() {
        return vedioList;
    }

    public void setVedioList(List<Vedio> vedioList) {
        this.vedioList = vedioList;
    }

    public List<Farm> getFarmList() {
        return farmList;
    }

    public void setFarmList(List<Farm> farmList) {
        this.farmList = farmList;
    }

    public Map<Long, List<DeviceGather>> getDeviceGatherMap() {
        return deviceGatherMap;
    }

    public void setDeviceGatherMap(Map<Long, List<DeviceGather>> deviceGatherMap) {
        this.deviceGatherMap = deviceGatherMap;
    }
}
