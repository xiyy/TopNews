package com.xi.liuliu.topnews.http;

import android.text.TextUtils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by liuliu on 2017/6/13.
 */

public class HttpUtil {
    private static final String TAG = "HttpUtil";
    private static final String HOST = "http://v.juhe.cn/toutiao/index";
    private static final String MY_APP_KEY = "6a0f8f312dfdc046f4132d0a6761ecc6";
    private Callback mCallback;

    public void requestNews(String channel) {
        if (!TextUtils.isEmpty(channel)) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().get().url(HOST + "?type=" + channel + "&key=" + MY_APP_KEY).build();
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
