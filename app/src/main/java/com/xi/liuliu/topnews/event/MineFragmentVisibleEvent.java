package com.xi.liuliu.topnews.event;

/**
 * Created by liuliu on 2017/6/19.
 */

public class MineFragmentVisibleEvent {
    private boolean isMineFragmentVisible;

    public MineFragmentVisibleEvent(boolean isMineFragmentVisible) {
        this.isMineFragmentVisible = isMineFragmentVisible;
    }

    public boolean getFragmentVisibility() {
        return isMineFragmentVisible;
    }
}
