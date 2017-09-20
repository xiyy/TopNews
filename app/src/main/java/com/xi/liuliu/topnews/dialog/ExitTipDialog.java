package com.xi.liuliu.topnews.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by zhangxb171 on 2017/9/20.
 */

public class ExitTipDialog implements View.OnClickListener {
    private Activity mActivity;
    private DialogView mDialogView;
    private TextView mQuit;
    private TextView mCancel;

    public ExitTipDialog(Activity activity) {
        mActivity = activity;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_exit_tip, null);
        mQuit = (TextView) view.findViewById(R.id.quit_dialog_tip_exit);
        mQuit.setOnClickListener(this);
        mCancel = (TextView) view.findViewById(R.id.cancel_dialog_tip_exit);
        mCancel.setOnClickListener(this);
        mDialogView = new DialogView(mActivity, view);
        mDialogView.setGravity(Gravity.CENTER);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setCancelable(true);
        mDialogView.setDimBehind(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_dialog_tip_exit:
                dismiss();
                break;
            case R.id.quit_dialog_tip_exit:
                dismiss();
                mActivity.finish();
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
}
