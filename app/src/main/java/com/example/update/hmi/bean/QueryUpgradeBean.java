package com.example.update.hmi.bean;

import com.google.gson.annotations.SerializedName;

public class QueryUpgradeBean {


    /**
     * event : 4100
     * triggerMode : 2
     * type : 1
     * return : 2
     */

    private int event;
    private int triggerMode;
    private int type;
    @SerializedName("return")
    private int returnX;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getTriggerMode() {
        return triggerMode;
    }

    public void setTriggerMode(int triggerMode) {
        this.triggerMode = triggerMode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReturnX() {
        return returnX;
    }

    public void setReturnX(int returnX) {
        this.returnX = returnX;
    }

    @Override
    public String toString() {
        return "QueryUpgradeBean{" +
                "event=" + event +
                ", triggerMode=" + triggerMode +
                ", type=" + type +
                ", returnX=" + returnX +
                '}';
    }
}
