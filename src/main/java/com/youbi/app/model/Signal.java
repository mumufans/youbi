package com.youbi.app.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Signal {
    private Integer signalId;

    private Integer typeId;

    private SignalType signalType;

    private Unit unit;

    private User user;

    private Float signalLong;

    private Float signalLat;

    private County county;

    private Roadway roadway;

    private boolean isDamaged;

    private boolean isUsed;

    private boolean isConfirmed;

    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

    public Integer getSignalId() {
        return signalId;
    }

    public void setSignalId(Integer signalId) {
        this.signalId = signalId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Unit getUnit() {
        return unit;
    }

    public SignalType getSignalType() {
        return signalType;
    }

    public void setSignalType(SignalType signalType) {
        this.signalType = signalType;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getSignalLong() {
        return signalLong;
    }

    public void setSignalLong(Float signalLong) {
        this.signalLong = signalLong;
    }

    public Float getSignalLat() {
        return signalLat;
    }

    public void setSignalLat(Float signalLat) {
        this.signalLat = signalLat;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public Roadway getRoadway() {
        return roadway;
    }

    public void setRoadway(Roadway roadway) {
        this.roadway = roadway;
    }

    public boolean getIsDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}