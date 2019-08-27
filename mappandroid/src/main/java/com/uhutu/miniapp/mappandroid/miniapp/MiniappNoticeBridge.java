package com.uhutu.miniapp.mappandroid.miniapp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.gson.Gson;
import com.uhutu.miniapp.mappandroid.defines.CommonConst;

import java.util.HashMap;

import javax.annotation.Nonnull;

public class MiniappNoticeBridge extends ReactContextBaseJavaModule {


    final  static String TAG="MiniappNoticeBridge";

    public static  Handler mHandler;
    @Nonnull
    @Override
    public String getName() {
        return CommonConst.MINIAPP_NOTICE_BRIDGE;
    }


    public MiniappNoticeBridge(final ReactApplicationContext reactContext){


        super(reactContext);




       mHandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);




                reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("MiniappNoticeReminder",new Gson().toJson(msg.obj));

            }
        };




    }


}
