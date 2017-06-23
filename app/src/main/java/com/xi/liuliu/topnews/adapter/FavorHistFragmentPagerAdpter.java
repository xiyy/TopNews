package com.xi.liuliu.topnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xi.liuliu.topnews.fragment.FavorHistFragment;

import java.util.List;

/**
 * Created by liuliu on 2017/6/23.
 */

public class FavorHistFragmentPagerAdpter extends FragmentPagerAdapter {
    private List<FavorHistFragment> mFragmentList;
    private List<String> mTabs;

    public FavorHistFragmentPagerAdpter(FragmentManager fm, List<FavorHistFragment> fragmentList, List<String> tabs) {
        super(fm);
        mFragmentList = fragmentList;
        mTabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    // TabLayout关联viewpager时会调用此方法.
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position);
    }
}
