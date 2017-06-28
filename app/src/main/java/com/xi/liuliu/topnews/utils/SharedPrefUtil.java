package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.sax.RootElement;

/**
 * Created by liuliu on 2017/6/27.
 */

public class SharedPrefUtil {
    private static SharedPrefUtil mInstance;
    private Context mContext;
    private static final String SP_NAME = "sp_topnews";

    private SharedPrefUtil(Context context) {
        mContext = context;
    }

    public static SharedPrefUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SharedPrefUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharedPrefUtil(context);
                }
            }
        }
        return mInstance;
    }

    public void putString(String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public String getString(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}
