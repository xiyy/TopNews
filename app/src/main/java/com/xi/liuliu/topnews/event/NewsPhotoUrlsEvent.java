package com.xi.liuliu.topnews.event;

/**
 * Created by zhangxb171 on 2017/7/28.
 */

public class NewsPhotoUrlsEvent {
    private String[] mPhotoUrls;

    public NewsPhotoUrlsEvent(String[] photoUrls) {
        mPhotoUrls = photoUrls;
    }

    public String[] getHtmlCode() {
        return mPhotoUrls;
    }
}
