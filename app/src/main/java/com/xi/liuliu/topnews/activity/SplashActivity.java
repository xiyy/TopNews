package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xi.liuliu.topnews.R;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private ImageView mImageView;
    private Animation mFadeScale;
    private int mBgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示，不显示系统的状态栏（屏幕上方的电池，时间，WiFi等）
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //nextInt(n),n>0,[0,n-1]之间的随机数
        mBgType = new Random().nextInt(2);//生成0或者1
        //在setContentView前，设置activity的主题
        if (mBgType == 0) {
            setTheme(R.style.splash_activity_theme_tower);
        } else {
            setTheme(R.style.splash_activity_theme_road);
        }
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.splash_activity_scene);
        //设置背景图，背景图与setTheme()保持一致
        if (mBgType == 0) {
            mImageView.setImageResource(R.drawable.splash_activity_tower);
        } else {
            mImageView.setImageResource(R.drawable.splash_activity_road);
        }
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
        mImageView.startAnimation(mFadeScale);

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
