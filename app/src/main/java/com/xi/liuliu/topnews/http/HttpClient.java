package com.xi.liuliu.topnews.http;

import android.text.TextUtils;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.xi.liuliu.topnews.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
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

    public void requestLoginSmsCode(String phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("mobilePhoneNumber", phoneNumber);
                jsonObject.put("ttl", 4000);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
            Request.Builder builder = new Request.Builder().url(Constants.LOGIN_SMS_CODE_HOST);
            builder.addHeader("X-LC-Id", Constants.LEAN_CLOUND_APP_ID);
            builder.addHeader("X-LC-Key", Constants.LEAN_CLOUND_APP_KEY);
            builder.addHeader("Content-Type", "application/json");
            builder.post(requestBody);
            Request request = builder.build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(mCallback);
        }
    }

    public void verifySmsCode(String smsCode, String phoneNumber) {
        if (!TextUtils.isEmpty(smsCode) && !TextUtils.isEmpty(phoneNumber)) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder().get().url(Constants.VERIFY_SMS_CODE + "/" + smsCode + "?mobilePhoneNumber=" + phoneNumber);
            builder.addHeader("X-LC-Id", Constants.LEAN_CLOUND_APP_ID);
            builder.addHeader("X-LC-Key", Constants.LEAN_CLOUND_APP_KEY);
            builder.addHeader("Content-Type", "application/json");
            Request request = builder.build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(mCallback);
        }
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
}
