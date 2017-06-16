package com.xi.liuliu.topnews.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.NewsItemAdapter;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.http.HttpUtil;
import com.xi.liuliu.topnews.utils.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
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
    private static final int MESSAGE_REFRESH_NEWS = 1002;
    private int mIndex;
    private View mFragmentView;
    private RecyclerView mRecyclerView;
    private NewsItemAdapter mNewsItemAdapter;
    private Handler mHandler;
    //所有刷新获取的数据
    private List<List<NewsItem>> mAllNewsList;
    private int mLastVisibleItemPosition;
    private LinearLayoutManager mLinearLayoutManager;
    private RelativeLayout mLoadingRlt;

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
        mLoadingRlt = (RelativeLayout) mFragmentView.findViewById(R.id.channel_fragment_layout_rlt_footer);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == mNewsItemAdapter.getItemCount()) {
                    mLoadingRlt.setVisibility(View.VISIBLE);
                    mHandler.sendEmptyMessage(MESSAGE_REFRESH_NEWS);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mLastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleResult(msg);
            }
        };
        mAllNewsList = new ArrayList<>();
        return mFragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new HttpUtil().setCallback(this).requestNews(Constants.CHANNELS_PARAM[mIndex]);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAllNewsList = null;
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String reponseBody = response.body().string();
        List<NewsItem> list = GsonUtil.getNewsList(reponseBody);
        mAllNewsList.add(list);
        mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
    }

    public void handleResult(Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case MESSAGE_SET_ADAPTER:
                    mNewsItemAdapter = new NewsItemAdapter(getContext(), mAllNewsList);
                    mRecyclerView.setAdapter(mNewsItemAdapter);
                    break;
                case MESSAGE_REFRESH_NEWS:
                    new HttpUtil().setCallback(new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String reponseBody = response.body().string();
                            List<NewsItem> list = GsonUtil.getNewsList(reponseBody);
                            mAllNewsList.add(list);
                            mHandler.sendEmptyMessage(MESSAGE_SET_ADAPTER);
                            mLoadingRlt.post(new Runnable() {
                                @Override
                                public void run() {
                                    mLoadingRlt.setVisibility(View.GONE);
                                }
                            });
                        }
                    }).requestNews(Constants.CHANNELS_PARAM[mIndex]);
                    break;
                default:
            }
        }
    }
}
