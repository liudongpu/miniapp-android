package com.uhutu.miniapp.mappandroid.model;

import java.io.Serializable;

public class MiniappStructModel implements Serializable {

    private static final long serialVersionUID = 1L;


    private String userToken;

    private String userName;


    private String appVersion;

    private String bundlePath;


    private String bundleView;

    private String envName;

    private String envUrl;


    private String miniInfo;


    /**
     * 加载模型  默认为空  如果为lazy的话则延迟加载显示 一般用于下载完成后的通知
     */
    private String loadModel;


    public String getMiniInfo() {
        return miniInfo;
    }

    public void setMiniInfo(String miniInfo) {
        this.miniInfo = miniInfo;
    }











    public String getBundlePath() {
        return bundlePath;
    }

    public void setBundlePath(String bundlePath) {
        this.bundlePath = bundlePath;
    }

    public String getBundleView() {
        return bundleView;
    }

    public void setBundleView(String bundleView) {
        this.bundleView = bundleView;
    }




    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }



    public String getEnvUrl() {
        return envUrl;
    }

    public void setEnvUrl(String envUrl) {
        this.envUrl = envUrl;
    }




    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }


    public String getLoadModel() {
        return loadModel;
    }

    public void setLoadModel(String loadModel) {
        this.loadModel = loadModel;
    }





}
