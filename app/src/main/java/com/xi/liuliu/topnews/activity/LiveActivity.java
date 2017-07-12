package com.xi.liuliu.topnews.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.controller.LiveMediaController;
import com.xi.liuliu.topnews.dialog.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

public class LiveActivity extends AppCompatActivity {
    private VideoView mVideoView;
    private LiveMediaController mMediaController;
    private static final int MESSAGE_TIME = 10010;
    private static final int MESSAGE_BATTERY = 10011;
    private LiveTimer mLiveTimer;
    private LoadingDialog mLoadingDialog;
    private static final String TAG = "LiveActivity";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_TIME:
                    mMediaController.setTime(msg.obj.toString());
                    break;
                case MESSAGE_BATTERY:
                    mMediaController.setBattery(msg.obj.toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = LiveActivity.this.getWindow();
        window.setFlags(flag, flag);
        if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_live);
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this).setCancelable(true).
                    setLoadingMessage("已经缓冲0%");
        }
        mLoadingDialog.show();
        String liveUrl = getIntent().getStringExtra("live_url");
        mVideoView = (VideoView) findViewById(R.id.video_view_live_activity);
        mVideoView.setVideoPath(liveUrl);
        mMediaController = new LiveMediaController(this, mVideoView, this);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setBufferSize(1024 * 1024);//1MB缓冲区
        mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        mp.pause();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mLoadingDialog.dissmiss();
                        mp.start();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        break;
                }
                return true;
            }
        });
        mVideoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                mLoadingDialog.setLoadingMessage("已经缓冲" + percent + "%");
            }
        });
        mMediaController.show(5000);
        mVideoView.requestFocus();
        registerBoradcastReceiver();
        if (mLiveTimer == null) {
            mLiveTimer = new LiveTimer();
        }
        mLiveTimer.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mVideoView != null) {
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLiveTimer != null && !mLiveTimer.isInterrupted()) {
            mLiveTimer.interrupt();
            mLiveTimer = null;
            Log.i(TAG, "mLiveTimer interrupt");
        }
        if (mVideoView != null) {
            mVideoView.stopPlayback();//停止播放，并释放资源
        }
        try {
            unregisterReceiver(batteryBroadcastReceiver);
        } catch (IllegalArgumentException ex) {

        }
    }

    private BroadcastReceiver batteryBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                int level = intent.getIntExtra("level", 0);
                int scale = intent.getIntExtra("scale", 100);
                Message msg = new Message();
                msg.obj = (level * 100) / scale + "";
                msg.what = MESSAGE_BATTERY;
                mHandler.sendMessage(msg);
            }
        }
    };

    public void registerBoradcastReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryBroadcastReceiver, intentFilter);

    }

    class LiveTimer extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (true) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String str = sdf.format(new Date());
                Message msg = new Message();
                msg.obj = str;
                msg.what = MESSAGE_TIME;
                mHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}