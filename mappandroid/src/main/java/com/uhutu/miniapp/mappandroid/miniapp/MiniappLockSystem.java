package com.uhutu.miniapp.mappandroid.miniapp;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class MiniappLockSystem {


    private final static ConcurrentHashMap<String,Long> currentMap=new ConcurrentHashMap<String,Long>();



    private final static   MiniappLockSystem instance=new MiniappLockSystem();


    public static MiniappLockSystem getInstance(){
        return instance;
    }


    /**
     * 判断是否页面能打开 注意 这里是风骚的实现代码 不是常规锁  是时间戳的差值来判断打开的
     * @param sKey
     * @return
     */
    public boolean checkActivityLock(String sKey){


        boolean bReturn=false;
        long lTimestamp=new Date().getTime();


        if(currentMap.containsKey(sKey)){

            //判断时间差3秒以上
            if(lTimestamp-currentMap.get(sKey)>2000){
                //currentMap.put(sKey,lTimestamp);
                bReturn=true;
            }

        }
        else{
            //currentMap.put(sKey,lTimestamp);

            bReturn=true;
        }

        currentMap.put(sKey,lTimestamp);





        return bReturn;

    }

    /**
     * 删除锁key  风骚实现
     * @param sKey
     * @return
     */
    public boolean removeActivityLock(String sKey){

        //这个锁暂时不删除key  只是将当前时间刷新map  交由时间差来判断是否能打开
        //long lTimestamp=new Date().getTime();
        //currentMap.put(sKey,lTimestamp);
        checkActivityLock(sKey);
        return true;
    }






}
