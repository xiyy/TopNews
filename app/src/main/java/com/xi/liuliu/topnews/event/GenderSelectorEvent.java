package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/9/26.
 */

public class GenderSelectorEvent {
    private int mGenderType;

    public GenderSelectorEvent(int genderType) {
        this.mGenderType = genderType;
    }

    public int getGenderType() {
        return mGenderType;
    }
}
