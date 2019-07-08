package com.uhutu.miniapp.mappandroid.model;

/**
 * 用户相关信息
 */
public class NativeUserInfo {



    /**
     * 登陆用户姓名
     */
    private String userName;

    /**
     * 用户token信息
     */
    private String userToken;

    /**
     * 用户登录名
     */
    private String loginName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }



}
