package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/8/18.
 * MainActivity接收LoginEvent，用于更新底部导航栏“我的”UI
 * MineFragment接收LoginEvent，用于更新顶部用户信息（手机、微信、QQ、微博登录）
 */

public class LoginEvent {
    public static final int LOGIN_PHONE = 0;
    public static final int LOGIN_WEIXIN = 1;
    public static final int LOGIN_QQ = 2;
    public static final int LOGIN_WEIBO = 3;
    private int mLoginType;
    private String mNickName;
    private String mPortraitUrl;

    public LoginEvent(int loginType, String nickName, String PortraitUrl) {
        this.mLoginType = loginType;
        this.mNickName = nickName;
        this.mPortraitUrl = PortraitUrl;
    }

    public String getName() {
        return mNickName;
    }

    public String getPortraitUrl() {
        return mPortraitUrl;
    }

    public int getLoginType() {
        return mLoginType;
    }
}
