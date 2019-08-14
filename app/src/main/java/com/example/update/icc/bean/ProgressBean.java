package com.example.update.icc.bean;

public class ProgressBean {

    /**
     * event : 1004
     * handled : 123
     * total : 222
     * error : 12
     */

    private int event;
    private int handled;
    private int total;
    private int error;

    public ProgressBean() {
    }

    public ProgressBean(int handled, int total) {
        this.handled = handled;
        this.total = total;
    }

    public ProgressBean(int event, int handled, int total, int error) {
        this.event = event;
        this.handled = handled;
        this.total = total;
        this.error = error;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getHandled() {
        return handled;
    }

    public void setHandled(int handled) {
        this.handled = handled;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
