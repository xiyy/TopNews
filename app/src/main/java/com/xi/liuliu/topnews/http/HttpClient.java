package com.xi.liuliu.topnews.http;

import android.text.TextUtils;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.xi.liuliu.topnews.constants.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 * Created by liuliu on 2017/6/13.
 */

public class HttpClient {
    public static final String TAG = "HttpClient";
    private Callback mCallback;

    public void requestNews(String channel) {
        if (!TextUtils.isEmpty(channel)) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().get().url(Constants.HOST + "?type=" + channel + "&key=" + Constants.JU_HE_APP_KEY).build();
            Call call = okHttpClient.newCall(request);
            //异步调用
            call.enqueue(mCallback);
        }
    }

    public HttpClient setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    public void requestWeiboLogin(Oauth2AccessToken accessToken) {
        if (accessToken == null) {
            throw new NullPointerException("HttpClient.requestWeiboLogin(),accessToken is null ");
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().get().url(Constants.WEI_BO_INFO_URL + "?access_token=" + accessToken.getToken() + "&uid=" + accessToken.getUid());
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(mCallback);
    }

    public void requestAddresses(String latitude, String longitude) {
        if (TextUtils.isEmpty(latitude) || TextUtils.isEmpty(longitude)) {
            throw new NullPointerException("HttpClient.requestAddresses(),latitude is null or longitude is null");
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().get().url(Constants.GET_NEARBY_ADDRESSES_URL + "&location=" + latitude + "," + longitude + "&output=json&pois=1");
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(mCallback);
    }

    public void requestIP(String encryptGuid) {
        //表单提交
        RequestBody formBody = new FormBody.Builder().add("parameter", encryptGuid).build();
        Request request = new Request.Builder().url(Constants.IP_PANDA).addHeader("Content-Type", "application/x-www-form-urlencoded").post(formBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(mCallback);
    }
}
