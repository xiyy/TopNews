package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/10/11.
 */

public class NotWifiWarnEvent {
    private int mNotWifiWarnTag;

    public NotWifiWarnEvent(int notWifiWarnTag) {
        mNotWifiWarnTag = notWifiWarnTag;
    }

    public int getNotWifiWarnTag() {
        return mNotWifiWarnTag;
    }
}
