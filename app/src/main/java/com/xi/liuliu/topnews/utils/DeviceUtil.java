package com.xi.liuliu.topnews.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by zhangxb171 on 2017/8/28.
 */

public class DeviceUtil {
    private static final String NOTIFICATION_STATE_OPEN = "打开";
    private static final String NOTIFICATION_STATE_CLOSE = "关闭";
    private static final String UN_KNOWN = "未知";

    /**
     * 得到屏幕高度，单位是像素
     *
     * @param activity
     * @return
     */
    public static int getHeightPixel(Activity activity) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    /**
     * 得到屏幕宽度，单位是像素
     *
     * @param activity
     * @return
     */
    public static int getWidthPixel(Activity activity) {
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }

    /**
     * 手机状态栏的高度，及信号、电量、时间一栏的高度，单位是像素
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    /**
     * dp转成px
     *
     * @param dpValue
     * @param context
     * @return
     */
    public static int dip2px(float dpValue, Context context) {

        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转成dp
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static int px2dip(float pxValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static String getBrand() {
        return Build.BRAND;

    }

    public static String getModel() {
        return Build.MODEL;
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getCpuType() {
        return Build.CPU_ABI;
    }

    public static String getNotificationState(Context context) {
        //Android 4.4及以上使用反射获取APP的通知状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            ApplicationInfo appInfo = context.getApplicationInfo();
            String pkg = context.getApplicationContext().getPackageName();
            int uid = appInfo.uid;
            Class appOpsClass;
            try {
                appOpsClass = Class.forName(AppOpsManager.class.getName());
                Method checkOpNoThrowMethod = appOpsClass.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
                Field opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION");
                int value = (int) opPostNotificationValue.get(Integer.class);
                boolean result = ((int) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
                if (result) {
                    return NOTIFICATION_STATE_OPEN;
                } else {
                    return NOTIFICATION_STATE_CLOSE;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return UN_KNOWN;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB_MR2) {
            //4.1-4.3暂时无法获取通知状态
            return UN_KNOWN;
        } else {
            //4.0以及4.0以下
            return NOTIFICATION_STATE_OPEN;
        }
    }

    //panda App相关，guid表示设备的唯一标识
    public static String getGuid(Context context) {
        String deviceId = "";
        try {
            TelephonyManager androidId = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
            String methods = androidId.getDeviceId();
            deviceId = deviceId + "IMEI No :";
            if (methods != null) {
                deviceId = deviceId + methods;
            }
        } catch (Exception var9) {
        }
        Class androidId1;
        try {
            androidId1 = Class.forName("android.os.SystemProperties");
            Method methods1 = androidId1.getMethod("get", new Class[]{String.class, String.class});
            String params = (String) ((String) methods1.invoke(androidId1, new Object[]{"ro.serialno", "unknown"}));
            deviceId = deviceId + " serial :";
            if (params != null) {
                deviceId = deviceId + params;
            }
        } catch (Exception var8) {
        }

        try {
            androidId1 = Class.forName("android.os.SystemProperties");
            Method[] methods2 = androidId1.getMethods();
            Object[] params1 = new Object[]{new String("ro.serialno"), new String("Unknown")};
            String serialnum2 = (String) ((String) methods2[2].invoke(androidId1, params1));
            deviceId = deviceId + " serial2 :";
            if (serialnum2 != null) {
                deviceId = deviceId + serialnum2;
            }
        } catch (Exception var7) {
        }

        try {
            String androidId2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (androidId2 != null) {
                deviceId = deviceId + " android ID :";
                deviceId = deviceId + androidId2;
            }
        } catch (Exception var6) {
        }
        return Md5Encoder.encode(deviceId);
    }


}
