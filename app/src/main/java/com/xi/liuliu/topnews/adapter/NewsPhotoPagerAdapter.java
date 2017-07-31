package com.xi.liuliu.topnews.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bm.library.PhotoView;

import java.util.List;

/**
 * Created by zhangxb171 on 2017/7/31.
 */

public class NewsPhotoPagerAdapter extends PagerAdapter {
    private List<PhotoView> mPhotoViewList;

    public NewsPhotoPagerAdapter(List<PhotoView> photoViewList) {
        mPhotoViewList = photoViewList;
    }

    @Override
    public int getCount() {
        return mPhotoViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mPhotoViewList.get(position));
        return mPhotoViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mPhotoViewList.get(position));
    }
}
