package com.uhutu.miniapp.miniapp_android;

import com.uhutu.miniapp.mappandroid.face.MiniappEventDelegate;
import com.uhutu.miniapp.mappandroid.miniapp.MiniappJumpUtil;
import com.uhutu.miniapp.mappandroid.model.NativeAppInfo;
import com.uhutu.miniapp.mappandroid.model.NativeConfigInfo;
import com.uhutu.miniapp.mappandroid.model.NativeOperateEvent;
import com.uhutu.miniapp.mappandroid.model.NativeUserInfo;

import java.util.HashMap;
import java.util.Map;

public class MiniappDelegate implements MiniappEventDelegate {

    private final static String TAG="MiniappDelegate";

    @Override
    public NativeUserInfo upNativeUserInfo() {

        NativeUserInfo userInfo=new NativeUserInfo();
        userInfo.setLoginName("simpleLogin");
        userInfo.setUserName("simpleUser");
        userInfo.setUserToken("463d6ced80ba74912dad91b3ed535cc8");

        return userInfo;
    }

    @Override
    public NativeAppInfo upNativeAppInfo() {

        NativeAppInfo appInfo=new NativeAppInfo();
        appInfo.setAppVersion("1.0.0");
        appInfo.setOsVersion("7.0.1");

        return appInfo;
    }

    @Override
    public NativeConfigInfo upNativeConfigInfo() {
        NativeConfigInfo configInfo=new NativeConfigInfo();
        configInfo.setBaseUrl("http://icomeminiapp.srnpr.com/mapps/version/#/beta_android.json");
        //configInfo.setBaseUrl("http://10.4.143.141:8870/build/version/#/alpha_android.json");
        //configInfo.setLocalPathDir(Environment.getExternalStorageDirectory().getAbsolutePath()+  File.separator);

        return configInfo;
    }

    @Override
    public boolean onNativeOperateEvent(NativeOperateEvent event) {
        return true;
    }

    @Override
    public void jumpWtihParam(NativeOperateEvent event) {

        Map<String,String> map=new HashMap<>();
        map.put("aaa","bbb");

        new MiniappJumpUtil().sendNativeNotice("notice_abc",map);

    }

}
