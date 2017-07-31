package com.xi.liuliu.topnews.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.bean.FavouriteNews;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.dialog.ShareDialog;
import com.xi.liuliu.topnews.event.NewsPhotoUrlsEvent;
import com.xi.liuliu.topnews.impl.NewsWebViewClient;
import com.xi.liuliu.topnews.utils.DBDao;
import com.xi.liuliu.topnews.utils.HtmlUtil;

import de.greenrobot.event.EventBus;

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
    private ProgressBar mProgressBar;
    private NewsItem mNewsItem;
    private DBDao mDBDao;
    private boolean isFavouriteNews;
    private Bitmap mShareThumb;
    private NewsWebViewClient mWebViewClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.web_view_news_detail);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mLeftGoBack = (ImageView) findViewById(R.id.left_back_icon);
        mMore = (ImageView) findViewById(R.id.news_detail_more_icon);
        mMyFavourite = (ImageView) findViewById(R.id.favorite_icon_news_detail);
        mShare = (ImageView) findViewById(R.id.share_icon_news_detail);
        mLeftGoBack.setOnClickListener(this);
        mShare.setOnClickListener(this);
        mMore.setOnClickListener(this);
        mMyFavourite.setOnClickListener(this);
        mNewsItem = (NewsItem) getIntent().getExtras().getParcelable("newsItem");
        mShareThumb = (Bitmap) getIntent().getExtras().getParcelable("shareThum");
        if (mDBDao == null) {
            mDBDao = new DBDao(this);
        }
        isFavouriteNews = mDBDao.isFavouriteExist(new FavouriteNews(mNewsItem));
        if (isFavouriteNews) {
            mMyFavourite.setImageDrawable(getResources().getDrawable(R.drawable.favorite_icon_selected));
        }
        HtmlUtil.getImagesUrlFromHtml(mNewsItem.getUrl());//获取HTML源代码
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    if (mProgressBar.getVisibility() != View.VISIBLE) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        mWebViewClient = new NewsWebViewClient(this);
        mWebView.setWebViewClient(mWebViewClient);
        loadNews(mWebView, mNewsItem.getUrl());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_icon_news_detail:
                new ShareDialog(this, mNewsItem, mShareThumb).show();
                break;
            case R.id.news_detail_more_icon:
                new ShareDialog(this, mNewsItem, mShareThumb).show();
                break;
            case R.id.favorite_icon_news_detail:
                myFavourite();
                break;
            case R.id.left_back_icon:
                finish();
                break;
        }
    }

    private void loadNews(WebView webView, String url) {
        if (webView != null && !TextUtils.isEmpty(url)) {
            Log.i(TAG, "URL:" + url);
            webView.loadUrl(url);
        }
    }

    private void myFavourite() {
        FavouriteNews favouriteNews = new FavouriteNews(mNewsItem);
        if (isFavouriteNews) {
            boolean isDeleted = mDBDao.deleteFavourite(favouriteNews);
            if (isDeleted) {
                isFavouriteNews = false;
                mMyFavourite.setImageDrawable(getResources().getDrawable(R.drawable.favorite_icon));
                Toast.makeText(this, R.string.favourite_cancle, Toast.LENGTH_SHORT).show();
            }
        } else {
            boolean insertFavourite = mDBDao.insertFavourite(favouriteNews);
            if (insertFavourite) {
                isFavouriteNews = true;
                mMyFavourite.setImageDrawable(getResources().getDrawable(R.drawable.favorite_icon_selected));
                Toast.makeText(this, R.string.favourite_success, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.zoomout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * HtmlUtil获取源代码后，通过mWebViewClient交给PhotoBrowserActivity
     *
     * @param event
     */
    public void onEventMainThread(NewsPhotoUrlsEvent event) {
        if (event != null) {
            String[] photoUrls = event.getHtmlCode();
            mWebViewClient.setImagesUrl(photoUrls);
        }
    }
}
