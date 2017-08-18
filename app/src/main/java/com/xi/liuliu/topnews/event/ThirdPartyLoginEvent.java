package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/8/18.
 */

public class ThirdPartyLoginEvent {
    private String mName;
    private String mPortraitUrl;

    public ThirdPartyLoginEvent(String name, String PortraitUrl) {
        this.mName = name;
        this.mPortraitUrl = PortraitUrl;
    }

    public String getName() {
        return mName;
    }

    public String getPortraitUrl() {
        return mPortraitUrl;
    }
}
