package com.xi.liuliu.topnews.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by zhangxb171 on 2017/8/28.
 */

public class DeviceUtil {
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
}
