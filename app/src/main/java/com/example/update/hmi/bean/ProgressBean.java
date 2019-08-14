package com.example.update.hmi.bean;

import java.util.List;

public class ProgressBean {

    /**
     * event : 12
     * array : [{"device":"ECU","progress":0}]
     * type : 0
     * errno : 0
     */

    private int event;
    private int type;
    private int errno;
    private List<ArrayBean> array;

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

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public List<ArrayBean> getArray() {
        return array;
    }

    public void setArray(List<ArrayBean> array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "ProgressBean{" +
                "event=" + event +
                ", type=" + type +
                ", errno=" + errno +
                ", array=" + array +
                '}';
    }

    public static class ArrayBean {
        /**
         * device : ECU
         * progress : 0
         */

        private String device;
        private int progress;

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
}
