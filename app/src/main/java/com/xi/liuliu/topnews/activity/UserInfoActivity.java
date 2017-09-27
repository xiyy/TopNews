package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.DatePickerDialog;
import com.xi.liuliu.topnews.dialog.GenderSelectorDialog;
import com.xi.liuliu.topnews.dialog.InputDialog;
import com.xi.liuliu.topnews.event.DatePickerEvent;
import com.xi.liuliu.topnews.event.GenderSelectorEvent;
import com.xi.liuliu.topnews.event.InputContentEvent;
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
    private DatePickerDialog mDatePickerDialog;
    private InputDialog mUserNameDialog;
    private InputDialog mIntroduceDialog;

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
        mIntroduce = (TextView) findViewById(R.id.content_introduce_user_info_activity);
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
            mRegion.setTextColor(getResources().getColor(R.color.text_view_default_color));
        }
        String birth = SharedPrefUtil.getInstance(this).getString(Constants.BIRTH_SP_KEY);
        if (birth != null) {
            mBirthDay.setText(birth);
            mBirthDay.setTextColor(getResources().getColor(R.color.text_view_default_color));
        }
        String userName = SharedPrefUtil.getInstance(this).getString(Constants.USER_NAME_SP_KEY);
        if (userName != null) {
            mUserName.setText(userName);
        }
        String introduce = SharedPrefUtil.getInstance(this).getString(Constants.INTRODUCE_SP_KEY);
        if (introduce != null) {
            mIntroduce.setText(introduce);
            mIntroduce.setTextColor(getResources().getColor(R.color.text_view_default_color));
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
                if (mUserNameDialog == null) {
                    mUserNameDialog = new InputDialog(this, InputContentEvent.INPUT_USER_NAME);
                }
                mUserNameDialog.startSoftInput();
                mUserNameDialog.show();
                break;
            case R.id.introduce_rl_user_info_activity:
                if (mIntroduceDialog == null) {
                    mIntroduceDialog = new InputDialog(this, InputContentEvent.INPUT_INTRODUCE);
                }
                mIntroduceDialog.startSoftInput();
                mIntroduceDialog.show();
                break;
            case R.id.gender_rl_user_info_activity:
                if (mGenderSelectorDialog == null) {
                    mGenderSelectorDialog = new GenderSelectorDialog(this, mGenderType);
                }
                mGenderSelectorDialog.show();

                break;
            case R.id.birth_day_rl_user_info_activity:
                if (mDatePickerDialog == null) {
                    mDatePickerDialog = new DatePickerDialog(this);
                }
                mDatePickerDialog.show();
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

    public void onEventMainThread(DatePickerEvent event) {
        if (event != null) {
            mBirthDay.setText(event.getDate());
            mBirthDay.setTextColor(getResources().getColor(R.color.text_view_default_color));
            SharedPrefUtil.getInstance(this).putString(Constants.BIRTH_SP_KEY, event.getDate());
        }
    }

    public void onEventMainThread(InputContentEvent event) {
        if (event != null) {
            int from = event.getInputFrom();
            String inputContent = event.getInputContent();
            if (!TextUtils.isEmpty(inputContent)) {
                if (from == InputContentEvent.INPUT_INTRODUCE) {
                    SharedPrefUtil.getInstance(this).putString(Constants.INTRODUCE_SP_KEY, inputContent);
                    mIntroduce.setText(inputContent);
                    mIntroduce.setTextColor(getResources().getColor(R.color.text_view_default_color));
                } else {
                    SharedPrefUtil.getInstance(this).putString(Constants.USER_NAME_SP_KEY, inputContent);
                    mUserName.setText(inputContent);
                }
            }
        }

    }

    private void setMale(int genderType) {
        if (genderType == 1) {
            mGender.setText(R.string.edit_user_info_gender_male);
            mGender.setTextColor(getResources().getColor(R.color.text_view_default_color));
        } else if (genderType == 0) {
            mGender.setText(R.string.edit_user_info_gender_female);
            mGender.setTextColor(getResources().getColor(R.color.text_view_default_color));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1006 && resultCode == 1007 && data != null) {
            int cityName = data.getIntExtra("city_name", 0);
            mRegion.setText(cityName);
            mRegion.setTextColor(getResources().getColor(R.color.text_view_default_color));
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
