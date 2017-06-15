package com.xi.liuliu.topnews.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuliu on 2017/6/15.
 */

public class NewsItem {
    private String uniquekey;
    private String title;
    private String date;
    private String category;
    @SerializedName("author_name")
    private String authorName;
    private String url;
    @SerializedName("thumbnail_pic_s")
    private String thumbnailPic;
    @SerializedName("thumbnail_pic_s02")
    private String thumbnailPic02;
    @SerializedName("thumbnail_pic_s03")
    private String thumbnailPic03;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    public String getThumbnailPic02() {
        return thumbnailPic02;
    }

    public void setThumbnailPic02(String thumbnailPic02) {
        this.thumbnailPic02 = thumbnailPic02;
    }

    public String getThumbnailPic03() {
        return thumbnailPic03;
    }

    public void setThumbnailPic03(String thumbnailPic03) {
        this.thumbnailPic03 = thumbnailPic03;
    }


}
