package com.topwulian.model;

import java.util.Date;

/**
 * 塞通token信息
 */
public class SysStTokenInfo extends BaseEntity<Long>{

    private String uuid; //UUID
    private Integer userid;
    private Integer farmid;
    private String token;
    private String areaid;
    private Date createtime;
    private String username;
    private String password;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFarmid() {
        return farmid;
    }

    public void setFarmid(Integer farmid) {
        this.farmid = farmid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
