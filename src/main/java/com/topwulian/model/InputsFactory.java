package com.topwulian.model;

/**
 * 投入品管理
 */
public class InputsFactory extends BaseEntity<Long>{

    private String factoryName; //生产厂家名称
    private String factoryLicense; //登记证号

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryLicense() {
        return factoryLicense;
    }

    public void setFactoryLicense(String factoryLicense) {
        this.factoryLicense = factoryLicense;
    }
}
