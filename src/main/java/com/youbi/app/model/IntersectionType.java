package com.youbi.app.model;

public class IntersectionType {
    private Integer intersectionTypeId;

    private String intersectionTypeName;

    public Integer getIntersectionTypeId() {
        return intersectionTypeId;
    }

    public void setIntersectionTypeId(Integer intersectionTypeId) {
        this.intersectionTypeId = intersectionTypeId;
    }

    public String getIntersectionTypeName() {
        return intersectionTypeName;
    }

    public void setIntersectionTypeName(String intersectionTypeName) {
        this.intersectionTypeName = intersectionTypeName == null ? null : intersectionTypeName.trim();
    }
}