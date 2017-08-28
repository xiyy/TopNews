package com.xi.liuliu.topnews.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liuliu on 2017/6/23.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "top_news_db";
    private static final int DB_VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists myFavourite(id integer primary key autoincrement, title varchar(100),icon1 varchar(100),icon2 varchar(100),icon3 varchar(100),src varchar(20),url varchar(100),favouriteTime varchar(20))");
        db.execSQL("create table if not exists myReadHistory(id integer primary key autoincrement, title varchar(100),icon1 varchar(100),icon2 varchar(100),icon3 varchar(100),src varchar(20),url varchar(100),readTime varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
