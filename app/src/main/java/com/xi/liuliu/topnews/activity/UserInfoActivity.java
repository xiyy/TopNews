package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.GenderSelectorDialog;
import com.xi.liuliu.topnews.event.GenderSelectorEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import de.greenrobot.event.EventBus;

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
    private GenderSelectorDialog mGenderSelectorDialog;
    private int mGenderType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
        initData();
    }

    private void initView() {
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
        mRegion = (TextView) findViewById(R.id.region_user_info_activity);
    }

    private void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mGenderType = SharedPrefUtil.getInstance(this).getInt(Constants.GENDER_SP_KEY);
        setMale(mGenderType);
        int cityName = SharedPrefUtil.getInstance(this).getInt(Constants.CITY_SP_KEY);
        if (cityName != -1) {
            mRegion.setText(cityName);
        }
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
                if (mGenderSelectorDialog == null) {
                    mGenderSelectorDialog = new GenderSelectorDialog(this, mGenderType);
                }
                mGenderSelectorDialog.show();
                break;
            case R.id.birth_day_rl_user_info_activity:

                break;

            case R.id.region_rl_user_info_activity:
                Intent intent = new Intent(this, RegionActivity.class);
                startActivityForResult(intent, 1006);
                break;
        }
    }

    public void onEventMainThread(GenderSelectorEvent event) {
        if (event != null) {
            setMale(event.getGenderType());
            SharedPrefUtil.getInstance(this).putInt(Constants.GENDER_SP_KEY, event.getGenderType());
        }
    }


    private void setMale(int genderType) {
        if (genderType == 1) {
            mGender.setText(R.string.edit_user_info_gender_male);
        } else if (genderType == 0) {
            mGender.setText(R.string.edit_user_info_gender_female);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1006 && resultCode == 1007 && data != null) {
            int cityName = data.getIntExtra("city_name", 0);
            mRegion.setText(cityName);
            SharedPrefUtil.getInstance(this).putInt(Constants.CITY_SP_KEY, cityName);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
