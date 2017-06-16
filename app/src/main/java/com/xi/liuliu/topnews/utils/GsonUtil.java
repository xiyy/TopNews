package com.xi.liuliu.topnews.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xi.liuliu.topnews.bean.News;
import com.xi.liuliu.topnews.bean.NewsItem;

import java.util.List;

/**
 * Created by liuliu on 2017/6/14.
 */

public class GsonUtil {
    public static List<NewsItem> getNewsList(String jsonStr) {
        List<NewsItem> itemList = null;
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(jsonStr)) {
            News news = gson.fromJson(jsonStr, News.class);
            itemList = news.getResult().getData();
        }
        return itemList;
    }
}
