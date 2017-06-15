package com.xi.liuliu.topnews.item;

import android.content.Context;
import android.content.Intent;
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
import com.xi.liuliu.topnews.activity.NewsDetailActivity;
import com.xi.liuliu.topnews.bean.NewsItem;

/**
 * Created by liuliu on 2017/6/14.
 */

public class NewsItemWith3Pic {
    public static RecyclerView.ViewHolder onCreate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_3_pic, parent, false);
        NewsItemWith3PicViewHolder holder = new NewsItemWith3PicViewHolder(view);
        holder.title = (TextView) view.findViewById(R.id.news_item_3_title);
        holder.newSrc = (TextView) view.findViewById(R.id.news_item_3_src);
        holder.time = (TextView) view.findViewById(R.id.news_item_3_time);
        holder.icon1 = (ImageView) view.findViewById(R.id.news_item_3_imageview_1);
        holder.icon2 = (ImageView) view.findViewById(R.id.news_item_3_imageview_2);
        holder.icon3 = (ImageView) view.findViewById(R.id.news_item_3_imageview_3);
        return holder;
    }

    public static void onBind(Context context, RecyclerView.ViewHolder viewHolder, int position, NewsItem newsItem) {
        final NewsItemWith3PicViewHolder holder = (NewsItemWith3PicViewHolder) viewHolder;
        final String newsUrl = newsItem.getUrl();
        holder.title.setText(newsItem.getTitle());
        holder.newSrc.setText(newsItem.getAuthorName());
        holder.time.setText(newsItem.getDate());
        Glide.with(context).load(newsItem.getThumbnailPic()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.icon1.setImageDrawable(resource);
            }
        });
        Glide.with(context).load(newsItem.getThumbnailPic02()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.icon2.setImageDrawable(resource);
            }
        });
        Glide.with(context).load(newsItem.getThumbnailPic03()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.icon3.setImageDrawable(resource);
            }
        });
        holder.newsItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
                intent.putExtra("news_url",newsUrl);
                v.getContext().startActivity(intent);
            }
        });
    }

    private static class NewsItemWith3PicViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView newSrc;
        TextView time;
        ImageView icon1;
        ImageView icon2;
        ImageView icon3;
        View newsItemView;
        public NewsItemWith3PicViewHolder(View itemView) {
            super(itemView);
            newsItemView = itemView;
        }
    }
}
