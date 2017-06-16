package com.xi.liuliu.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.item.NewsItemWith1Pic;
import com.xi.liuliu.topnews.item.NewsItemWith3Pic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliu on 2017/6/13.
 */

public class NewsItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<NewsItem> mAllNewsList;
    private static final int PIC1_NEWS_TAG = 0;
    private static final int PIC3_NEWS_TAG = 1;

    public NewsItemAdapter(Context context, List<List<NewsItem>> allNewsList) {
        mContext = context;
        mAllNewsList = convertToNewsLit(allNewsList);
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
            NewsItemWith1Pic.onBind(mContext, holder, position, mAllNewsList.get(position));
        } else {
            NewsItemWith3Pic.onBind(mContext, holder, position, mAllNewsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mAllNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPic1(mAllNewsList.get(position))) {
            return PIC1_NEWS_TAG;
        } else {
            return PIC3_NEWS_TAG;
        }
    }

    public List<NewsItem> convertToNewsLit(List<List<NewsItem>> allNewsList) {
        List<NewsItem> list = new ArrayList<>();
        for (int i = 0; i < allNewsList.size(); i++) {
            List<NewsItem> newsList = allNewsList.get(i);
            for (int j = 0; j < newsList.size(); j++) {
                NewsItem item = newsList.get(j);
                if (isPic1(item) || isPic3(item)) {
                    list.add(item);
                }
            }
        }
        return list;
    }

    public boolean isPic1(NewsItem item) {
        if (item != null) {
            if (!TextUtils.isEmpty(item.getThumbnailPic()) && TextUtils.isEmpty(item.getThumbnailPic02())) {
                return true;
            }
        }
        return false;
    }

    public boolean isPic3(NewsItem item) {
        if (item != null) {
            if (!TextUtils.isEmpty(item.getThumbnailPic()) && !TextUtils.isEmpty(item.getThumbnailPic02()) && !TextUtils.isEmpty(item.getThumbnailPic03())) {
                return true;
            }
        }
        return false;
    }
}
