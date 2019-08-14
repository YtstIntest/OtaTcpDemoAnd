package com.example.update.hmi.bean;

public class SelectUpgradeModeBean {
    /**
     * event : 106
     * mode : 1
     * scheduleTime : 12312312313
     */

    private int event;
    private int mode;
    private String scheduleTime;

    public SelectUpgradeModeBean(int event, int mode, String scheduleTime) {
        this.event = event;
        this.mode = mode;
        this.scheduleTime = scheduleTime;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    @Override
    public String toString() {
        return "SelectUpgradeModeBean{" +
                "event=" + event +
                ", mode=" + mode +
                ", scheduleTime=" + scheduleTime +
                '}';
    }
}
