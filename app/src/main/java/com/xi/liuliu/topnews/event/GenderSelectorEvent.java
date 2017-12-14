package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/9/26.
 */

public class GenderSelectorEvent {
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 0;
    private int mGenderType;

    public GenderSelectorEvent(int genderType) {
        this.mGenderType = genderType;
    }

    public int getGenderType() {
        return mGenderType;
    }
}
