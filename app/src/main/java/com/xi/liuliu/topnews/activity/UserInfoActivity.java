package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.DatePickerDialog;
import com.xi.liuliu.topnews.dialog.GetGenderDialog;
import com.xi.liuliu.topnews.dialog.GetPicDialog;
import com.xi.liuliu.topnews.dialog.InputDialog;
import com.xi.liuliu.topnews.event.DatePickerEvent;
import com.xi.liuliu.topnews.event.GenderSelectorEvent;
import com.xi.liuliu.topnews.event.InputContentEvent;
import com.xi.liuliu.topnews.event.LoginEvent;
import com.xi.liuliu.topnews.event.PortraitUpdateEvent;
import com.xi.liuliu.topnews.utils.FileUtils;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import java.io.File;

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
    private GetGenderDialog mGenderSelectorDialog;
    private int mGenderType;
    private DatePickerDialog mDatePickerDialog;
    private InputDialog mUserNameDialog;
    private InputDialog mIntroduceDialog;
    private GetPicDialog mGetPicDialog;
    private File mCropFile;
    private File mCameraUserPortrait;
    private int mLoginType;

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
        mLoginType = SharedPrefUtil.getInstance(this).getInt(Constants.LOGIN_TYPE_SP_KEY);
        //显示头像
        String portraitPath = SharedPrefUtil.getInstance(this).getString(Constants.USER_PORTRAIT_PATH_SP_KEY);
        if (!TextUtils.isEmpty(portraitPath)) {
            Bitmap bitmap = BitmapFactory.decodeFile(portraitPath);
            //清除缓存后，bitmap为null
            if (bitmap != null) {
                mPortraitImg.setImageBitmap(bitmap);
            } else {
                setThirdPortrait(mLoginType);
            }
        } else {
            setThirdPortrait(mLoginType);
        }
        //显示性别
        mGenderType = SharedPrefUtil.getInstance(this).getInt(Constants.GENDER_SP_KEY);
        setMale(mGenderType);
        //显示城市
        int cityName = SharedPrefUtil.getInstance(this).getInt(Constants.CITY_SP_KEY);
        if (cityName != -1) {
            mRegion.setText(cityName);
            mRegion.setTextColor(getResources().getColor(R.color.text_view_default_color));
        }
        //显示生日
        String birth = SharedPrefUtil.getInstance(this).getString(Constants.BIRTH_SP_KEY);
        if (birth != null) {
            mBirthDay.setText(birth);
            mBirthDay.setTextColor(getResources().getColor(R.color.text_view_default_color));
        }
        //显示用户名
        String userName = SharedPrefUtil.getInstance(this).getString(Constants.USER_NAME_SP_KEY);
        if (!TextUtils.isEmpty(userName)) {
            mUserName.setText(userName);
        } else {
            setThirdUserName(mLoginType);
        }
        //显示个人介绍
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
                if (mGetPicDialog == null) {
                    mGetPicDialog = new GetPicDialog(this, this, R.layout.dialog_get_pic, GetPicDialog.FROM_USER_PORTRAIT);
                }
                mCameraUserPortrait = FileUtils.createImageFile();
                mGetPicDialog.setCameraFile(mCameraUserPortrait);
                mGetPicDialog.show();
                break;
            case R.id.name_rl_user_info_activity:
                String userName = mUserName.getText().toString().trim();
                if (mUserNameDialog == null) {
                    mUserNameDialog = new InputDialog(this, InputContentEvent.INPUT_USER_NAME, userName);
                }
                mUserNameDialog.startSoftInput();
                mUserNameDialog.show();
                break;
            case R.id.introduce_rl_user_info_activity:
                String introduce = mIntroduce.getText().toString().trim();
                if (mIntroduceDialog == null) {
                    mIntroduceDialog = new InputDialog(this, InputContentEvent.INPUT_INTRODUCE, introduce);
                }
                mIntroduceDialog.startSoftInput();
                mIntroduceDialog.show();
                break;
            case R.id.gender_rl_user_info_activity:
                if (mGenderSelectorDialog == null) {
                    mGenderSelectorDialog = new GetGenderDialog(this, mGenderType);
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

    /**
     * 设置手机登录、微博登录、QQ登录、微信登录的头像
     *
     * @param loginType
     */

    private void setThirdPortrait(int loginType) {
        switch (loginType) {
            case LoginEvent.LOGIN_WEIBO:
                String portraitUrl = SharedPrefUtil.getInstance(this).getString(Constants.WEI_BO_Portrait_URL);
                Glide.with(this).load(portraitUrl).transition(DrawableTransitionOptions.withCrossFade()).into(mPortraitImg);
                break;
            case LoginEvent.LOGIN_PHONE:
                mPortraitImg.setImageResource(R.drawable.default_head_portrait);
                break;
            case LoginEvent.LOGIN_QQ:
                break;
            case LoginEvent.LOGIN_WEIXIN:
                break;
        }
    }

    /**
     * 设置手机登录、微博登录、QQ登录、微信登录的用户名
     *
     * @param loginType
     */
    private void setThirdUserName(int loginType) {
        switch (loginType) {
            case LoginEvent.LOGIN_WEIBO:
                String userName = SharedPrefUtil.getInstance(this).getString(Constants.WEI_BO_NICK_NAME_SP_KEY);
                mUserName.setText(userName);
                break;
            case LoginEvent.LOGIN_PHONE:
                String phoneNumber = SharedPrefUtil.getInstance(this).getString(Constants.USER_PHONE_NUMBER_SP_KEY);
                mUserName.setText("手机用户" + phoneNumber);
                break;
            case LoginEvent.LOGIN_QQ:
                break;
            case LoginEvent.LOGIN_WEIXIN:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1006 && resultCode == 1007 && data != null) {
            handleRegionResult(data);
        }
        //裁剪从相册中选取的图片，注意resultCode！= RESULT_OK
        if (requestCode == 1001) {
            cutPic(data.getData());
        }
        //裁剪相机拍摄的图片
        if (requestCode == 1002) {
            Uri fileUri = FileUtils.getImageContentUri(this, mCameraUserPortrait);
            cutPic(fileUri);
        }
        //裁剪图片
        if (requestCode == 1010) {
            handleCutPicResult(resultCode);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void handleRegionResult(Intent data) {
        int cityName = data.getIntExtra("city_name", 0);
        mRegion.setText(cityName);
        mRegion.setTextColor(getResources().getColor(R.color.text_view_default_color));
        SharedPrefUtil.getInstance(this).putInt(Constants.CITY_SP_KEY, cityName);
    }

    private void handleCutPicResult(int resultCode) {
        if (resultCode == RESULT_OK) {
            String portraitPath = mCropFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(portraitPath);
            mPortraitImg.setImageBitmap(bitmap);
            SharedPrefUtil.getInstance(this).putString(Constants.USER_PORTRAIT_PATH_SP_KEY, portraitPath);
            EventBus.getDefault().post(new PortraitUpdateEvent(portraitPath));
        }
    }

    /**
     * 裁剪图片
     *
     * @param uri
     */
    public void cutPic(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        mCropFile = FileUtils.createImageFile();
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCropFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);
        startActivityForResult(intent, 1010);
    }

}
