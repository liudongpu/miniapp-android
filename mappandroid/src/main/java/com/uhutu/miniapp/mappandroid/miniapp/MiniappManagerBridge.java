package com.uhutu.miniapp.mappandroid.miniapp;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;
import com.uhutu.miniapp.mappandroid.defines.CommonConst;
import com.uhutu.miniapp.mappandroid.model.NativeOperateEvent;

import java.util.HashMap;
import java.util.Map;


public class MiniappManagerBridge extends ReactContextBaseJavaModule {

    final  static String TAG="MiniappManagerBridge";


    public MiniappManagerBridge(ReactApplicationContext reactContext) {
        super(reactContext);
    }



    @Override
    public String getName() {
        return CommonConst.MINIAPP_MANAGER_BRIDGE;
    }


    /**
     * 这个是小应用调用原生的功能   注意，调用这个逻辑的没有返回值
     * @param sType 类型
     * @param sJson 参数json
     * @param sOption 设置json
     */
    @ReactMethod
    public void sendNativeEvent(String sType, String sJson, String sOption) {
        Log.d(TAG, "sendNativeEvent  type:"+sType+"  json:"+sJson  +"  option:"+sOption);


        NativeOperateEvent operateEvent=new NativeOperateEvent();
        operateEvent.setEventType(sType);
        operateEvent.setEventParam(sJson);
        operateEvent.setEventSet(sOption);




        //原生的需要ui操作的消息传递模式 不需要UI的逻辑在这里直接操作掉
        switch (sType){

            case CommonConst.NATIVE_EVENT_TOAST:{
                //ToastUtil.showToast(new JsonParser().parse(sJson).getAsJsonObject().get("messageInfo").getAsString());



            }
            break;
            case CommonConst.NATIVE_EVENT_BACK:{
                getCurrentActivity().finish();
            }
            case "nativeEventLoadClose":
            case "nativeEventLoadOpen":
            case CommonConst.NATIVE_EVENT_JUMP:
            {
                MiniappEventInstance.getInstance().getNativeDelegate().jumpWtihParam(operateEvent);
            }
                break;
                default:
                {
                Intent intent = new Intent(CommonConst.MINIAPP_NOTIFICATION_EVENT);
                intent.putExtra(CommonConst.NOTIFICATION_EVENT_NAME, sJson);
                getReactApplicationContext().sendBroadcast(intent);
            }
                break;


        }
    }


    /**
     * 小应用调用带返回值的 返回值Promise操作
     * @param sType 类型
     * @param sJson 参数json
     * @param sOption 设置json
     * @param promise
     */
    @ReactMethod
    public void sendNativePromise(
            String sType, String sJson, String sOption,
            Promise promise) {

        Log.d(TAG, "sendNativePromise  type:"+sType+"  json:"+sJson  +"  option:"+sOption);
        try {

            Map<String,String> map=new HashMap<>();

            switch (sType){
                case "nativePromiseToken": {
                    //map.put("token", SharedPreferencesUtils.getStringShareData("token"));

                    map.put("token", MiniappEventInstance.getInstance().getNativeDelegate().upNativeUserInfo().getUserToken());
                }
                    break;
                case "nativePromiseInfo": {
                    map.put("systemType","android");
                   map.put("systemVersion",android.os.Build.VERSION.RELEASE);
                   map.put("systemModel",android.os.Build.MODEL);
                   map.put("deviceBrand",android.os.Build.BRAND);
                }
                break;

            }


            promise.resolve(new Gson().toJson(map));
        } catch (Exception e) {
            promise.reject("error", e);
        }
    }



}
