package com.xi.liuliu.topnews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.NewsItemAdapter;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.http.HttpUtil;
import com.xi.liuliu.topnews.utils.JsonUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liuliu on 2017/6/12.
 */

public class ChannelFragment extends Fragment implements Callback {
    private static final String TAG = "ChannelFragment";
    private static final int MESSAGE_SET_ADAPTER = 1001;
    private int mIndex;
    private View mFragmentView;
    private RecyclerView mRecyclerView;
    private NewsItemAdapter mNewsItemAdapter;
    private Handler mHandler;
    private List<List> mNewsList;

    public void setIndex(int index) {
        mIndex = index;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.channel_fragment_layout, container, false);
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.channel_fragment_layout_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == MESSAGE_SET_ADAPTER) {
                    mNewsItemAdapter = new NewsItemAdapter(getContext(), mNewsList);
                    mRecyclerView.setAdapter(mNewsItemAdapter);
                }
            }
        };

        return mFragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new HttpUtil().setCallback(this).requestNews(Constants.CHANNELS_PARAM[mIndex]);
    }


    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String reponseBody = response.body().string();
        mNewsList = new JsonUtil().getNewSList(reponseBody);
        mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
        Log.i(TAG, "index:" + mIndex + "data:" + reponseBody);
    }
}
