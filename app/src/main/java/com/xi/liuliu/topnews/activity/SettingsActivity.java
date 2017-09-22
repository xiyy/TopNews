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
import com.xi.liuliu.topnews.utils.ToastUtil;

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
        calculateCacheSize();

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
                Intent intent = new Intent(this, UserInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.clear_cache_rl:
                //缓存为0或者正在计算缓存时，不弹出dialog
                if (!mCacheSize.getText().toString().equals("0.0MB") && !mCacheSize.getText().toString().equals("正在计算...")) {
                    ClearCacheDialog clearCacheDialog = new ClearCacheDialog(this);
                    clearCacheDialog.setClearCacheListener(new ClearCacheListener() {
                        @Override
                        public void onClearCacheFinished() {
                            //ClearCacheListener.onClearCacheFinished运行在子线程中，不能在子线程中进行UI操作，引发crash
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.toastInCenter(SettingsActivity.this, R.string.clear_cache_toast_success);
                                    mCacheSize.setText("0.0MB");
                                }
                            });
                        }
                    });
                    clearCacheDialog.show();
                }
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
    }

    public void calculateCacheSize() {
        CacheSizeCalculateListener listener = new CacheSizeCalculateListener() {
            @Override
            public void onCalculateFinished(long cacheSize) {
                String size = FileUtils.formatFileSize(cacheSize, FileUtils.SIZETYPE_MB) + "MB";
                mCacheSize.setText(size);
            }
        };
        CaculateCacheSizeRunnable runnable = new CaculateCacheSizeRunnable(listener);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    static class CaculateCacheSizeRunnable implements Runnable {
        CacheSizeCalculateListener listener;

        public CaculateCacheSizeRunnable(CacheSizeCalculateListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            long cacheSize = 0;
            try {
                cacheSize = FileUtils.getCacheSize();
            } catch (Exception e) {
                e.printStackTrace();
            }
            listener.onCalculateFinished(cacheSize);
        }
    }

    interface CacheSizeCalculateListener {
        void onCalculateFinished(long cacheSize);
    }

    public static class ClearCacheRunnable implements Runnable {
        ClearCacheListener listener;

        public ClearCacheRunnable(ClearCacheListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            FileUtils.clearCache();
            listener.onClearCacheFinished();
        }
    }

    public interface ClearCacheListener {
        void onClearCacheFinished();
    }
}
