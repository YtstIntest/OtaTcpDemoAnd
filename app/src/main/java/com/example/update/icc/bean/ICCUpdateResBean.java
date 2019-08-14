package com.example.update.icc.bean;

import com.google.gson.annotations.SerializedName;

public class ICCUpdateResBean {


    /**
     * event : 1012
     * return : true
     */

    private int event;
    @SerializedName("return")
    private boolean returnX;

    public ICCUpdateResBean() {
    }

    public ICCUpdateResBean(int event, boolean returnX) {
        this.event = event;
        this.returnX = returnX;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public boolean isReturnX() {
        return returnX;
    }

    public void setReturnX(boolean returnX) {
        this.returnX = returnX;
    }
}
