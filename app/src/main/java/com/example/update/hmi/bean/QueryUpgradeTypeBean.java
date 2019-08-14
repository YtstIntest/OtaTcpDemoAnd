package com.example.update.hmi.bean;

public class QueryUpgradeTypeBean {

    /**
     * event : 20
     * triggerMode : 1
     * type : 0
     */

    private int event;
    private int triggerMode;
    private int type;

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

    @Override
    public String toString() {
        return "QueryUpgradeTypeBean{" +
                "event=" + event +
                ", triggerMode=" + triggerMode +
                ", type=" + type +
                '}';
    }
}
