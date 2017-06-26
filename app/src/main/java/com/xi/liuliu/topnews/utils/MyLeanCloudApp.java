package com.xi.liuliu.topnews.utils;


import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "J9jGoaYEbyYl1iB4Nu3W0swr-gzGzoHsz", "LSe5Tdf9RP0kHbBsp0FYWrwL");
        AVOSCloud.setDebugLogEnabled(true);
    }
}

