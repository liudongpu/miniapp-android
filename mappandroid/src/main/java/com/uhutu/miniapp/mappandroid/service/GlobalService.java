package com.uhutu.miniapp.mappandroid.service;

import com.uhutu.miniapp.mappandroid.face.MiniappNativeDelegate;
import com.uhutu.miniapp.mappandroid.model.ConfigInfo;

/**
 * 全局操作服务
 */
public class GlobalService {

    private  final  static GlobalService instance=new GlobalService();

    public static GlobalService getInstance(){
        return instance;
    }





    public MiniappNativeDelegate getNativeDelegate() {
        return nativeDelegate;
    }

    public void setNativeDelegate(MiniappNativeDelegate nativeDelegate) {
        this.nativeDelegate = nativeDelegate;
    }

    /**
     * 这个是代理回调操作对象  这个一般用于全局引用
     */
    private MiniappNativeDelegate nativeDelegate;










}
