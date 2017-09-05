package com.xi.liuliu.topnews.bean;

import java.util.List;

/**
 * Created by liuliu on 2017/6/15.
 */

public class NewsResult {
    private String stat;
    private List<NewsItem> data;
    public List<NewsItem> getData() {
        return data;
    }

    public void setData(List<NewsItem> data) {
        this.data = data;
    }
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

}
