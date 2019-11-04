package com.topwulian.model;

/**
 * 阿里云设备
 */
public class AliyunDevice extends BaseEntity<Long>{

    private String deviceName;
    private String productKey;
    private String farmId;
    private String userId;
    private String sotTic;
    private String subTic;
    private String createtime;
    private String createuser;
    private String updatetime;
    private String updateuser;
    private String deviceType;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSotTic() {
        return sotTic;
    }

    public void setSotTic(String sotTic) {
        this.sotTic = sotTic;
    }

    public String getSubTic() {
        return subTic;
    }

    public void setSubTic(String subTic) {
        this.subTic = subTic;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }
}
