package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xi.liuliu.topnews.bean.FavouriteNews;

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

    public boolean insert(FavouriteNews favouriteNews) {
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

    public boolean deleteAll() {
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

    public boolean isExist(FavouriteNews favouriteNews) {
        SQLiteDatabase database = mDBOpenHelper.getReadableDatabase();
        if (database.isOpen()) {
            Cursor cursor = database.rawQuery("select * from myFavourite where url=?", new String[]{favouriteNews.getUrl()});
            if (cursor != null && cursor.getCount() >= 1) {
                return true;
            }
        }
        return false;
    }
}
