package com.xi.liuliu.topnews.event;

/**
 * Created by liuliu on 2017/6/19.
 */

public class HomeFragmentVisibleEvent {
    private boolean isHomeFragmentVisible;

    public HomeFragmentVisibleEvent(boolean isHomeFragmentVisible) {
        this.isHomeFragmentVisible = isHomeFragmentVisible;
    }

    public boolean getFragmentVisibility() {
        return isHomeFragmentVisible;
    }
}
