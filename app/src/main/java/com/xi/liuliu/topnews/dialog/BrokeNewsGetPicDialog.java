package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by zhangxb171 on 2017/9/7.
 */

public class BrokeNewsGetPicDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private TextView mCamera;
    private TextView mAlbum;
    private TextView mCancle;

    public BrokeNewsGetPicDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_broke_news_get_pic_dialog, null);
        mCamera = (TextView) view.findViewById(R.id.camera_broke_news_get_pic_dialog);
        mCamera.setOnClickListener(this);
        mAlbum = (TextView) view.findViewById(R.id.album_broke_news_get_pic_dialog);
        mAlbum.setOnClickListener(this);
        mCancle = (TextView) view.findViewById(R.id.cancle_broke_news_get_pic_dialog);
        mCancle.setOnClickListener(this);
        mDialogView = new DialogView(mContext, view, R.style.share_dialog_animation);
        mDialogView.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mDialogView.setGravity(Gravity.BOTTOM);
        mDialogView.setFullWidth(true);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setDimBehind(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera_broke_news_get_pic_dialog:
                getPicFromCamera();
                break;
            case R.id.album_broke_news_get_pic_dialog:
                getPicFromAlbum();
                break;
            case R.id.cancle_broke_news_get_pic_dialog:
                dismiss();
                break;
        }
    }

    public void show() {
        if (mDialogView != null) {
            mDialogView.showDialog();
        }
    }

    public void dismiss() {
        if (mDialogView != null) {
            mDialogView.dismissDialog();
        }
    }

    private void getPicFromCamera() {


    }

    private void getPicFromAlbum() {

    }
}
