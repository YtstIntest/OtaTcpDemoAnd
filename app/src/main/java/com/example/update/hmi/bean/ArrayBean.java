package com.example.update.hmi.bean;

public class ArrayBean {
    /**
     * device : ECU
     * size : 100
     * vendor :
     * swAppliedReference :
     * swReference :
     * description : http:www.intest.com/hmi
     * version : v1.0
     */

    private String device;
    private int size;
    private String vendor;
    private String swAppliedReference;
    private String swReference;
    private String description;
    private String version;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSwAppliedReference() {
        return swAppliedReference;
    }

    public void setSwAppliedReference(String swAppliedReference) {
        this.swAppliedReference = swAppliedReference;
    }

    public String getSwReference() {
        return swReference;
    }

    public void setSwReference(String swReference) {
        this.swReference = swReference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
