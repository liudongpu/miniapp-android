package com.uhutu.miniapp.mappandroid.miniapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    /**
     * 跳转链接
     * @param sUrl
     * @param activity
     */
    public void jumpUrl(String sUrl, Context activity){
        Intent intent = new Intent(activity, MiniappViewController.class);


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        intent.putExtra(CommonConst.NITIFICATION_EVENT_STRUCT,sUrl);


        activity.startActivity(intent);


    }





    }
