package com.xi.liuliu.topnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by zhangxb171 on 2017/9/8.
 */

public class ImgPickerGridView extends GridView {
    public ImgPickerGridView(Context context) {
        super(context);
    }

    public ImgPickerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImgPickerGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重新测量GridView的高度，返回重新测量GridView的高度的实际高度
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
