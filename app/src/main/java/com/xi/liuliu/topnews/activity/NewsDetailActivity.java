package com.xi.liuliu.topnews.activity;

import android.content.Intent;
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
import android.widget.RelativeLayout;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.bean.FavouriteNews;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.dialog.ShareDialog;
import com.xi.liuliu.topnews.event.NewsPhotoUrlsEvent;
import com.xi.liuliu.topnews.event.WeiboShareEvent;
import com.xi.liuliu.topnews.impl.NewsWebViewClient;
import com.xi.liuliu.topnews.utils.DBDao;
import com.xi.liuliu.topnews.utils.HtmlUtil;
import com.xi.liuliu.topnews.utils.ToastUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/15.
 */

public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener, WbShareCallback {
    private static final String TAG = "NewsDetailActivity";
    private WebView mWebView;
    private RelativeLayout mLeftGoBack;
    private RelativeLayout mMore;
    private RelativeLayout mMyFavourite;
    private ImageView mMyFavouriteImg;
    private RelativeLayout mShare;
    private ProgressBar mProgressBar;
    private NewsItem mNewsItem;
    private DBDao mDBDao;
    private boolean isFavouriteNews;
    private Bitmap mShareThumb;
    private NewsWebViewClient mWebViewClient;
    private SsoHandler mSsoHandler;
    private WbShareHandler mWbShareHandler;

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
        mLeftGoBack = (RelativeLayout) findViewById(R.id.go_back_rl_activity_detail_news);
        mMore = (RelativeLayout) findViewById(R.id.more_rl_activity_detail_news);
        mMyFavourite = (RelativeLayout) findViewById(R.id.favorite_rl_activity_detail_news);
        mMyFavouriteImg = (ImageView) findViewById(R.id.favorite_img_rl_activity_detail_news);
        mShare = (RelativeLayout) findViewById(R.id.share_rl_activity_detail_news);
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
            mMyFavouriteImg.setImageDrawable(getResources().getDrawable(R.drawable.favorite_icon_selected));
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
            case R.id.share_rl_activity_detail_news:
                new ShareDialog(this, mNewsItem, mShareThumb).show();
                break;
            case R.id.more_rl_activity_detail_news:
                new ShareDialog(this, mNewsItem, mShareThumb).show();
                break;
            case R.id.favorite_rl_activity_detail_news:
                myFavourite();
                break;
            case R.id.go_back_rl_activity_detail_news:
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
                mMyFavouriteImg.setImageDrawable(getResources().getDrawable(R.drawable.favorite_icon));
                ToastUtil.toastInCenter(this, R.string.favourite_cancle);
            }
        } else {
            boolean insertFavourite = mDBDao.insertFavourite(favouriteNews);
            if (insertFavourite) {
                isFavouriteNews = true;
                mMyFavouriteImg.setImageDrawable(getResources().getDrawable(R.drawable.favorite_icon_selected));
                ToastUtil.toastInCenter(this, R.string.favourite_success);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mWbShareHandler.doResultIntent(intent, this);
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

    public void onEventMainThread(WeiboShareEvent event) {
        if (event != null) {
            weiboShare();
        }
    }

    private void weiboShare() {
        if (!WbSdk.isWbInstall(this)) {
            ToastUtil.toastInCenter(this, R.string.share_dialog_weibo_not_installed);
            return;
        }
        mSsoHandler = new SsoHandler(this);
        mSsoHandler.authorize(new WbAuthListener() {
            @Override
            public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                Log.i(TAG, "shareToWeibo:" + "授权成功");
                sendWeiboMsg();
            }

            @Override
            public void cancel() {
                Log.i(TAG, "shareToWeibo:" + "授权取消");
            }

            @Override
            public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                Log.i(TAG, "shareToWeibo:" + "授权失败");
            }
        });
    }

    @Override
    public void onWbShareSuccess() {
        ToastUtil.toastInCenter(this, R.string.share_dialog_toast_success);
    }

    @Override
    public void onWbShareCancel() {
        ToastUtil.toastInCenter(this, R.string.share_dialog_toast_cancel);
    }

    @Override
    public void onWbShareFail() {
        ToastUtil.toastInCenter(this, R.string.share_dialog_toast_failure);
    }

    private void sendWeiboMsg() {
        mWbShareHandler = new WbShareHandler(this);
        mWbShareHandler.registerApp();
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = mNewsItem.getTitle();
        mediaObject.setThumbImage(mShareThumb);
        mediaObject.actionUrl = mNewsItem.getUrl();
        weiboMessage.mediaObject = mediaObject;
        mWbShareHandler.shareMessage(weiboMessage, false);
    }
}
