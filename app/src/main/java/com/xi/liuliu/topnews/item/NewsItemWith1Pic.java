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

    public static void onBind(final Context context, RecyclerView.ViewHolder viewHolder, int position, NewsItem newsItem) {
        final NewsItemWith1PicViewHolder holder = (NewsItemWith1PicViewHolder) viewHolder;
        final String newsUrl = newsItem.getUrl();
        holder.title.setText(newsItem.getTitle());
        holder.newSrc.setText(newsItem.getAuthorName());
        holder.time.setText(newsItem.getDate());
        Glide.with(context).load(newsItem.getThumbnailPic()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.icon.setImageDrawable(resource);
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

    private static class NewsItemWith1PicViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView newSrc;
        TextView time;
        ImageView icon;
        View newsItemView;
        public NewsItemWith1PicViewHolder(View itemView) {
            super(itemView);
            newsItemView = itemView;
        }
    }
}
