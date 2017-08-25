package com.xi.liuliu.topnews.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.ChannelFragmentPagerAdapter;
import com.xi.liuliu.topnews.constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliu on 2017/6/19.
 */

public class HomeFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<ChannelFragment> mFragmentsList;
    private List<String> mChannelsList;
    private AppCompatActivity mActivity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        fillDatas();
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager_fragment_home);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout_fragment_home);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ChannelFragmentPagerAdapter channelFragmentPagerAdapter = new ChannelFragmentPagerAdapter(mActivity.getSupportFragmentManager(), mFragmentsList, mChannelsList);
        mViewPager.setAdapter(channelFragmentPagerAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(channelFragmentPagerAdapter);//给Tabs设置适配器
        return view;
    }

    private void fillDatas() {
        mFragmentsList = new ArrayList<>();
        mChannelsList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            mChannelsList.add(Constants.CHANNELS[i]);
        }
        for (int i = 0; i < mChannelsList.size(); i++) {
            ChannelFragment channelFragment = new ChannelFragment();
            channelFragment.setIndex(i);
            mFragmentsList.add(channelFragment);
        }
    }

    public void setActivity(AppCompatActivity activity) {
        mActivity = activity;
    }
}
