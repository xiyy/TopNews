package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by zhangxb171 on 2017/8/16.
 */

public class ToastUtil {
    public static void toastInCenter(Context context, int StringId) {
        Toast toast = Toast.makeText(context.getApplicationContext(), StringId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static Toast getTransparentToast(Context context, int StringId, int alpha) {
        Toast toast = Toast.makeText(context, StringId, Toast.LENGTH_SHORT);
        toast.getView().getBackground().setAlpha(alpha);//设置背景透明度
        toast.setGravity(Gravity.CENTER, 0, 0);
        return toast;
    }
}
