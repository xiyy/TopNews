package com.xi.liuliu.topnews.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.LiveListActivity;
import com.xi.liuliu.topnews.activity.MainActivity;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.NotWifiWarnDialog;
import com.xi.liuliu.topnews.event.LiveFragmentVisibleEvent;
import com.xi.liuliu.topnews.utils.AnimUtil;
import com.xi.liuliu.topnews.utils.DeviceUtil;
import com.xi.liuliu.topnews.utils.NetWorkUtil;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by liuliu on 2017/7/7.
 */

public class LiveFragment extends Fragment implements View.OnClickListener {
    private FrameLayout mFlVideoView;
    private VideoView mVideoView;
    private GridView mGridView;
    private ImageView mLoadingView;
    private ImageView mFullScreen;
    private ImageView mSwitchBtn;
    private ImageView mExitFullScreenBtn;
    private TextView mTitle;
    private List<ImageView> mChannelImageViews = new ArrayList<>(6);
    private AnimationDrawable mLoadingAnim;
    private boolean isVideoClear = true;
    private boolean isFullScreen;
    private boolean isLiveFragmentVisible = true;
    private boolean isVideoFirstStart = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitle = (TextView) view.findViewById(R.id.title_live_fragment);
        mFlVideoView = (FrameLayout) view.findViewById(R.id.fl_video_view);
        mGridView = (GridView) view.findViewById(R.id.grid_view_fragment_live);
        mGridView.setAdapter(new ChannelAdapter(getActivity(), Constants.LIVE_CHANNEL, Constants.LIVE_CHANNEL_ICON_ID));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("live_channel_title_id", position);
                Intent intent = new Intent(getActivity(), LiveListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
            }
        });
        mVideoView = (VideoView) view.findViewById(R.id.video_view_live_fragment);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (!mLoadingAnim.isRunning()) {
                            mLoadingView.setVisibility(View.VISIBLE);
                            mLoadingAnim.start();
                            mp.pause();
                        }
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        if (isVideoFirstStart) {
                            isVideoFirstStart = false;
                        }
                        mLoadingView.setVisibility(View.GONE);
                        mLoadingAnim.stop();
                        if (isLiveFragmentVisible) {
                            mp.start();
                        } else {
                            //视频缓冲时，如果切换Fragment，视频缓冲完成后，会播放视频；此时应该停止播放
                            mp.pause();
                        }
                        break;

                }
                return true;
            }
        });
        mVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == event.ACTION_DOWN) {
                    if (!isVideoFirstStart) {
                        if (isVideoClear) {
                            isVideoClear = false;
                            AnimUtil.alphaAndVisible(mFullScreen, 0, 1, 1000);
                            //缓冲View与mSwitchBtn不允许同时出现
                            if (!mLoadingAnim.isRunning()) {
                                AnimUtil.alphaAndVisible(mSwitchBtn, 0, 1, 1000);
                                if (mVideoView.isPlaying()) {
                                    mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_pause);
                                } else {
                                    mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_play);
                                }
                            } else {
                                mSwitchBtn.setVisibility(View.GONE);
                            }
                            //全屏时，退出按钮才显示
                            if (isFullScreen()) {
                                AnimUtil.alphaAndVisible(mExitFullScreenBtn, 0, 1, 1000);
                            }
                            //3秒后自动消失
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (!isVideoClear) {
                                        clearScreen();
                                        isVideoClear = true;
                                    }
                                }
                            }, 3000);

                        } else {
                            clearScreen();
                            isVideoClear = true;
                        }
                    }

                }
                return true;
            }
        });
        mFullScreen = (ImageView) view.findViewById(R.id.full_screen_live_fragment);
        mFullScreen.setOnClickListener(this);
        mSwitchBtn = (ImageView) view.findViewById(R.id.switch_video_live_fragment);
        mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_play);
        mSwitchBtn.setOnClickListener(this);
        mExitFullScreenBtn = (ImageView) view.findViewById(R.id.exit_btn_video_live_fragment);
        mExitFullScreenBtn.setOnClickListener(this);
        mLoadingView = (ImageView) view.findViewById(R.id.loading_fragment_live);
        mLoadingAnim = (AnimationDrawable) mLoadingView.getBackground();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_video_live_fragment:
                //第一次播放时
                if (isVideoFirstStart) {
                    //WiFi网络直接播放
                    if (NetWorkUtil.isWiFi(getActivity())) {
                        mSwitchBtn.setVisibility(View.GONE);
                        mLoadingView.setVisibility(View.VISIBLE);
                        mLoadingAnim.start();
                        mVideoView.setVideoPath(Constants.CCTV13);
                        mVideoView.requestFocus();
                        return;
                    }
                    //移动网络，弹出对话框
                    if (NetWorkUtil.isMobile(getActivity())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(R.string.alert_dialog_mobile_network_title).setMessage(R.string.alert_dialog_mobile_network_message).setPositiveButton(R.string.alert_dialog_mobile_network_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                mSwitchBtn.setVisibility(View.GONE);
                                mLoadingView.setVisibility(View.VISIBLE);
                                mLoadingAnim.start();
                                mVideoView.setVideoPath(Constants.CCTV13);
                                mVideoView.requestFocus();
                                return;

                            }
                        }).setNegativeButton(R.string.alert_dialog_mobile_network_cancle, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                return;
                            }
                        }).create().show();
                    }

                } //第一次播放以后
                else {
                    if (mVideoView.isPlaying()) {
                        mVideoView.pause();
                        mSwitchBtn.setVisibility(View.VISIBLE);
                        mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_play);
                    } else {
                        //移动网络
                        if (NetWorkUtil.isMobile(getActivity())) {
                            int notWifiWarn = SharedPrefUtil.getInstance(getActivity()).getInt(Constants.NOT_WIFI_WARN_SP_KEY);
                            if (notWifiWarn == NotWifiWarnDialog.NOT_WIFI_WARN_EVERY_TIME) {//设置"每次都提醒"，弹出Dialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle(R.string.alert_dialog_mobile_network_title).setMessage(R.string.alert_dialog_mobile_network_message).setPositiveButton(R.string.alert_dialog_mobile_network_ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mVideoView.start();
                                        mSwitchBtn.setVisibility(View.VISIBLE);
                                        mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_pause);
                                        dialog.dismiss();

                                    }
                                }).setNegativeButton(R.string.alert_dialog_mobile_network_cancle, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
                            } else//只提醒一次
                            {
                                mVideoView.start();
                                mSwitchBtn.setVisibility(View.VISIBLE);
                                mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_pause);
                            }
                        } else {//wifi网络
                            mVideoView.start();
                            mSwitchBtn.setVisibility(View.VISIBLE);
                            mSwitchBtn.setImageResource(R.drawable.layer_list_live_activity_switch_pause);
                        }

                    }
                }
                break;
            case R.id.full_screen_live_fragment:
                if (isFullScreen) {
                    setVideoPreview();//竖屏
                } else {
                    setFullScreen();
                }
                break;
            case R.id.exit_btn_video_live_fragment:
                if (isFullScreen()) {
                    setVideoPreview();
                }
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        mChannelImageViews = null;
        mVideoView.stopPlayback();//停止播放并释放资源
    }

    public void onEventMainThread(LiveFragmentVisibleEvent event) {
        if (event != null) {
            if (event.isFragmentVisible()) {
                isLiveFragmentVisible = true;
                //旋转动画
                if (mChannelImageViews != null) {
                    for (ImageView each : mChannelImageViews) {
                        AnimUtil.rotate(each, AnimUtil.ROTATE_Y, 0, 360, 1000);
                    }
                }
                //检查网络并播放
                if (NetWorkUtil.isNetWorkAvailable(getActivity())) {
                    if (!mVideoView.isBuffering()) {
                        mVideoView.requestFocus();//没有缓冲的话，开始缓冲
                    }
                    if (mVideoView.hasFocus() && !mVideoView.isPlaying()) {
                        mVideoView.start();//暂停状态的话，开始播放
                    }
                }

            } else {
                //切换fragment时，暂停播放
                isLiveFragmentVisible = false;
                mVideoView.pause();
            }
        }
    }

    private void clearScreen() {
        AnimUtil.alphaAndGone(mFullScreen, 1, 0, 1000);
        AnimUtil.alphaAndGone(mSwitchBtn, 1, 0, 1000);
        AnimUtil.alphaAndGone(mExitFullScreenBtn, 1, 0, 1000);
    }

    private void setFullScreen() {
        LinearLayout.LayoutParams fullScreenLLP = new LinearLayout.LayoutParams(
                DeviceUtil.getHeightPixel(getActivity()), DeviceUtil.getWidthPixel(getActivity()) - DeviceUtil.getStatusBarHeight(getActivity()));
        mTitle.setVisibility(View.GONE);
        mGridView.setVisibility(View.GONE);
        ((MainActivity) getActivity()).setBottomBarVisibility(View.GONE);
        mFlVideoView.setLayoutParams(fullScreenLLP);//mFlVideoView的宽是屏幕高度，高是屏幕宽度-状态栏高度
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//Activity横屏
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        isFullScreen = true;
    }

    public void setVideoPreview() {
        LinearLayout.LayoutParams previewLLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DeviceUtil.dip2px(203, getActivity()));
        mTitle.setVisibility(View.VISIBLE);
        mGridView.setVisibility(View.VISIBLE);
        ((MainActivity) getActivity()).setBottomBarVisibility(View.VISIBLE);
        mFlVideoView.setLayoutParams(previewLLP);//mFlVideoView的宽是屏幕的宽度，高是203dp
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Activity竖屏
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        isFullScreen = false;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }


    class ChannelAdapter extends BaseAdapter {
        private Context context;
        private String[] channels;
        private int[] channelsIconId;

        public ChannelAdapter(Context context, String[] channels, int[] channelsIconId) {
            this.context = context;
            this.channels = channels;
            this.channelsIconId = channelsIconId;
        }

        @Override
        public int getCount() {
            return channels.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_live_fragment, null);
            }
            ImageView channelIcon = (ImageView) convertView.findViewById(R.id.image_view_item_live_fragment);
            TextView channel = (TextView) convertView.findViewById(R.id.text_view_item_live_fragment);
            channelIcon.setImageResource(channelsIconId[position]);
            channel.setText(channels[position]);
            mChannelImageViews.add(position, channelIcon);
            AnimUtil.rotate(channelIcon, AnimUtil.ROTATE_Y, 0, 360, 1000);
            return convertView;
        }
    }
}
