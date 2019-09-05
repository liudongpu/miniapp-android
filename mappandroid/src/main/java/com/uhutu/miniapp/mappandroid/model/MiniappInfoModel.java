package com.uhutu.miniapp.mappandroid.model;


/**
 *  升级配置相关
 *
 *              "id": "demo_one",
 *               "code": 18092107,
 *               "version": "1.0.8",
 *               "name": "aaa",
 *               "group": "demo",
 *               "feature": "one",
 *               "info": "abc",
 *               "url": "http://10.4.143.96:8870/build/zip/demo_one/18092107/android/demo_one_18092107.zip",
 *               "file": "index.android.bundle",
 *               "env": "alpha",
 *               "view": "MiniappPoject",
 *               "folder": "demo_one_18092107"
 */
public class MiniappInfoModel {


    /**
     * 小应用编号  一般由[group]_[name] 组成
     */
    private String id;

    /**
     * 版本编码  用于版本升级标记的判断
     */
    private int code;

    /**
     * 版本号  用于显示
     */
    private String version;
    /**
     * 名字
     */
    private String name;
    /**
     * 分组
     */
    private String group;
    /**
     * 功能
     */
    private String feature;
    /**
     * 描述信息
     */
    private String info;
    /**
     * 压缩包下载地址
     */
    private String url;
    /**
     * 文件名
     */
    private String file;
    /**
     * 环境标记  alpha,beta,preview,release
     */
    private String env;
    /**
     * 初始视图  一般写死为MiniappPoject
     */
    private String view;
    /**
     * 文件夹地址
     */
    private String folder;

    /**
     * 备用地址 这个字段手动设置，一般为空  如果存在这个字段并且值不为空时 优先调用该操作冗余
     */
    private String back;

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }




}
