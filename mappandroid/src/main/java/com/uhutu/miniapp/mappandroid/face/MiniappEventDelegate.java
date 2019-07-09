package com.uhutu.miniapp.mappandroid.face;

import com.uhutu.miniapp.mappandroid.model.NativeAppInfo;
import com.uhutu.miniapp.mappandroid.model.NativeConfigInfo;
import com.uhutu.miniapp.mappandroid.model.NativeOperateEvent;
import com.uhutu.miniapp.mappandroid.model.NativeUserInfo;


/**
 * 原生APP需要实现这个接口类，用于小应用的回调逻辑方法
 */
public interface MiniappEventDelegate {


   /**
    * 获取用户相关信息
    * @return
    */
   public NativeUserInfo upNativeUserInfo();


   /**
    * 获取原生app相关信息
    * @return
    */
   public NativeAppInfo upNativeAppInfo();


   /**
    * 获取配置信息
    * @return
    */
   public NativeConfigInfo upNativeConfigInfo();


   /**
    * 事件
    * @param event
    * @return
    */
   public boolean onNativeOperateEvent(NativeOperateEvent event);









}
