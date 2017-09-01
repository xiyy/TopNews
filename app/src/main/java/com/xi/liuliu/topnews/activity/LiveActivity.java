package com.xi.liuliu.topnews.activity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.utils.AnimUtil;
import com.xi.liuliu.topnews.utils.DeviceUtil;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LiveActivity";
    private VideoView mVideoView;
    private ImageView mSwitchBtn;
    private ImageView mExitBtn;
    private ImageView mFullScreenBtn;
    private FrameLayout mFlVideoView;
    private ImageView mLoadingView;
    private LinearLayout mTopPart;
    private LinearLayout mBottomPart;
    private AnimationDrawable mLoadingAnim;
    private boolean isFullScreen;
    private boolean isScreenClear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Vitamio.isInitialized(getApplicationContext())) {
            return;
        }
        setContentView(R.layout.activity_live);
        String liveUrl = getIntent().getStringExtra("live_url");
        mFlVideoView = (FrameLayout) findViewById(R.id.fl_video_view_live_activity);
        mSwitchBtn = (ImageView) findViewById(R.id.switch_video_live_activity);
        mExitBtn = (ImageView) findViewById(R.id.exit_btn_video_live_activity);
        mSwitchBtn.setOnClickListener(this);
        mExitBtn.setOnClickListener(this);
        mFullScreenBtn = (ImageView) findViewById(R.id.full_screen_live_activity);
        mFullScreenBtn.setOnClickListener(this);
        mTopPart = (LinearLayout) findViewById(R.id.top_part_live_activity);
        mBottomPart = (LinearLayout) findViewById(R.id.bottom_part_live_activity);
        mLoadingView = (ImageView) findViewById(R.id.loading_btn_live_activity);
        mLoadingAnim = (AnimationDrawable) mLoadingView.getBackground();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingAnim.start();
        mVideoView = (VideoView) findViewById(R.id.video_view_live_activity);
        mVideoView.setVideoPath(liveUrl);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (!mLoadingAnim.isRunning()) {
                            //缓冲时，如果mSwitchBtn可见，要使其隐藏；mSwitchBtn和mLoadingView不允许同时显示
                            if (mSwitchBtn.getVisibility() == View.VISIBLE) {
                                mSwitchBtn.setVisibility(View.GONE);
                            }
                            mLoadingView.setVisibility(View.VISIBLE);
                            mLoadingAnim.start();
                        }

                        mp.pause();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mLoadingView.setVisibility(View.GONE);
                        mLoadingAnim.stop();
                        mp.start();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        Log.i(TAG, "当前网速：" + extra + "kb/s");
                        break;
                }
                return true;
            }
        });

        mVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.ACTION_DOWN == event.getAction()) {
                    if (isScreenClear) {
                        isScreenClear = false;
                        AnimUtil.alphaAndVisible(mExitBtn, 0, 1, 1000);
                        AnimUtil.alphaAndVisible(mFullScreenBtn, 0, 1, 1000);
                        //缓冲时隐藏mSwitchBtn，缓冲完成后才能显示mSwitchBtn；缓冲对话框与mSwitchBtn不能同时显示
                        if (!mLoadingAnim.isRunning()) {
                            AnimUtil.alphaAndVisible(mSwitchBtn, 0, 1, 1000);
                            if (mVideoView.isPlaying()) {
                                mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_pause);
                            }
                            if (mVideoView.hasFocus() && !mVideoView.isPlaying()) {
                                //暂停状态
                                mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_play);
                            }
                        } else {
                            mSwitchBtn.setVisibility(View.GONE);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (!isScreenClear) {
                                    isScreenClear = true;
                                    clearScreen();
                                }
                            }
                        }, 3000);
                    } else {
                        isScreenClear = true;
                        clearScreen();
                    }
                }
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null && mVideoView.isPlaying()) {
            mVideoView.stopPlayback();//停止播放，并释放资源
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_video_live_activity:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_play);
                } else {
                    mVideoView.start();
                    mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_pause);
                }
                break;
            case R.id.exit_btn_video_live_activity:
                if (mLoadingAnim != null && mLoadingAnim.isRunning()) {
                    mLoadingAnim.stop();
                }
                if (isFullScreen) {
                    setVideoPreview();
                } else {
                    finish();
                }
                break;
            case R.id.full_screen_live_activity:
                if (!isFullScreen) {
                    setFullScreen();
                } else {
                    setVideoPreview();
                }
                break;
        }
    }

    private void clearScreen() {
        AnimUtil.alphaAndGone(mFullScreenBtn, 1, 0, 1000);
        AnimUtil.alphaAndGone(mExitBtn, 1, 0, 1000);
        AnimUtil.alphaAndGone(mSwitchBtn, 1, 0, 1000);
    }

    private void setFullScreen() {
        LinearLayout.LayoutParams fullScreenLLP = new LinearLayout.LayoutParams(
                DeviceUtil.getHeightPixel(this), DeviceUtil.getWidthPixel(this) - DeviceUtil.getStatusBarHeight(this));
        mTopPart.setVisibility(View.GONE);
        mBottomPart.setVisibility(View.GONE);
        mFlVideoView.setLayoutParams(fullScreenLLP);//mFlVideoView的宽是屏幕高度，高是屏幕宽度-状态栏高度
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//Activity横屏
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        isFullScreen = true;
    }

    public void setVideoPreview() {
        LinearLayout.LayoutParams previewLLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceUtil.dip2px(203, this));
        mTopPart.setVisibility(View.VISIBLE);
        mBottomPart.setVisibility(View.VISIBLE);
        mFlVideoView.setLayoutParams(previewLLP);//mFlVideoView的宽是屏幕的宽度，高是203dp
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Activity竖屏
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        isFullScreen = false;
    }
}