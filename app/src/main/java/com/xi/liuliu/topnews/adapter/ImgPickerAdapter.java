package com.xi.liuliu.topnews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.xi.liuliu.topnews.R;

import java.util.ArrayList;

/**
 * Created by zhangxb171 on 2017/9/8.
 */

public class ImgPickerAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Bitmap> mBitmapList;

    public ImgPickerAdapter(Context context, ArrayList<Bitmap> list) {
        mContext = context;
        mBitmapList = list;
    }

    @Override
    public int getCount() {
        return mBitmapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBitmapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_broke_news_img, null);
        } else {
            view = convertView;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_item_broke_news_pic);
        imageView.setImageBitmap(mBitmapList.get(position));
        return view;
    }
}
