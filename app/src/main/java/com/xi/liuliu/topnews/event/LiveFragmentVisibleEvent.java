package com.xi.liuliu.topnews.event;

/**
 * Created by liuliu on 2017/6/19.
 */

public class LiveFragmentVisibleEvent {
    private boolean isLiveFragmentVisible;

    public LiveFragmentVisibleEvent(boolean isLiveFragmentVisible) {
        this.isLiveFragmentVisible = isLiveFragmentVisible;
    }

    public boolean getFragmentVisibility() {
        return isLiveFragmentVisible;
    }
}
