package com.uhutu.miniapp.mappandroid.miniapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uhutu.miniapp.mappandroid.defines.CommonConst;
import com.uhutu.miniapp.mappandroid.helper.StringUtils;
import com.uhutu.miniapp.mappandroid.model.MiniappStructModel;

public class MiniappViewController extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

    private final static String TAG="MiniappViewController";


    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;


    private Activity mActivity;

    private EventReceiver receiver;
    private Dialog loadingDialog;

    private MiniappStructModel structModel;


    private boolean flagViewShow=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity=this;

        receiver = new EventReceiver();
        IntentFilter filter = new IntentFilter(CommonConst.MINIAPP_NOTIFICATION_EVENT);
        registerReceiver(receiver, filter);


        mReactRootView = new ReactRootView(this);
         structModel=(MiniappStructModel)   getIntent().getSerializableExtra(CommonConst.NITIFICATION_EVENT_STRUCT);

        String sPathDir=  structModel.getBundlePath();

        Log.i(TAG, "viewShow: "+sPathDir);


        ReactInstanceManagerBuilder builder= ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModulePath("index")

                .setCurrentActivity(MiniappViewController.this)
                .addPackage(new MainReactPackage())
                .addPackage(new MiniappManagerPackage())
                //.setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED);


        if(StringUtils.isNotBlank(sPathDir)) {

            builder.setJSBundleFile(sPathDir);
        }
        else{
            builder.setUseDeveloperSupport(true);
        }


        mReactInstanceManager = builder
                .build();



        // The string here (e.g. "MyReactNativeApp") has to match
        // the string in AppRegistry.registerComponent() in index.js

        loadingDialog = createLoadingDialog(MiniappViewController.this, "加载中...");

        if(StringUtils.isBlank(structModel.getLoadModel())){
            viewShow();
        }

        //viewShow();

        // downLoad("http://10.4.92.129:8870/build/zip/demo_one/18071401/android/demo_one_18071401.zip","demo_one_18071401.zip");
    }






    private void viewShow(){


        if(!flagViewShow){
            flagViewShow=true;
        }
        else{
            return ;
        }


        Bundle bundle=new Bundle();
//        structModel.setUserToken(MiniappEventInstance.getInstance().getNativeDelegate().upNativeUserInfo().getUserToken());
        bundle.putString("initapp", new Gson().toJson(structModel));

        mReactRootView.startReactApplication(mReactInstanceManager, structModel.getBundleView(), bundle);



        /*
        这个代码是设置透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        */

        //显示Dialog



        setContentView(mReactRootView);
    }



    public Dialog createLoadingDialog(Activity activity,String sDialogText){




//        ViewGroup view = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        addContentView(view, params);


        //ProgressBar progressBar = new ProgressBar(this);
        //progressBar.setLayoutParams(new ViewGroup.LayoutParams(500, 500));
        //mReactRootView.addView(progressBar);



        final android.app.ProgressDialog pd = new android.app.ProgressDialog(activity);

        //设置提示信息
        pd.setMessage(sDialogText);
        //设置ProgressDialog 是否可以按返回键取消；
        pd.setCancelable(true);
        pd.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        //显示ProgressDialog
        pd.show();
        return pd;

//        return progressBar
    }

    public void closeDialog(Dialog dialog){
        dialog.cancel();
    }




    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
        }
        if (mReactRootView != null) {
            mReactRootView.unmountReactApplication();
        }
    }


    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }








    public class EventReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {





            //mActivity.finish();


            String sJson=intent.getStringExtra(CommonConst.NOTIFICATION_EVENT_NAME);




            Log.d(TAG, "onReceive: "+sJson);

            JsonParser parser = new JsonParser();
            JsonObject jsonObject=  parser.parse(sJson).getAsJsonObject();



            String sType = jsonObject.get(CommonConst.NOTIFICATION_EVENT_TYPE).getAsString();
            switch(sType){

                case "systemLoadView":{
                    viewShow();
                }
                break;

                case "nativeEventBack":
                    if (mReactInstanceManager != null && mActivity!=null){
                        mActivity.finish();
                    }

                    break;
                case "nativeEventJump":


                    Log.d(TAG, "onReceive: "+jsonObject.get("targetUrl").getAsString());

                    //new JumpUtil().jumpUrl(jsonObject.get("targetUrl").getAsString(),getApplicationContext());

                    break;
                case "nativeEventLoadClose":

                    closeDialog(loadingDialog);
                    break;
                case "nativeEventLoadOpen":
                    loadingDialog.show();
                    //createLoadingDialog(mActivity,"加载中...");
                    break;
                default:
                    break;
            }
        }
    }












}