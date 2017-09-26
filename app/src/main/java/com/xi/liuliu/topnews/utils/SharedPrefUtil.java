package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LoginEvent;

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

    public void putInt(String key, int value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    public String getString(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public int getInt(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SP_NAME, mContext.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public void saveLoginStateWithWeibo(String nickName, String portraitUrl) {
        SharedPrefUtil.getInstance(mContext).putBoolean(Constants.LOGIN_SP_KEY, true);
        SharedPrefUtil.getInstance(mContext).putString(Constants.LOGIN_TYPE_SP_KEY, String.valueOf(LoginEvent.LOGIN_WEIBO));
        SharedPrefUtil.getInstance(mContext).putString(Constants.WEI_BO_NICK_NAME_SP_KEY, nickName);
        SharedPrefUtil.getInstance(mContext).putString(Constants.WEI_BO_Portrait_URL, portraitUrl);
    }

    public void savaLoginStateWithPhone(String phoneNumber) {
        SharedPrefUtil.getInstance(mContext).putBoolean(Constants.LOGIN_SP_KEY, true);
        SharedPrefUtil.getInstance(mContext).putString(Constants.LOGIN_TYPE_SP_KEY, String.valueOf(LoginEvent.LOGIN_PHONE));
        SharedPrefUtil.getInstance(mContext).putString(Constants.USER_PHONE_NUMBER_SP_KEY, phoneNumber);
    }
}
