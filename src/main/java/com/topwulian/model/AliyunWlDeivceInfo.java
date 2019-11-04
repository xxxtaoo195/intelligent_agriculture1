package com.topwulian.model;

public class AliyunWlDeivceInfo extends BaseEntity<Long>{


    private String device_id;
    private String device_unit;
    private String device_name;
    private String device_type;

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_unit() {
        return device_unit;
    }

    public void setDevice_unit(String device_unit) {
        this.device_unit = device_unit;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
}
