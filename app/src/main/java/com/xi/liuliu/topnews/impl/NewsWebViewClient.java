package com.xi.liuliu.topnews.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xi.liuliu.topnews.dialog.PhotoBrowserDialog;

import io.vov.vitamio.utils.Log;

/**
 * Created by zhangxb171 on 2017/7/28.
 */

public class NewsWebViewClient extends WebViewClient {
    private static final String TAG = "NewsWebViewClient";
    private boolean hasImagesUrlLoadFinished;
    private String[] mImagesUrl;
    private Context mContext;

    public NewsWebViewClient(Context context) {
        mContext = context;
    }

    public void setImagesUrl(String[] imagesUrl) {
        mImagesUrl = imagesUrl;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        hasImagesUrlLoadFinished = true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    /**
     * 加载资源时回调；点击图片时会发生跳转，此时拦截跳转的URL，该URL就是图片的URL
     *
     * @param view
     * @param url
     */
    @Override
    public void onLoadResource(WebView view, String url) {
        Log.i(TAG, "onLoadResource-->" + url);
        if (hasImagesUrlLoadFinished) {
            for (String imageUrl : mImagesUrl) {
                if (!TextUtils.isEmpty(imageUrl)) {
                    if (imageUrl.equals(url)) {
                        view.goBack();
                        new PhotoBrowserDialog(mContext, imageUrl, mImagesUrl).show();
                    }
                }
            }
        }
    }
}
