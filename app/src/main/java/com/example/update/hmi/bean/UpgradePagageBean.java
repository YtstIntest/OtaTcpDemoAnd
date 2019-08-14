package com.example.update.hmi.bean;

import java.util.List;

public class UpgradePagageBean {


    /**
     * event : 17
     * triggerMode : 0
     * type : 0
     * array : [{"device":"ECU","size":100,"vendor":"","swAppliedReference":"","swReference":"","description":"http:www.intest.com/hmi","version":"v1.0"}]
     */

    private int event;
    private int triggerMode;
    private int type;
    private List<ArrayBean> array;

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

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "UpgradePagageBean{" +
                "event=" + event +
                ", triggerMode=" + triggerMode +
                ", type=" + type +
                ", array=" + array +
                '}';
    }
}
