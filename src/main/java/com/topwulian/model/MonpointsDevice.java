package com.topwulian.model;

import java.util.Date;

/**
 * 监测点采集
 */
public class MonpointsDevice extends BaseEntity<Long>{

    private Long mid;  //监测点编号
    private Long deviceid; //设备编号

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Long deviceid) {
        this.deviceid = deviceid;
    }
}
