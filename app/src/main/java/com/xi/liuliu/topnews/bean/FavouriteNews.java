package com.xi.liuliu.topnews.bean;

import com.xi.liuliu.topnews.utils.DateUtil;

/**
 * Created by liuliu on 2017/6/23.
 */

public class FavouriteNews {
    private NewsItem newsItem;

    public FavouriteNews(NewsItem newsItem) {
        this.newsItem = newsItem;
    }

    public String getTitle() {
        return newsItem.getTitle();
    }

    public String getIcon1() {
        return newsItem.getThumbnailPic();
    }

    public String getICon2() {
        return newsItem.getThumbnailPic02();
    }

    public String getIcon3() {
        return newsItem.getThumbnailPic03();
    }

    public String getSrc() {
        return newsItem.getAuthorName();
    }

    public String getUrl() {
        return newsItem.getUrl();
    }

    public String getFavouriteTime() {
        String stamp = Long.toString(System.currentTimeMillis());
        return DateUtil.stampToDate(stamp);
    }

}
