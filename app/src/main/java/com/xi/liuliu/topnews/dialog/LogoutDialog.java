package com.xi.liuliu.topnews.dialog;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LogoutEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;
import com.xi.liuliu.topnews.utils.ToastUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/28.
 */

public class LogoutDialog implements View.OnClickListener {
    private DialogView mDialogView;
    private TextView mCancle;
    private TextView mQuit;
    private Activity mSettinsActivity;

    public LogoutDialog(Activity activity) {
        mSettinsActivity = activity;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mSettinsActivity).inflate(R.layout.dialog_log_out, null);
        mCancle = (TextView) view.findViewById(R.id.cancel_dialog_log_out);
        mCancle.setOnClickListener(this);
        mQuit = (TextView) view.findViewById(R.id.confirm_dialog_log_out);
        mQuit.setOnClickListener(this);
        mDialogView = new DialogView(mSettinsActivity, view);
        mDialogView.setGravity(Gravity.CENTER);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setCancelable(true);
        mDialogView.setDimBehind(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_dialog_log_out:
                dismiss();
                break;
            case R.id.confirm_dialog_log_out:
                logout();
        }
    }

    private void logout() {
        SharedPrefUtil.getInstance(mSettinsActivity).putBoolean(Constants.LOGIN_SP_KEY, false);
        EventBus.getDefault().post(new LogoutEvent());
        dismiss();
        if (mSettinsActivity != null) {
            mSettinsActivity.finish();
        }
        ToastUtil.toastInCenter(mSettinsActivity, R.string.settings_toast_log_out_success);
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
