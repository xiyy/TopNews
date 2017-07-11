package com.xi.liuliu.topnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/7/11.
 */

public class LiveListAdapter extends BaseAdapter {
    private String[] mLiveChannels;
    private Context mContext;

    public LiveListAdapter(String[] liveChannels, Context context) {
        mLiveChannels = liveChannels;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mLiveChannels.length;
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
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_live_list_activity, null);
            viewHolder = new ViewHolder();
            viewHolder.channel = (TextView) view.findViewById(R.id.text_view_item_live_list_activity);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.channel.setText(mLiveChannels[position]);
        return view;
    }

    static class ViewHolder {
        TextView channel;
    }
}
