package com.xi.liuliu.topnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xi.liuliu.topnews.fragment.ChannelFragment;

import java.util.List;

/**
 * Created by liuliu on 2017/6/12.
 */

public class ChannelFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<ChannelFragment> mFragmentsList;
    private List<String> mChannels;

    public ChannelFragmentPagerAdapter(FragmentManager fm, List<ChannelFragment> fragments, List<String> channels) {
        super(fm);
        mFragmentsList = fragments;
        mChannels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {

        return mChannels.size();
    }

    // TabLayout关联viewpager时会调用此方法.
    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position);
    }

}
