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
}
