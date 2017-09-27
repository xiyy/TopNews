package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/9/27.
 */

public class InputContentEvent {
    public static final int INPUT_USER_NAME = 0;
    public static final int INPUT_INTRODUCE = 1;
    private String mInputContent;
    private int mFrom;

    public InputContentEvent(String input, int from) {
        mInputContent = input;
        mFrom = from;
    }

    public String getInputContent() {
        return mInputContent;
    }

    public int getInputFrom() {
        return mFrom;
    }
}
