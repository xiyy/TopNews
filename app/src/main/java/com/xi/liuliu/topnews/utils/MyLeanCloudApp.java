package com.xi.liuliu.topnews.utils;


import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.xi.liuliu.topnews.constants.Constants;

public class MyLeanCloudApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "J9jGoaYEbyYl1iB4Nu3W0swr-gzGzoHsz", "LSe5Tdf9RP0kHbBsp0FYWrwL");
        AVOSCloud.setDebugLogEnabled(true);
        WbSdk.install(this, new AuthInfo(this, Constants.WEI_BO_APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
        NetWorkUtil.requestIP(this);
        LocationUtil.getLongitudeAndLatitude(this);
    }
}

