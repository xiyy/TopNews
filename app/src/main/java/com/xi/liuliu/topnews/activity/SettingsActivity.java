package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.LogoutDialog;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mGoBack;
    private TextView mLogout;
    private TextView mUserAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mGoBack = (ImageView) findViewById(R.id.settings_left_back_icon);
        mGoBack.setOnClickListener(this);
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
        }
    }

    private void showUserAgreement() {
        Intent userAgreeIntent = new Intent(this, UserAgreementActivity.class);
        startActivity(userAgreeIntent);
    }
}
