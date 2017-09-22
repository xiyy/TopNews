package com.xi.liuliu.topnews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

import java.util.ArrayList;

/**
 * Created by zhangxb171 on 2017/9/22.
 */

public class RegionAdapter extends RecyclerView.Adapter {
    public ArrayList<Integer> mRegionResIdList;

    public RegionAdapter(ArrayList<Integer> list) {
        mRegionResIdList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_region_activity, parent, false);
        TextView regionName = (TextView) view.findViewById(R.id.region_name_item_region_activity);
        Button guideSymbol = (Button) view.findViewById(R.id.guide_symbol_item_region_activity);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView = view;
        holder.regionName = regionName;
        holder.guideSymbol = guideSymbol;
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.regionName.setText(mRegionResIdList.get(position));
        //最后四项，箭头不可见
        if ((position == mRegionResIdList.size() - 1) || (position == mRegionResIdList.size() - 2) ||
                (position == mRegionResIdList.size() - 3) || (position == mRegionResIdList.size() - 4)) {
            viewHolder.guideSymbol.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mRegionResIdList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView regionName;
        Button guideSymbol;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
