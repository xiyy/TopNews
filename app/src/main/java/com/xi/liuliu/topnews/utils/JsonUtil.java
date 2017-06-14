package com.xi.liuliu.topnews.utils;

import android.text.TextUtils;

import com.xi.liuliu.topnews.bean.NewsWith1Pic;
import com.xi.liuliu.topnews.bean.NewsWith3Pic;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuliu on 2017/6/14.
 */

public class JsonUtil {
    public List<List> getNewSList(String jsonStr) {
        List<List> newsList = new ArrayList<>();
        List<NewsWith1Pic> pic1List = new ArrayList<>();
        List<NewsWith3Pic> pic3List = new ArrayList<>();
        if (!TextUtils.isEmpty(jsonStr)) {
            try {
                JSONObject response = new JSONObject(jsonStr);
                JSONObject result = response.getJSONObject("result");
                JSONArray data = result.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    JSONObject each = data.getJSONObject(i);
                    String title = each.getString("title");
                    String newsSrc = each.getString("author_name");
                    String time = each.getString("date");
                    String url = each.getString("url");
                    String icon1 = null;
                    String icon2 = null;
                    String icon3 = null;
                    if (each.has("thumbnail_pic_s")) {
                        icon1 = each.getString("thumbnail_pic_s");
                    }
                    if (each.has("thumbnail_pic_s02")) {
                        icon2 = each.getString("thumbnail_pic_s02");
                    }
                    if (each.has("thumbnail_pic_s03")) {
                        icon3 = each.getString("thumbnail_pic_s03");
                    }
                    if (!TextUtils.isEmpty(icon1) && TextUtils.isEmpty(icon2)) {
                        NewsWith1Pic news = new NewsWith1Pic();
                        news.setmTitle(title).setmNewsSrc(newsSrc).setmTime(time).setmNewUrl(url).setmIconUrl(icon1);
                        pic1List.add(news);
                    }
                    if (!TextUtils.isEmpty(icon1) && !TextUtils.isEmpty(icon2) && !TextUtils.isEmpty(icon3)) {
                        NewsWith3Pic news = new NewsWith3Pic();
                        news.setmTitle(title).setmNewsSrc(newsSrc).setmTime(time).setmNewUrl(url).setmIcon1Url(icon1).setmIcon2Url(icon2).setmIcon3Url(icon3);
                        pic3List.add(news);
                    }
                }
                newsList.add(pic1List);
                newsList.add(pic3List);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newsList;
    }
}
