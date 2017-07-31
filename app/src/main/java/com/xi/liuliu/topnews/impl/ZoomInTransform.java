package com.xi.liuliu.topnews.impl;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zhangxb171 on 2017/7/19.
 */

public class ZoomInTransform implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        int height = page.getHeight();
        //这里只对右边的View做了操作
        if (position > 0 && position <= 1) {
            //position是1.0->0,但是沒有等于0
            //设置该View的X轴不动
            page.setTranslationX(-width * position);
            //设置缩放中心在View的正中心
            page.setPivotX(width / 2);
            page.setPivotY(height / 2);
            //设置放缩比例（0.0，1.0]
            page.setScaleX(1 - position);
            page.setScaleY(1 - position);

        } else if (position >= -1 && position < 0) {//对左侧View操作

        } else {//对中间View操作

        }
    }
}
