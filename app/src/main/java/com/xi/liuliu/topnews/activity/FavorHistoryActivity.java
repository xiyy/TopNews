package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.FavorHistFragmentPagerAdpter;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.fragment.FavorHistFragment;
import com.xi.liuliu.topnews.utils.DBDao;

import java.util.ArrayList;
import java.util.List;

public class FavorHistoryActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Button mGoBack;
    private ArrayList<NewsItem> mFavouriteList;
    private ArrayList<NewsItem> mReadHistoryList;
    private DBDao mDBDao;
    private FavorHistFragmentPagerAdpter mFavorHistFragmentPagerAdpter;
    private List<FavorHistFragment> mFavorHistFragmentList;
    private List<String> mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favor_history);
        mDBDao = new DBDao(this);
        mFavouriteList = mDBDao.getAllFavourite();
        mReadHistoryList = mDBDao.getAllReadNews();
        fillData();
        init();
    }

    private void init() {
        mGoBack = (Button) findViewById(R.id.go_back_favor_history);
        mGoBack.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout_activity_favor_history);
        mViewPager = (ViewPager) findViewById(R.id.viewPager_activity_favor_history);
        mFavorHistFragmentPagerAdpter = new FavorHistFragmentPagerAdpter(getSupportFragmentManager(), mFavorHistFragmentList, mTabs);
        mViewPager.setAdapter(mFavorHistFragmentPagerAdpter);//给ViewPager设置适配器
        mViewPager.setCurrentItem(getIntent().getIntExtra("viewPager_current_item",0));//当前显示的页卡
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mFavorHistFragmentPagerAdpter);//给Tabs设置适配器
    }

    private void fillData() {
        mFavorHistFragmentList = new ArrayList<>(2);
        FavorHistFragment favorFragment = new FavorHistFragment();
        favorFragment.setData(mFavouriteList);
        FavorHistFragment histFragment = new FavorHistFragment();
        histFragment.setData(mReadHistoryList);
        mFavorHistFragmentList.add(favorFragment);
        mFavorHistFragmentList.add(histFragment);
        mTabs = new ArrayList<>(2);
        mTabs.add(Constants.FAVOURITE_HISTORY[0]);
        mTabs.add(Constants.FAVOURITE_HISTORY[1]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back_favor_history:
                finish();
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.zoomout);
    }
}
