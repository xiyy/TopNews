package com.xi.liuliu.topnews.controller;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class LiveMediaController extends MediaController {
    private GestureDetector mGestureDetector;
    private ImageButton mGoBack;
    private ImageView mBattery;
    private TextView mTime;
    private TextView mBatteryPercent;
    private VideoView mVideoView;
    private Activity mActivity;
    private Context mContext;
    private int mControllerWidth = 0;

    public LiveMediaController(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.mContext = context;
        this.mVideoView = videoView;
        this.mActivity = activity;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        mControllerWidth = wm.getDefaultDisplay().getWidth();
        mGestureDetector = new GestureDetector(context, new MyGestureListener());
    }

    @Override
    protected View makeControllerView() {
        View v = LayoutInflater.from(mContext).inflate(R.layout.live_media_controller, null);
        v.setMinimumHeight(mControllerWidth);
        mGoBack = (ImageButton) v.findViewById(R.id.media_controller_top_back);
        mBattery = (ImageView) v.findViewById(R.id.media_controller_battery);
        mGoBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity != null) {
                    mActivity.finish();
                }
            }
        });
        mBatteryPercent = (TextView) v.findViewById(R.id.media_controller_battery_percent);
        mTime = (TextView) v.findViewById(R.id.media_controller_current_time);
        return v;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) return true;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }
    }


    public void setTime(String time) {
        if (mTime != null)
            mTime.setText(time);
    }

    public void setBattery(String stringBattery) {
        if (mTime != null && mBattery != null) {
            mBatteryPercent.setText(stringBattery + "%");
            int battery = Integer.valueOf(stringBattery);
            if (battery < 15)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_15));
            if (battery < 30 && battery >= 15)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_15));
            if (battery < 45 && battery >= 30)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_30));
            if (battery < 60 && battery >= 45)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_45));
            if (battery < 75 && battery >= 60)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_60));
            if (battery < 90 && battery >= 75)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_75));
            if (battery > 90)
                mBattery.setImageDrawable(getResources().getDrawable(R.drawable.media_controller_battery_90));
        }
    }

    private void toggleMediaControlsVisiblity() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    }

    private void playOrPause() {
        if (mVideoView != null)
            if (mVideoView.isPlaying()) {
                mVideoView.pause();
            } else {
                mVideoView.start();
            }
    }

}
