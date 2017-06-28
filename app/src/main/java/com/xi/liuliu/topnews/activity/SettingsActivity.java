package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LoginResultEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import de.greenrobot.event.EventBus;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mGoBack;
    private TextView mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mGoBack = (ImageView) findViewById(R.id.settings_left_back_icon);
        mGoBack.setOnClickListener(this);
        mLogout = (TextView) findViewById(R.id.settings_log_out);
        mLogout.setOnClickListener(this);
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
                logout();
                break;
        }
    }

    private void logout() {
        //弹出对话框，退出确认
        SharedPrefUtil.getInstance(this).putBoolean(Constants.LOGIN_SP_KEY, false);
        SharedPrefUtil.getInstance(this).putString(Constants.USER_PHONE_NUMBER_SP_KEY, null);
        EventBus.getDefault().post(new LoginResultEvent(false));
        Toast toast = Toast.makeText(getApplicationContext(), R.string.settings_toast_log_out_success, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        finish();
    }
}
