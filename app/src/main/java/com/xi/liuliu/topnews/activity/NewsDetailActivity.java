package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/15.
 */

public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "NewsDetailActivity";
    private WebView mWebView;
    private ImageView mLeftGoBack;
    private ImageView mMore;
    private ImageView mMyFavourite;
    private ImageView mShare;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_view_news_detail);
        mLeftGoBack = (ImageView) findViewById(R.id.left_back_icon);
        mMore = (ImageView) findViewById(R.id.news_detail_more_icon);
        mMyFavourite = (ImageView) findViewById(R.id.favorite_icon_news_detail);
        mShare = (ImageView) findViewById(R.id.share_icon_news_detail);
        mLeftGoBack.setOnClickListener(this);
        loadNews(mWebView, getIntent().getStringExtra("news_url"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_back_icon:
                finish();
                break;
        }
    }

    private void loadNews(WebView webView, String url) {
        if (webView != null && !TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
    }
}
