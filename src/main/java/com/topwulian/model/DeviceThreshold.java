package com.topwulian.model;

import java.util.Date;

/**
 * 设备
 */
public class DeviceThreshold extends BaseEntity<Long> {

	private String minthreshold; //最低阈值
	private String maxthreshold; //最高阈值
	private String mid;       //监测点ID
	private String deviceId;  //设备编号
	private Date remindDate;  //预警时间
	private Integer status;   //状态
	private String remark; //备注
	private String value; //值

	private String name;  //设备名称
	private String monpointsName; //监测点名称

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonpointsName() {
		return monpointsName;
	}

	public void setMonpointsName(String monpointsName) {
		this.monpointsName = monpointsName;
	}

	public Date getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(Date remindDate) {
		this.remindDate = remindDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMinthreshold() {
		return minthreshold;
	}

	public void setMinthreshold(String minthreshold) {
		this.minthreshold = minthreshold;
	}

	public String getMaxthreshold() {
		return maxthreshold;
	}

	public void setMaxthreshold(String maxthreshold) {
		this.maxthreshold = maxthreshold;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
}
