package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.ClearCacheDialog;
import com.xi.liuliu.topnews.dialog.LogoutDialog;
import com.xi.liuliu.topnews.utils.FileUtils;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mGoBack;
    private RelativeLayout mEditUserInfo;
    private RelativeLayout mClearCache;
    private RelativeLayout mCheckVersion;
    private RelativeLayout mVideoNoticeNoWifi;
    private TextView mLogout;
    private TextView mUserAgreement;
    private TextView mCacheSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mGoBack = (RelativeLayout) findViewById(R.id.settings_left_back_icon);
        mGoBack.setOnClickListener(this);
        mEditUserInfo = (RelativeLayout) findViewById(R.id.edit_personal_info_rl);
        mEditUserInfo.setOnClickListener(this);
        mClearCache = (RelativeLayout) findViewById(R.id.clear_cache_rl);
        mClearCache.setOnClickListener(this);
        mCacheSize = (TextView) findViewById(R.id.cache_size);
        mCheckVersion = (RelativeLayout) findViewById(R.id.check_version_rl);
        mCheckVersion.setOnClickListener(this);
        mVideoNoticeNoWifi = (RelativeLayout) findViewById(R.id.video_notice_no_wifi);
        mVideoNoticeNoWifi.setOnClickListener(this);
        mLogout = (TextView) findViewById(R.id.settings_log_out);
        mLogout.setOnClickListener(this);
        mUserAgreement = (TextView) findViewById(R.id.user_agreement_activity_settings);
        mUserAgreement.setOnClickListener(this);
        boolean isLoggedIn = SharedPrefUtil.getInstance(this).getBoolean(Constants.LOGIN_SP_KEY);
        if (isLoggedIn) {
            mLogout.setVisibility(View.VISIBLE);
        } else {
            mLogout.setVisibility(View.GONE);
        }
        caculateCacheSize();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_left_back_icon:
                finish();
                break;
            case R.id.settings_log_out:
                new LogoutDialog(this).show();
                break;
            case R.id.user_agreement_activity_settings:
                showUserAgreement();
                break;
            case R.id.edit_personal_info_rl:

                break;
            case R.id.clear_cache_rl:
                new ClearCacheDialog(this).show();
                break;
            case R.id.check_version_rl:

                break;
            case R.id.video_notice_no_wifi:

                break;

        }
    }

    private void showUserAgreement() {
        Intent userAgreeIntent = new Intent(this, UserAgreementActivity.class);
        startActivity(userAgreeIntent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.zoomout);
    }

    public void caculateCacheSize() {
        CacheSizeCaculateListener listener = new CacheSizeCaculateListener() {
            @Override
            public void onCaculateFinished(long cacheSize) {
                String size = FileUtils.formatFileSize(cacheSize, FileUtils.SIZETYPE_MB) + "MB";
                mCacheSize.setText(size);
            }
        };
        CaculateCacheSizeRunnable runnable = new CaculateCacheSizeRunnable(listener);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    static class CaculateCacheSizeRunnable implements Runnable {
        CacheSizeCaculateListener cacheSizeCaculateListener;

        public CaculateCacheSizeRunnable(CacheSizeCaculateListener cacheSizeCaculateListener) {
            this.cacheSizeCaculateListener = cacheSizeCaculateListener;
        }

        @Override
        public void run() {
            long cacheSize = 0;
            try {
                cacheSize = FileUtils.getCacheSize();
            } catch (Exception e) {
                e.printStackTrace();
            }
            cacheSizeCaculateListener.onCaculateFinished(cacheSize);
        }
    }

    interface CacheSizeCaculateListener {
        void onCaculateFinished(long cacheSize);
    }
}
