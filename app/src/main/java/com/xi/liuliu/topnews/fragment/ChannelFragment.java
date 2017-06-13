package com.xi.liuliu.topnews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/12.
 */

public class ChannelFragment extends Fragment {
    private String mChannelTitle;

    public void setChannelTitle(String channelTitle) {
        mChannelTitle = channelTitle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.channel_fragment_layout, container,false);
        TextView textView = (TextView) view.findViewById(R.id.channel_fragment_layout_channel_title);
        textView.setText(mChannelTitle);
        return view;
    }
}
