package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/21.
 */

public class LoadingDialog {
    private Context mContext;
    private DialogView mDialogView;
    private TextView mLoadingMessage;

    public LoadingDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_loading, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.loading_layout_image_view);
        mLoadingMessage = (TextView) view.findViewById(R.id.loading_layout_text_view);
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

    public LoadingDialog setLoadingMessage(int messageId) {
        mLoadingMessage.setText(messageId);
        return this;
    }

    public LoadingDialog setCancelable(boolean cancel) {
        mDialogView.setCancelable(cancel);
        return this;
    }
}
