package com.xi.liuliu.topnews.fragment;


import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.LiveActivity;
import com.xi.liuliu.topnews.activity.LiveListActivity;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LiveFragmentVisibleEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by liuliu on 2017/7/7.
 */

public class LiveFragment extends Fragment {
    private VideoView mVideoView;
    private GridView mGridView;
    private ImageView mLoadingView;
    private ImageView mFullScreen;
    private List<ImageView> mChannelImageViews = new ArrayList<>(5);
    private AnimationDrawable mLoadingAnim;
    private boolean isFullScreenImgShow;

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        mChannelImageViews = null;
        mVideoView.stopPlayback();//停止播放并释放资源
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
            rotateChannelImageView(channelIcon);
            return convertView;
        }


    }

    private void initView(View view) {
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
        mVideoView.setVideoPath(Constants.CCTV13);
        mVideoView.requestFocus();
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
                        mLoadingView.setVisibility(View.GONE);
                        mLoadingAnim.stop();
                        //mp.setVolume(0, 0);音量为0
                        mp.start();
                        break;
                }
                return true;
            }
        });
        mVideoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == event.ACTION_DOWN) {
                    if (isFullScreenImgShow) {
                        mFullScreen.setVisibility(View.GONE);
                        isFullScreenImgShow = false;
                    } else {
                        mFullScreen.setVisibility(View.VISIBLE);
                        isFullScreenImgShow = true;
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isFullScreenImgShow) {
                                    mFullScreen.setVisibility(View.GONE);
                                    isFullScreenImgShow = false;
                                }
                            }
                        }, 3000);
                    }
                }
                return true;
            }
        });
        mFullScreen = (ImageView) view.findViewById(R.id.full_screen_live_fragment);
        mFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.stopPlayback();
                Intent intent = new Intent(getActivity(), LiveActivity.class);
                intent.putExtra("live_url", Constants.CCTV13);
                startActivity(intent);
            }
        });
        mLoadingView = (ImageView) view.findViewById(R.id.loading_fragment_live);
        mLoadingAnim = (AnimationDrawable) mLoadingView.getBackground();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingAnim.start();
    }

    private void rotateChannelImageView(ImageView channelIcon) {
        if (channelIcon != null) {
            ObjectAnimator.ofFloat(channelIcon, "rotationY", 0.0f, 360.0f).setDuration(1000).start();
        }
    }

    public void onEventMainThread(LiveFragmentVisibleEvent event) {
        if (event != null) {
            if (event.isFragmentVisible()) {
                //旋转动画
                if (mChannelImageViews != null) {
                    for (ImageView each : mChannelImageViews) {
                        rotateChannelImageView(each);
                    }
                }
                if (!mVideoView.isBuffering()) {
                    mVideoView.start();//fragment切回来时，继续播放
                }
            } else {
                mVideoView.pause();//切换fragment时，暂停播放
            }
        }
    }

}
