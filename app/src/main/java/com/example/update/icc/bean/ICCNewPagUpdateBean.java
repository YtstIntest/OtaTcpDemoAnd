package com.example.update.icc.bean;

public class ICCNewPagUpdateBean {


    /**
     * event : 3
     * url : http://www.intest.com/icc
     */

    private int event;
    private String url;

    public ICCNewPagUpdateBean() {
    }

    public ICCNewPagUpdateBean(int event, String url) {
        this.event = event;
        this.url = url;
    }

    public ICCNewPagUpdateBean(int event) {
        this.event = event;
    }

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ICCNewPagUpdateBean{" +
                "event=" + event +
                ", url='" + url + '\'' +
                '}';
    }
}
