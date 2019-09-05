package com.uhutu.miniapp.mappandroid.miniapp;

import android.app.Activity;
import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;
import com.uhutu.miniapp.mappandroid.face.MiniappEventDelegate;
import com.uhutu.miniapp.mappandroid.model.NativeOperateEvent;

import java.util.HashMap;
import java.util.Map;


/**
 * 这个是原生APP调用小应用的逻辑接口，单例
 */
public class MiniappSupport {




    private final static MiniappSupport support=new MiniappSupport();

    /**
     * 获取单例对象
     * @return
     */
    public static MiniappSupport getInstance(){
        return support;
    }


    /**
     * 跳转小应用的操作类
     * @param sUrl 小应用的地址
     * @param iMiniappDelegate 回调类，一般可以静态
     * @param activity context对象
     */
    public void jumpUrl(String sUrl, MiniappEventDelegate iMiniappDelegate, Context activity){


        MiniappEventInstance.getInstance().setNativeDelegate(iMiniappDelegate);

        new MiniappJumpUtil().jumpUrl(sUrl,activity);
    }


    /**
     * 备用流程  在紧急情况下跳转到指定页面
     * @param sUrl
     */
    public void delegateBackJump(String sUrl){
        NativeOperateEvent operateEvent=new NativeOperateEvent();
        operateEvent.setEventType("nativeEventJump");
        Map<String,String> mJump=new HashMap<>();
        mJump.put("targetUrl",sUrl);
        operateEvent.setEventParam(new Gson().toJson(mJump));
        MiniappEventInstance.getInstance().getNativeDelegate().jumpWtihParam(operateEvent);
    }


    /**
     * 发送系统通知消息  原生系统发送给RN的通知内容
     * @param sListencerName
     * @param map
     */
    public void sendNativeNotice(String sListencerName, Map<String,String> map){
        map.put("miniapp_lisenter_name",sListencerName);
        Message msg=new Message();
        msg.obj=map;
        MiniappNoticeBridge.mHandler.sendMessage(msg);
    }








}
