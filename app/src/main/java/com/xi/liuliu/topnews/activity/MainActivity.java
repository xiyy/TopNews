package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.ChannelFragmentPagerAdapter;
import com.xi.liuliu.topnews.fragment.ChannelFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<ChannelFragment> mFragmentsList;
    private List<String> mChannelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillDatas();
        mViewPager = (ViewPager) findViewById(R.id.main_activity_ViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.main_activity_TabLayout);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ChannelFragmentPagerAdapter channelFragmentPagerAdapter = new ChannelFragmentPagerAdapter(getSupportFragmentManager(), mFragmentsList, mChannelsList);
        mViewPager.setAdapter(channelFragmentPagerAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(channelFragmentPagerAdapter);//给Tabs设置适配器
    }

    private void fillDatas() {
        mFragmentsList = new ArrayList<>();
        mChannelsList = new ArrayList<>();
        mChannelsList.add("头条");
        mChannelsList.add("社会");
        mChannelsList.add("国内");
        mChannelsList.add("娱乐");
        mChannelsList.add("体育");
        mChannelsList.add("军事");
        mChannelsList.add("科技");
        mChannelsList.add("财经");
        mChannelsList.add("时尚");
        for (int i = 0; i < mChannelsList.size(); i++) {
            ChannelFragment channelFragment = new ChannelFragment();
            channelFragment.setChannelTitle(mChannelsList.get(i));
            mFragmentsList.add(channelFragment);
        }
    }
}
