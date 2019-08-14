package com.example.update.hmi.bean;

public class DownloadResultBean {

    /**
     * event : 1
     * type : 0
     * scheduleTime : 12312312313
     */

    private int event;
    private int type;
    private String scheduleTime;

    public DownloadResultBean(int event, int type,String scheduleTime) {
        this.event = event;
        this.type = type;
        this.scheduleTime = scheduleTime;
    }

    public DownloadResultBean(int event, int type) {
        this.event = event;
        this.type = type;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

}
