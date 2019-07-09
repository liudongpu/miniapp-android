package com.uhutu.miniapp.mappandroid.miniapp;

import com.uhutu.miniapp.mappandroid.face.MiniappEventDelegate;

/**
 * 全局操作服务
 */
public class MiniappEventInstance {

    private  final  static MiniappEventInstance instance=new MiniappEventInstance();

    public static MiniappEventInstance getInstance(){
        return instance;
    }





    public MiniappEventDelegate getNativeDelegate() {
        return nativeDelegate;
    }

    public void setNativeDelegate(MiniappEventDelegate nativeDelegate) {
        this.nativeDelegate = nativeDelegate;
    }

    /**
     * 这个是代理回调操作对象  这个一般用于全局引用
     */
    private MiniappEventDelegate nativeDelegate;










}
