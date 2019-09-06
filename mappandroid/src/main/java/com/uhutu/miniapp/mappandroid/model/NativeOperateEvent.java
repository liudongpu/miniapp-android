package com.uhutu.miniapp.mappandroid.model;

import android.app.Activity;

/**
 * 原生操作相关
 */
public class NativeOperateEvent {


    /**
     * 操作类型
     */
    private String eventType;

    /**
     * 操作的参数
     */
    private String eventParam;

    /**
     * 操作的设置
     */
    private String eventSet;




    private Activity sourceActivity;


    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventParam() {
        return eventParam;
    }

    public void setEventParam(String eventParam) {
        this.eventParam = eventParam;
    }

    public String getEventSet() {
        return eventSet;
    }

    public void setEventSet(String eventSet) {
        this.eventSet = eventSet;
    }

    public Activity getSourceActivity() {
        return sourceActivity;
    }

    public void setSourceActivity(Activity sourceActivity) {
        this.sourceActivity = sourceActivity;
    }





}
