package com.uhutu.miniapp.mappandroid.model;

/**
 * 原生配置相关
 */
public class NativeConfigInfo {

    /**
     * 基本URL地址 用于下载
     */
    private String baseUrl;

    /**
     * 本地文件存储路径
     */
    private String localPathDir;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLocalPathDir() {
        return localPathDir;
    }

    public void setLocalPathDir(String localPathDir) {
        this.localPathDir = localPathDir;
    }




}
