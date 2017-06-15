package com.xi.liuliu.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.item.NewsItemWith1Pic;
import com.xi.liuliu.topnews.item.NewsItemWith3Pic;

import java.util.List;

/**
 * Created by liuliu on 2017/6/13.
 */

public class NewsItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<List> mNewsList;
    private static final int PIC1_NEWS_TAG = 0;
    private static final int PIC3_NEWS_TAG = 1;

    public NewsItemAdapter(Context context, List<List> newsList) {
        mContext = context;
        mNewsList = newsList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == PIC1_NEWS_TAG) {
            return NewsItemWith1Pic.onCreate(parent);
        } else {
            return NewsItemWith3Pic.onCreate(parent);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int viewType = getItemViewType(position);
        if (viewType == PIC1_NEWS_TAG) {
            NewsItemWith1Pic.onBind(mContext, holder, position, (NewsItem) mNewsList.get(0).get(position));
        } else {
            NewsItemWith3Pic.onBind(mContext, holder, position, (NewsItem) mNewsList.get(1).get(position - mNewsList.get(0).size()));
        }
    }

    @Override
    public int getItemCount() {
        return mNewsList.get(0).size() + mNewsList.get(1).size();
    }

    @Override
    public int getItemViewType(int position) {
        int pic1NewsCount = -1;
        if (mNewsList != null) {
            pic1NewsCount = mNewsList.get(0).size();
        }
        if (position < pic1NewsCount) {
            return PIC1_NEWS_TAG;
        } else {
            return PIC3_NEWS_TAG;
        }
    }

}
