package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/27.
 */

public class LoginDialog {
    private Context mContext;
    private DialogView mDialogView;

    public LoginDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_login, null);
        mDialogView = new DialogView(mContext, view);
        mDialogView.setFullScreen(true);
        mDialogView.setCanceledOnTouchOutside(false);
        mDialogView.setCancelable(true);
        mDialogView.setGravity(Gravity.BOTTOM);
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
}
