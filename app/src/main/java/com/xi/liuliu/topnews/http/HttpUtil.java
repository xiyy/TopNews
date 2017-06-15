package com.xi.liuliu.topnews.http;

import android.text.TextUtils;

import com.xi.liuliu.topnews.constants.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.xi.liuliu.topnews.constants.Constants.MY_APP_KEY;

/**
 * Created by liuliu on 2017/6/13.
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    private Callback mCallback;

    public void requestNews(String channel) {
        if (!TextUtils.isEmpty(channel)) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().get().url(Constants.HOST + "?type=" + channel + "&key=" + Constants.MY_APP_KEY).build();
            Call call = okHttpClient.newCall(request);
            //异步调用
            call.enqueue(mCallback);
        }
    }

    public HttpUtil setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }
}
