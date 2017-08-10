package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/8/10.
 */

public class LiveFragmentVisibleEvent {
    private boolean isVisible;

    public LiveFragmentVisibleEvent(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isFragmentVisible() {
        return isVisible;
    }
}
