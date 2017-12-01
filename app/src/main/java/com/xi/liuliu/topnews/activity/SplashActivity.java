package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.xi.liuliu.topnews.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private ImageView mImageView;
    private RelativeLayout mLogoLayout;
    private Animation mFadeIn;
    private Animation mFadeScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.splash_activity_scene);
        mLogoLayout = (RelativeLayout) findViewById(R.id.splash_activity_rl_logo);
        int random = new Random().nextInt(2);
        if (random == 0) {
            mImageView.setImageResource(R.drawable.splash_activity_tower);
        } else {
            mImageView.setImageResource(R.drawable.splash_activity_road);
        }
        mFadeIn = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in);
        mFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageView.startAnimation(mFadeScale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mFadeScale = AnimationUtils.loadAnimation(this, R.anim.welcome_fade_in_scale);
        mFadeScale.setFillAfter(true);
        mFadeScale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mImageView.startAnimation(mFadeIn);
        mLogoLayout.startAnimation(mFadeIn);
    }

    //禁止用返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
