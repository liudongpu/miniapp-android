package com.uhutu.miniapp.mappandroid.support;

import android.content.Context;

import com.uhutu.miniapp.mappandroid.face.MiniappNativeDelegate;
import com.uhutu.miniapp.mappandroid.service.GlobalService;
import com.uhutu.miniapp.mappandroid.service.JumpPage;


/**
 * 这个是原生APP调用小应用的逻辑接口，单例
 */
public class MiniappSupport {




    private final static MiniappSupport support=new MiniappSupport();

    /**
     * 获取单例对象
     * @return
     */
    public static MiniappSupport getInstance(){
        return support;
    }


    /**
     * 跳转小应用的操作类
     * @param sUrl 小应用的地址
     * @param iMiniappDelegate 回调类，一般可以静态
     * @param activity context对象
     */
    public void jumpUrl(String sUrl, MiniappNativeDelegate iMiniappDelegate, Context activity){


        GlobalService.getInstance().setNativeDelegate(iMiniappDelegate);

        new JumpPage().jumpUrl(sUrl,activity);
    }








}
