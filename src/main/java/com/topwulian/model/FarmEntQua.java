package com.topwulian.model;

import java.util.Date;

public class FarmEntQua extends BaseEntity<Long> {

    private Integer farmid;
    private String imgURL;

    private Date createTime;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFarmid() {
        return farmid;
    }

    public void setFarmid(Integer farmid) {
        this.farmid = farmid;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
