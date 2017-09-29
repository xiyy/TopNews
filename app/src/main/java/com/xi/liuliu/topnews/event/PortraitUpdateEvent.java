package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/9/29.
 */

public class PortraitUpdateEvent {
    private String mPortraitPath;

    public PortraitUpdateEvent(String path) {
        mPortraitPath = path;
    }

    public String getPortraitPath() {
        return mPortraitPath;
    }
}
