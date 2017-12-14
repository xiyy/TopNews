package com.xi.liuliu.topnews.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import java.util.List;

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

    public static boolean isSomeAppInstalled(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            throw new RuntimeException("AppUtil isAppInstalled 参数为null");
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packageInfo == null ? false : true;
    }

    public static String getSomeAppVersion(Context context, String packageName) {
        if (context == null || TextUtils.isEmpty(packageName)) {
            throw new RuntimeException("AppUtil getSomeAppVersion 参数为null");
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    public static boolean isSomeProcessRunning(Context context, String processName) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> list = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : list) {
            if (info.service.getClassName().equals(processName)) {
                return true;
            }
        }
        return false;
    }
}
