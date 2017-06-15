package com.xi.liuliu.topnews.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xi.liuliu.topnews.bean.News;
import com.xi.liuliu.topnews.bean.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliu on 2017/6/14.
 */

public class GsonUtil {
    public List<List> getNewsList(String jsonStr) {
        List<List> newsList = new ArrayList<>();
        List<NewsItem> pic1List = new ArrayList<>();
        List<NewsItem> pic3List = new ArrayList<>();
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(jsonStr)) {
            News news = gson.fromJson(jsonStr, News.class);
            List<NewsItem> itemList = news.getResult().getData();
            if (itemList != null) {
                for (NewsItem newsItem : itemList) {
                    if (!TextUtils.isEmpty(newsItem.getThumbnailPic()) && TextUtils.isEmpty(newsItem.getThumbnailPic02())) {
                        pic1List.add(newsItem);
                    }
                    if (!TextUtils.isEmpty(newsItem.getThumbnailPic()) && !TextUtils.isEmpty(newsItem.getThumbnailPic02()) && !TextUtils.isEmpty(newsItem.getThumbnailPic03())) {
                        pic3List.add(newsItem);
                    }
                }
                newsList.add(pic1List);
                newsList.add(pic3List);
            }
        }
        return newsList;
    }
}
