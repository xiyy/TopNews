package com.xi.liuliu.topnews.fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;

/**
 * Created by liuliu on 2017/7/7.
 */

public class LiveFragment extends Fragment {
    private GridView mGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        mGridView = (GridView) view.findViewById(R.id.grid_view_fragment_live);
        mGridView.setAdapter(new ChannelAdapter(getActivity(), Constants.LIVE_CHANNEL, Constants.LIVE_CHANNEL_ICON_ID));
        return view;
    }

    static class ChannelAdapter extends BaseAdapter {
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
            View view = null;
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.item_live_fragment, null);
            } else {
                view = convertView;
            }
            ImageView channelIcon = (ImageView) view.findViewById(R.id.image_view_item_live_fragment);
            TextView channel = (TextView) view.findViewById(R.id.text_view_item_live_fragment);
            channelIcon.setImageResource(channelsIconId[position]);
            channel.setText(channels[position]);
            return view;
        }
    }
}
