package com.youbi.app.model;

import java.util.Date;
import java.util.List;

public class Intersection {
    private Integer intersectionId;

    private String intersectionName;

    private Float intersectionLong;

    private Float intersectionLat;

    private User user;

    private Unit unit;

    private List<Integer> roadwayIds;

    private IntersectionType intersectionType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Boolean isDamaged;

    private Date createDate;

    public List<Integer> getRoadwayIds() {
        return roadwayIds;
    }

    public void setRoadwayIds(List<Integer> roadwayIds) {
        this.roadwayIds = roadwayIds;
    }

    public IntersectionType getIntersectionType() {
        return intersectionType;
    }

    public void setIntersectionType(IntersectionType intersectionType) {
        this.intersectionType = intersectionType;
    }

//    private List<Roadway> roadwayList;
//
//    public List<Roadway> getRoadwayList() {
//        return roadwayList;
//    }
//
//    public void setRoadwayList(List<Roadway> roadwayList) {
//        this.roadwayList = roadwayList;
//    }

    public Integer getIntersectionId() {
        return intersectionId;
    }

    public void setIntersectionId(Integer intersectionId) {
        this.intersectionId = intersectionId;
    }

    public String getIntersectionName() {
        return intersectionName;
    }

    public void setIntersectionName(String intersectionName) {
        this.intersectionName = intersectionName == null ? null : intersectionName.trim();
    }

    public Float getIntersectionLong() {
        return intersectionLong;
    }

    public void setIntersectionLong(Float intersectionLong) {
        this.intersectionLong = intersectionLong;
    }

    public Float getIntersectionLat() {
        return intersectionLat;
    }

    public void setIntersectionLat(Float intersectionLat) {
        this.intersectionLat = intersectionLat;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean getIsDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
}