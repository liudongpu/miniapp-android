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


    private String id;

    private int code;
    private String version;
    private String name;
    private String group;
    private String feature;
    private String info;
    private String url;
    private String file;
    private String env;
    private String view;
    private String folder;


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
