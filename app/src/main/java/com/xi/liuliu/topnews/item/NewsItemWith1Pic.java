package com.xi.liuliu.topnews.item;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.bean.NewsWith1Pic;

/**
 * Created by liuliu on 2017/6/14.
 */

public class NewsItemWith1Pic {
    public static RecyclerView.ViewHolder onCreate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_1_pic, parent, false);
        NewsItemWith1PicViewHolder holder = new NewsItemWith1PicViewHolder(view);
        holder.title = (TextView) view.findViewById(R.id.news_item_1_title);
        holder.newSrc = (TextView) view.findViewById(R.id.news_item_1_src);
        holder.time = (TextView) view.findViewById(R.id.news_item_1_time);
        holder.icon = (ImageView) view.findViewById(R.id.news_item_1_ImageView);
        return holder;
    }

    public static void onBind(Context context, RecyclerView.ViewHolder viewHolder, int position, NewsWith1Pic news) {
        final NewsItemWith1PicViewHolder holder = (NewsItemWith1PicViewHolder) viewHolder;
        holder.title.setText(news.getmTitle());
        holder.newSrc.setText(news.getmNewsSrc());
        holder.time.setText(news.getmTime());
        Glide.with(context).load(news.getmIconUrl()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.icon.setImageDrawable(resource);
            }
        });
    }

    private static class NewsItemWith1PicViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView newSrc;
        TextView time;
        ImageView icon;
        public NewsItemWith1PicViewHolder(View itemView) {
            super(itemView);
        }
    }
}
