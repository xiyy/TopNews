package com.xi.liuliu.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.impl.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by zhangxb171 on 2017/9/25.
 */

public class CityAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<Integer> mCityList;
    private OnItemClickListener mOnItemClickListener;

    public CityAdapter(Context context, ArrayList<Integer> list) {
        mContext = context;
        mCityList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_city_activity, parent, false);
        view.setOnClickListener(this);
        ViewHolder holder = new ViewHolder(view);
        TextView cityName = (TextView) view.findViewById(R.id.city_name_item_city_activity);
        holder.cityName = cityName;
        holder.itemView = view;
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.cityName.setText(mCityList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView cityName;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
