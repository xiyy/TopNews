package com.xi.liuliu.topnews.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.NewsDetailActivity;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.bean.ReadNews;
import com.xi.liuliu.topnews.utils.BitmapUtil;
import com.xi.liuliu.topnews.utils.DBDao;
import com.xi.liuliu.topnews.utils.DateUtil;

/**
 * Created by liuliu on 2017/6/14.
 */

public class NewsItemWith1Pic {
    private static final String TAG = "NewsItemWith1Pic";

    public static RecyclerView.ViewHolder onCreate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_1_pic, parent, false);
        NewsItemWith1PicViewHolder holder = new NewsItemWith1PicViewHolder(view);
        holder.title = (TextView) view.findViewById(R.id.news_item_1_title);
        holder.newSrc = (TextView) view.findViewById(R.id.news_item_1_src);
        holder.time = (TextView) view.findViewById(R.id.news_item_1_time);
        holder.icon = (ImageView) view.findViewById(R.id.news_item_1_ImageView);
        return holder;
    }

    public static void onBind(final Context context, RecyclerView.ViewHolder viewHolder, int position, final NewsItem newsItem) {
        final NewsItemWith1PicViewHolder holder = (NewsItemWith1PicViewHolder) viewHolder;
        holder.title.setText(newsItem.getTitle());
        holder.newSrc.setText(newsItem.getAuthorName());
        holder.time.setText(DateUtil.getNewsFormatTime(newsItem.getDate()));
        Log.i(TAG, newsItem.getDate());
        final Intent intent = new Intent(context, NewsDetailActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable("newsItem", newsItem);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.shape_news_item_place_holder_bg).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(newsItem.getThumbnailPic()).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                holder.icon.setImageDrawable(resource);
                bundle.putParcelable("shareThum", BitmapUtil.drawableToBitmap(resource));
            }
        });
        holder.newsItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DBDao(v.getContext()).insertHistory(new ReadNews(newsItem));
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).overridePendingTransition(R.anim.zoomin,R.anim.zoomout);
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
