package com.youbi.app.core.vo;

/**
 * Created by hubin1 on 2017/4/17.
 */
public class Marker {

    private int id;

    private int type;

    private float lon;

    private float lat;

    private int isDamaged;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getIsDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(int isDamaged) {
        this.isDamaged = isDamaged;
    }
}
