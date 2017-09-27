package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/9/27.
 */

public class DatePickerEvent {
    private String mDate;

    public DatePickerEvent(String date) {
        mDate = date;
    }

    public String getDate() {
        return mDate;
    }
}
