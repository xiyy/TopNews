package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/8/18.
 */

public class LoginInfoEvent {
    private String mNickName;
    private String mPortraitUrl;

    public LoginInfoEvent(String nickName, String PortraitUrl) {
        this.mNickName = nickName;
        this.mPortraitUrl = PortraitUrl;
    }

    public String getName() {
        return mNickName;
    }

    public String getPortraitUrl() {
        return mPortraitUrl;
    }
}
