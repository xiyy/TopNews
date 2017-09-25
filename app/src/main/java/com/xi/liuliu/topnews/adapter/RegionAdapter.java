package com.xi.liuliu.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.impl.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by zhangxb171 on 2017/9/22.
 */

public class RegionAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    private static final int SHOW_GUIDE_SYMBOL = 1;
    private static final int NOT_SHOW_GUIDE_SYMBOL = 0;
    public ArrayList<Integer> mRegionResIdList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public RegionAdapter(ArrayList<Integer> list, Context context) {
        mRegionResIdList = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region_activity, parent, false);
        view.setOnClickListener(this);
        TextView regionName = (TextView) view.findViewById(R.id.region_name_item_region_activity);
        Button guideSymbol = (Button) view.findViewById(R.id.guide_symbol_item_region_activity);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView = view;
        holder.regionName = regionName;
        holder.guideSymbol = guideSymbol;
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.regionName.setText(mRegionResIdList.get(position));
        if (getItemViewType(position) == NOT_SHOW_GUIDE_SYMBOL) {
            viewHolder.guideSymbol.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mRegionResIdList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //最后四项，箭头不可见
        if ((position == mRegionResIdList.size() - 1) || (position == mRegionResIdList.size() - 2) ||
                (position == mRegionResIdList.size() - 3) || (position == mRegionResIdList.size() - 4)) {
            return NOT_SHOW_GUIDE_SYMBOL;
        } else {
            return SHOW_GUIDE_SYMBOL;
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView regionName;
        Button guideSymbol;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
