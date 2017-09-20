package com.xi.liuliu.topnews.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xi.liuliu.topnews.bean.News;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.bean.NewsResult;

import java.util.List;

/**
 * Created by liuliu on 2017/6/14.
 */

public class GsonUtil {
    public static List<NewsItem> getNewsList(String jsonStr) {
        List<NewsItem> itemList = null;
        NewsResult newsResult = null;
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(jsonStr)) {
            News news = gson.fromJson(jsonStr, News.class);
            //要判null，预防crash
            if (news != null) {
                newsResult = news.getResult();
            }
            if (newsResult != null) {
                itemList = newsResult.getData();
            }
        }
        return itemList;
    }
}
