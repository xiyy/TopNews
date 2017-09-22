package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mExitRl;
    private RelativeLayout mPortraitRl;
    private RelativeLayout mUserNameRl;
    private RelativeLayout mIntroduceRl;
    private RelativeLayout mGenderRl;
    private RelativeLayout mBirthDayRl;
    private RelativeLayout mRegionRl;
    private ImageView mPortraitImg;
    private TextView mUserName;
    private TextView mIntroduce;
    private TextView mGender;
    private TextView mBirthDay;
    private TextView mRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mExitRl = (RelativeLayout) findViewById(R.id.go_back_user_info_activity);
        mExitRl.setOnClickListener(this);
        mPortraitRl = (RelativeLayout) findViewById(R.id.portrait_rl_user_info_activity);
        mPortraitRl.setOnClickListener(this);
        mUserNameRl = (RelativeLayout) findViewById(R.id.name_rl_user_info_activity);
        mUserNameRl.setOnClickListener(this);
        mIntroduceRl = (RelativeLayout) findViewById(R.id.introduce_rl_user_info_activity);
        mIntroduceRl.setOnClickListener(this);
        mGenderRl = (RelativeLayout) findViewById(R.id.gender_rl_user_info_activity);
        mGenderRl.setOnClickListener(this);
        mBirthDayRl = (RelativeLayout) findViewById(R.id.birth_day_rl_user_info_activity);
        mBirthDayRl.setOnClickListener(this);
        mRegionRl = (RelativeLayout) findViewById(R.id.region_rl_user_info_activity);
        mRegionRl.setOnClickListener(this);
        mPortraitImg = (ImageView) findViewById(R.id.portrait_user_info_activity);
        mUserName = (TextView) findViewById(R.id.name_user_info_activity);
        mGender = (TextView) findViewById(R.id.gender_user_info_activity);
        mBirthDay = (TextView) findViewById(R.id.birth_day_user_info_activity);
        mRegion = (TextView) findViewById(R.id.birth_day_user_info_activity);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back_user_info_activity:
                finish();
                break;
            case R.id.portrait_rl_user_info_activity:

                break;
            case R.id.name_rl_user_info_activity:

                break;
            case R.id.introduce_rl_user_info_activity:

                break;
            case R.id.gender_rl_user_info_activity:

                break;
            case R.id.birth_day_rl_user_info_activity:

                break;

            case R.id.region_rl_user_info_activity:

                break;
        }
    }
}
