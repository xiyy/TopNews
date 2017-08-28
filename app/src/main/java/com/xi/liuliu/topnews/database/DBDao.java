package com.xi.liuliu.topnews.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xi.liuliu.topnews.bean.FavouriteNews;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.bean.ReadNews;

import java.util.ArrayList;

/**
 * Created by liuliu on 2017/6/23.
 */

public class DBDao {
    private Context mContext;
    private DBOpenHelper mDBOpenHelper;

    public DBDao(Context context) {
        mContext = context;
        mDBOpenHelper = new DBOpenHelper(context);
    }

    public boolean insertFavourite(FavouriteNews favouriteNews) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        if (database.isOpen()) {
            database.execSQL(
                    "insert into myFavourite(title,icon1,icon2,icon3,src,url,favouriteTime) values (?,?,?,?,?,?,?)",
                    new Object[]{favouriteNews.getTitle(), favouriteNews.getIcon1(), favouriteNews.getICon2(), favouriteNews.getIcon3(), favouriteNews.getSrc(), favouriteNews.getUrl(), favouriteNews.getFavouriteTime()});
            database.close();
            return true;

        }
        return false;
    }

    public boolean deleteAllFavourite() {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        if (database.isOpen()) {
            database.execSQL("delete from myFavourite");
            database.close();
            return true;
        }
        return false;
    }

    public boolean deleteFavourite(FavouriteNews favouriteNews) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        if (database.isOpen()) {
            database.execSQL("delete from myFavourite where url=?", new String[]{favouriteNews.getUrl()});
            database.close();
            return true;
        }
        return false;
    }

    public boolean isFavouriteExist(FavouriteNews favouriteNews) {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        if (database.isOpen()) {
            Cursor cursor = database.rawQuery("select * from myFavourite where url=?", new String[]{favouriteNews.getUrl()});
            if (cursor != null && cursor.getCount() >= 1) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<NewsItem> getAllFavourite() {
        ArrayList<NewsItem> newsList = new ArrayList<>();
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        if (database.isOpen()) {
            Cursor cursor = database.rawQuery("select * from myFavourite", null);
            if (cursor != null && cursor.getCount() >= 1) {
                for (int i = cursor.getCount() - 1; i >= 0; i--) {
                    cursor.moveToPosition(i);
                    NewsItem newsItem = new NewsItem();
                    newsItem.setTitle(cursor.getString(1));
                    newsItem.setThumbnailPic(cursor.getString(2));
                    newsItem.setThumbnailPic02(cursor.getString(3));
                    newsItem.setThumbnailPic03(cursor.getString(4));
                    newsItem.setAuthorName(cursor.getString(5));
                    newsItem.setUrl(cursor.getString(6));
                    //收藏的时间
                    newsItem.setDate(cursor.getString(7));
                    newsList.add(newsItem);
                }
                cursor.close();
                database.close();
            }
        }
        return newsList;
    }

    public boolean insertHistory(ReadNews readNews) {
        SQLiteDatabase database = mDBOpenHelper.getWritableDatabase();
        if (database.isOpen()) {
            database.execSQL(
                    "insert into myReadHistory(title,icon1,icon2,icon3,src,url,readTime) values (?,?,?,?,?,?,?)",
                    new Object[]{readNews.getTitle(), readNews.getIcon1(), readNews.getICon2(), readNews.getIcon3(), readNews.getSrc(), readNews.getUrl(), readNews.getReadTime()});
            database.close();
            return true;

        }
        return false;
    }

    public ArrayList<NewsItem> getAllReadNews() {
        ArrayList<NewsItem> newsList = new ArrayList<>();
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        if (database.isOpen()) {
            Cursor cursor = database.rawQuery("select * from myReadHistory", null);
            if (cursor != null && cursor.getCount() >= 1) {
                for (int i = cursor.getCount() - 1; i >= 0; i--) {
                    cursor.moveToPosition(i);
                    NewsItem newsItem = new NewsItem();
                    newsItem.setTitle(cursor.getString(1));
                    newsItem.setThumbnailPic(cursor.getString(2));
                    newsItem.setThumbnailPic02(cursor.getString(3));
                    newsItem.setThumbnailPic03(cursor.getString(4));
                    newsItem.setAuthorName(cursor.getString(5));
                    newsItem.setUrl(cursor.getString(6));
                    //收藏的时间
                    newsItem.setDate(cursor.getString(7));
                    newsList.add(newsItem);
                }
                cursor.close();
                database.close();
            }
        }
        return newsList;
    }

}
