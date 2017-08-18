package com.xi.liuliu.topnews.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xi.liuliu.topnews.dialog.PhotoBrowserDialog;

/**
 * Created by zhangxb171 on 2017/7/28.
 */

public class NewsWebViewClient extends WebViewClient {
    private static final String TAG = "NewsWebViewClient";
    private boolean isPageFinished;
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
        isPageFinished = true;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    /**
     * 点击图片时拦截URL
     *
     * @param view
     * @param url
     * @return true表明，针对点击请求的URL，不执行跳转，WebViewClient自己处理点击请求的URL
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (isPageFinished) {
            if (mImagesUrl != null) {
                for (String imageUrl : mImagesUrl) {
                    if (!TextUtils.isEmpty(imageUrl)) {
                        if (imageUrl.equals(url)) {
                            new PhotoBrowserDialog(mContext, imageUrl, mImagesUrl).show();
                        }
                    }
                }
            }
        }
        return true;
    }
}
