package com.xi.liuliu.topnews.bean;

/**
 * Created by liuliu on 2017/6/23.
 */

public class FavouriteNews {
    private NewsItem newsItem;
    private int mFavouriteTime;

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

    public int getFavouriteTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public void setFavouriteTime() {
        mFavouriteTime = (int) (System.currentTimeMillis() / 1000);
    }
}
