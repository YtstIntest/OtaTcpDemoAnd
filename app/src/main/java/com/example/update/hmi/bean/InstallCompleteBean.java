package com.example.update.hmi.bean;

import java.util.List;

public class InstallCompleteBean {


    /**
     * event : 23
     * timeout : 0
     * array : [{"device":"ECU","result":0,"description":"111"}]
     * type : 0
     */

    private int event;
    private int timeout;
    private int type;
    private List<ArrayBean> array;

    public int getEvent() {
        return event;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
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
        return "InstallCompleteBean{" +
                "event=" + event +
                ", timeout=" + timeout +
                ", type=" + type +
                ", array=" + array +
                '}';
    }

    public static class ArrayBean {
        /**
         * device : ECU
         * result : 0
         * description : 111
         */

        private String device;
        private int result;
        private String description;

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
