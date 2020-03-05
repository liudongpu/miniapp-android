package com.uhutu.miniapp.miniapp_android;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.util.Log;

import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.framework.quinoxless.IInitCallback;
import com.alipay.mobile.framework.quinoxless.QuinoxlessApplicationLike;
import com.alipay.mobile.framework.quinoxless.QuinoxlessFramework;

@Keep
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        QuinoxlessFramework.setup(this, new IInitCallback() {
            @Override
            public void onPostInit() {
                // 在这里开始使用 mPaaS 功能
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        QuinoxlessFramework.init();
    }
}