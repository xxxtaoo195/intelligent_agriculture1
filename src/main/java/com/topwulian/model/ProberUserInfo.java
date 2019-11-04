package com.topwulian.model;

import java.util.Date;

public class ProberUserInfo extends BaseEntity<Long> {


    private String username;
    private String password;
    private String token;
    private Date tokendate;
    private String farmId;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokendate() {
        return tokendate;
    }

    public void setTokendate(Date tokendate) {
        this.tokendate = tokendate;
    }

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }
}
