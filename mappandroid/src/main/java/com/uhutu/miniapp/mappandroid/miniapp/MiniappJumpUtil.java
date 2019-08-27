package com.uhutu.miniapp.mappandroid.miniapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.uhutu.miniapp.mappandroid.defines.CommonConst;
import com.uhutu.miniapp.mappandroid.face.IMiniappJumpUtil;
import com.uhutu.miniapp.mappandroid.helper.DownloadUtil;
import com.uhutu.miniapp.mappandroid.helper.StringUtils;
import com.uhutu.miniapp.mappandroid.model.MiniappStructModel;
import com.uhutu.miniapp.mappandroid.model.MiniappInfoModel;
import com.uhutu.miniapp.mappandroid.model.NativeAppInfo;
import com.uhutu.miniapp.mappandroid.model.NativeUserInfo;

public class MiniappJumpUtil implements IMiniappJumpUtil {


    private final  static String TAG="MiniappJumpUtil";


    private String localJumpUrl="";



    private void toastMessage(final Activity activity,final String sMessage){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast toast=Toast.makeText(activity,sMessage,Toast.LENGTH_SHORT);
                toast.show();


            }
        });
    }


    public void sendNativeNotice(String sListencerName, Map<String,String> map){
        map.put("miniapp_lisenter_name",sListencerName);
        Message msg=new Message();
        msg.obj=map;
        MiniappNoticeBridge.mHandler.sendMessage(msg);
    }




    /**
     * 跳转链接
     * @param sUrl
     * @param activity
     */
    public void jumpUrl(String sUrl, Context activity){




        if(!MiniappLockSystem.getInstance().checkActivityLock(sUrl)){
            return;
        }



        localJumpUrl=sUrl;

        int iIndex=sUrl.indexOf(":");




        if(iIndex>-1){


            String sStart= StringUtils.substringBefore(sUrl,":");

            switch (sStart){

                case "icome-miniapp":


                    targetMiniapp(sUrl,activity);
                    break;
                case "debug-miniapp":


                    MiniappStructModel structModel=new MiniappStructModel();
                    structModel.setBundlePath("");
                    structModel.setBundleView("MiniappPoject");
                    structModel.setEnvName("alpha");
                    structModel.setEnvUrl(sUrl);
                    structModel.setMiniInfo("{\"id\":\"\",\"code\":0,\"version\":\"0.0.0\"}");

                    openMiniapp(structModel,activity);
                    break;


                case "icome-native":
                case "icome-h5":
                case "icome-url":

                default:
                    Log.e(TAG, "jumpUrl: not exist start:"+sStart);
                    break;

            }

        }
        else{
            Log.e(TAG, "jumpUrl: not exist split:"+sUrl );
        }

    }


    /**
     * 跳转小应用的代码逻辑
     * @param sUrl
     * @param activity
     */
    private void targetMiniapp(final String sUrl,final Context activity){


        final MiniappStructModel structModel=new MiniappStructModel();



        String sTargetId=StringUtils.substringBetween(sUrl,"://",".app");
        String sRequestUrl= MiniappEventInstance.getInstance().getNativeDelegate().upNativeConfigInfo().getBaseUrl().replace("/#/","/"+sTargetId+"/");


        String sConfigPath= MiniappEventInstance.getInstance().getNativeDelegate().upNativeConfigInfo().getLocalPathDir();
        if(StringUtils.isBlank(sConfigPath)){
            sConfigPath=activity.getApplicationContext().getFilesDir().getAbsolutePath() + File.separator;
        }

        final String sLocalPath=sConfigPath;

        try {
            sRequestUrl=sRequestUrl+"?system_source="+ URLEncoder.encode(sUrl,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).build();
        //2构造Request,
        //builder.get()代表的是get请求，url方法里面放的参数是一个网络地址
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(sRequestUrl).build();

        //3将Request封装成call
        Call call = okHttpClient.newCall(request);

        //4，执行call，这个方法是异步请求数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //失败调用
                Log.e(TAG, "onFailure: " ,e);


                //toastMessage(activity,"网络请求失败");






            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                String str = response.body().string();
                Log.d(TAG, "onResponse: " +str);

                //JSONObject jsonObject= JSON.parseObject(str);

                //JsonObject jsonObject= new JsonParser().parse(str).getAsJsonObject();


                final  MiniappInfoModel upgradeModel=new Gson().fromJson(str,MiniappInfoModel.class);


                String sBasePathName= sLocalPath+ CommonConst.STORAGE_START_NAME;

                String sBundlePath=sBasePathName+ File.separator+"bundle"+ File.separator+upgradeModel.getFolder();


                //localFilePath=sBundlePath+File.separator+jsonObject.getString("file");


                structModel.setBundlePath(sBundlePath+ File.separator+upgradeModel.getFile());

                structModel.setBundleView(upgradeModel.getView());
                structModel.setEnvName(upgradeModel.getEnv());
                structModel.setEnvUrl(sUrl);
                structModel.setMiniInfo(str);


                File fLocalBundle=new File(structModel.getBundlePath());

                //这里简单判断文件存在  后续添加Sqlite的校验代码
                if(!fLocalBundle.exists()){

                    String sZipFileName=StringUtils.substringAfterLast(upgradeModel.getUrl(),"/");


                    downloadFile(structModel,activity,upgradeModel.getUrl(),sBasePathName+ File.separator+"zip",sZipFileName,sBundlePath);


                }
                else{


                    openMiniapp(structModel,activity);

                }

            }
        });
    }




    private void downloadFile(final MiniappStructModel structModel,final  Context activity,final String sDownloadUrl,final String sZipFolder,final String sZipFile,final String sBundleFolder){


        File folder = new File(sBundleFolder);
        folder.mkdirs();



        if(folder.exists()) {

            DownloadUtil.get().download(sDownloadUrl, sZipFile, sZipFolder, new DownloadUtil.OnDownloadListener() {

                @Override
                public void onDownloading(int progress) {

                }

                @Override
                public void onDownloadSuccess() {


                    decompression(sZipFolder + File.separator + sZipFile, sBundleFolder);


                    Log.i(TAG, "downloadFile:onDownloadSuccess: " + sZipFolder + File.separator + sZipFile);


                    openMiniapp(structModel,activity);

                }

                @Override
                public void onDownloadFailed() {

                }

            });
        }
        else{
            Log.e(TAG, "downloadFile:exist: "+folder.exists()+folder.getAbsolutePath());
        }
    }





    private void openMiniapp(MiniappStructModel structModel,Context activity){
        Intent intent = new Intent(activity, MiniappViewController.class);

        NativeUserInfo userInfo= MiniappEventInstance.getInstance().getNativeDelegate().upNativeUserInfo();

        NativeAppInfo appInfo= MiniappEventInstance.getInstance().getNativeDelegate().upNativeAppInfo();

        structModel.setUserToken( userInfo.getUserToken());
        structModel.setUserName( userInfo.getUserName());

        structModel.setAppVersion(appInfo.getAppVersion());

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra(CommonConst.NITIFICATION_EVENT_STRUCT,structModel);


        activity.startActivity(intent);

        MiniappLockSystem.getInstance().removeActivityLock(structModel.getEnvUrl());

    }







    /**
     * 解压
     */
    public static void decompression(String sZipFile, String sTargetFolder) {

        try {

            ZipInputStream inZip = new ZipInputStream(new FileInputStream(sZipFile));
            ZipEntry zipEntry;
            String szName;
            try {
                while ((zipEntry = inZip.getNextEntry()) != null) {

                    szName = zipEntry.getName();
                    if (zipEntry.isDirectory()) {

                        szName = szName.substring(0, szName.length() - 1);
                        File folder = new File(sTargetFolder + File.separator + szName);
                        folder.mkdirs();

                    } else {

                        File file1 = new File(sTargetFolder + File.separator + szName);
                        boolean s = file1.createNewFile();
                        FileOutputStream fos = new FileOutputStream(file1);
                        int len;
                        byte[] buffer = new byte[1024];

                        while ((len = inZip.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                            fos.flush();
                        }

                        fos.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            inZip.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}