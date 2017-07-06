package com.xi.liuliu.topnews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.xi.liuliu.topnews.R;

public class BrokeNewsActivity extends AppCompatActivity {
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broke_news);
        initWebView();
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.web_view_activity_broke_news);
        mWebView.getSettings().setJavaScriptEnabled(true);

    }
}
