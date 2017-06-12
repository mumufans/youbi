package com.youbi.app.model;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String zhname;

    private String hash;

    private String salt;

    private String badeNumber;

    private String duty;

    private String mobile;


//    private int unit;
//
//    private int role;

    private Unit adminUnit;

    private Date lastlogintime;

    private Unit unit;

    private Role role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getZhname() {
        return zhname;
    }

    public void setZhname(String zhname) {
        this.zhname = zhname == null ? null : zhname.trim();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getBadeNumber() {
        return badeNumber;
    }

    public void setBadeNumber(String badeNumber) {
        this.badeNumber = badeNumber == null ? null : badeNumber.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Unit getAdminunit() {
        return adminUnit;
    }

    public void setAdminunit(Unit adminUnit) {
        this.adminUnit = adminUnit;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }
}