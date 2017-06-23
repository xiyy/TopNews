package com.xi.liuliu.topnews.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.NewsItemAdapter;
import com.xi.liuliu.topnews.bean.NewsItem;

import java.util.List;

/**
 * Created by liuliu on 2017/6/23.
 */

public class FavorHistFragment extends Fragment {
    private List<NewsItem> mNewsItemList;
    private RecyclerView mRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favor_hist_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_favor_hist_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsItemAdapter newsItemAdapter = new NewsItemAdapter(mNewsItemList, getContext());
        mRecyclerView.setAdapter(newsItemAdapter);
        return view;
    }

    public void setData(List<NewsItem> newsItemList) {
        this.mNewsItemList = newsItemList;
    }
}
