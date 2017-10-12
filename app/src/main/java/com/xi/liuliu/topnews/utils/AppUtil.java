package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by zhangxb171 on 2017/10/12.
 */

public class AppUtil {
    public static int getVersionCode(Context mContext) {
        int versionCode = 0;
        try {
            //获取软件版本号，对应build.gradle下android:versionCode
            versionCode = mContext.getPackageManager().
                    getPackageInfo(mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static String getVersionName(Context context) {
        String verName = "";
        try {
            //获取版本名称，对应build.gradle下android:versionName
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
