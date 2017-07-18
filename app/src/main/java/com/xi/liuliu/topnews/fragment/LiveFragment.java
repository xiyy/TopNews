package com.xi.liuliu.topnews.fragment;


import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.LiveListActivity;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LiveFragmentVisibleEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/7/7.
 */

public class LiveFragment extends Fragment {
    private GridView mGridView;
    private List<ImageView> mChannelImageViews = new ArrayList<>(5);

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        View view = inflater.inflate(R.layout.fragment_live, container, false);
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
                getActivity().overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        mChannelImageViews = null;
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

    private void rotateChannelImageView(ImageView channelIcon) {
        if (channelIcon != null) {
            ObjectAnimator.ofFloat(channelIcon, "rotationY", 0.0f, 360.0f).setDuration(1000).start();
        }
    }

    public void onEventMainThread(LiveFragmentVisibleEvent event) {
        if (event != null) {
            if (event.getFragmentVisibility() && mChannelImageViews != null) {
                for (ImageView each : mChannelImageViews) {
                    rotateChannelImageView(each);
                }
            }
        }
    }
}
