package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

public class UserAgreementActivity extends AppCompatActivity {
    private WebView mWebView;
    private TextView mClose;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        mClose = (TextView) findViewById(R.id.go_back_activity_user_agreement);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar_user_agreement);
        mProgressBar.setMax(100);
        mWebView = (WebView) findViewById(R.id.web_view_activity_user_agreement);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
        mWebView.loadUrl("file:///android_asset/user_agreement.html");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.removeAllViews();
        mWebView.destroy();
    }
}
