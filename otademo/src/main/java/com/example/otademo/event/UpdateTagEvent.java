package com.example.otademo.event;

public class UpdateTagEvent {
    public int value;
    public UpdateEventType eventType;
    public String data;
    /**
     * @param eventType 事件类型
     * @param value 数字数据
     */
    public UpdateTagEvent(UpdateEventType eventType, int value) {
        this.eventType = eventType;
        this.value = value;
    }

    /**
     * @param eventType 事件类型
     * 无结果返回
     */
    public UpdateTagEvent(UpdateEventType eventType) {
        this.eventType = eventType;
    }

    /**
     * @param eventType 事件类型
     * @param data 字符串数据
     */
    public UpdateTagEvent(UpdateEventType eventType, String data) {
        this.eventType = eventType;
        this.data = data;
    }
}
