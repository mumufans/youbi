package com.youbi.app.model;

public class SignalType {
    private Integer signalTypeId;

    private String signalTypeName;

    public Integer getSignalTypeId() {
        return signalTypeId;
    }

    public void setSignalTypeId(Integer signalTypeId) {
        this.signalTypeId = signalTypeId;
    }

    public String getSignalTypeName() {
        return signalTypeName;
    }

    public void setSignalTypeName(String signalTypeName) {
        this.signalTypeName = signalTypeName == null ? null : signalTypeName.trim();
    }
}