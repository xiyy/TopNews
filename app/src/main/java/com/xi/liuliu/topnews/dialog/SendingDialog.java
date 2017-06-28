package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/26.
 */

public class SendingDialog {
    private Context mContext;
    private DialogView mDialogView;
    private String mMessage;
    private boolean isMessageVisiable = true;

    public SendingDialog(Context context, String message) {
        mContext = context;
        mMessage = message;
        init();
    }

    public SendingDialog(Context context) {
        this(context, null);
    }

    public SendingDialog(Context context, boolean isMessageVisiable) {
        mContext = context;
        this.isMessageVisiable = isMessageVisiable;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_sending, null);
        TextView textView = (TextView) view.findViewById(R.id.loading_layout_text_view);
        if (mMessage != null) {
            textView.setText(mMessage);
        }
        if (!isMessageVisiable) {
            textView.setVisibility(View.GONE);
        }
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
