package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.SettingsActivity;

/**
 * Created by zhangxb171 on 2017/9/21.
 */

public class ClearCacheDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private TextView mConfirm;
    private TextView mCancel;
    private SettingsActivity.ClearCacheListener mClearCacheListener;

    public ClearCacheDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_clear_cache, null);
        mConfirm = (TextView) view.findViewById(R.id.confirm_clear_cache_dialog);
        mConfirm.setOnClickListener(this);
        mCancel = (TextView) view.findViewById(R.id.cancel_clear_cache_dialog);
        mCancel.setOnClickListener(this);
        mDialogView = new DialogView(mContext, view, R.style.share_dialog_animation);
        mDialogView.setGravity(Gravity.BOTTOM);
        mDialogView.setFullWidth(true);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setDimBehind(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_clear_cache_dialog:

                clearCache();
                dismiss();
                break;
            case R.id.cancel_clear_cache_dialog:
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

    private void clearCache() {
        SettingsActivity.ClearCacheRunnable runnable = new SettingsActivity.ClearCacheRunnable(mClearCacheListener);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void setClearCacheListener(SettingsActivity.ClearCacheListener listener) {
        this.mClearCacheListener = listener;
    }

}
