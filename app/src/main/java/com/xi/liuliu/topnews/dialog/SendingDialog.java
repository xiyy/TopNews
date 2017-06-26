package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/26.
 */

public class SendingDialog {
    private Context mContext;
    private DialogView mDialogView;

    public SendingDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sending, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.loading_layout_image_view);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
        mDialogView = new DialogView(mContext, view);
    }

    public void show() {
        if (mDialogView != null) {
            mDialogView.showDialog();
        }
    }

    public void dissmiss() {
        if (mDialogView != null) {
            mDialogView.dismissDialog();
        }
    }
}
