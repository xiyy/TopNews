package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.NotWifiWarnEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import de.greenrobot.event.EventBus;

/**
 * Created by zhangxb171 on 2017/10/11.
 */

public class NotWifiWarnDialog {
    public static final int NOT_WIFI_WARN_ONCE = 0;
    public static final int NOT_WIFI_WARN_EVERY_TIME = 1;
    private Context mContext;
    private DialogView mDialogView;
    private RadioButton mRbOnce;
    private RadioButton mRbEveryTime;
    private TextView mCancel;
    private int mLastTimeTag;

    public NotWifiWarnDialog(Context context) {
        this.mContext = context;
        mLastTimeTag = SharedPrefUtil.getInstance(context).getInt(Constants.NOT_WIFI_WARN_SP_KEY);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_not_wifi_warn, null);
        mRbOnce = (RadioButton) view.findViewById(R.id.radio_btn_not_wifi_warn_once);
        mRbOnce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EventBus.getDefault().post(new NotWifiWarnEvent(NOT_WIFI_WARN_ONCE));
                }
                dismiss();
            }
        });
        mRbEveryTime = (RadioButton) view.findViewById(R.id.radio_btn_not_wifi_warn_every_time);
        mRbEveryTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EventBus.getDefault().post(new NotWifiWarnEvent(NOT_WIFI_WARN_EVERY_TIME));
                }
                dismiss();
            }
        });
        if (mLastTimeTag == NOT_WIFI_WARN_ONCE) {
            mRbOnce.setChecked(true);
        } else if (mLastTimeTag == NOT_WIFI_WARN_EVERY_TIME) {
            mRbEveryTime.setChecked(true);
        }
        mCancel = (TextView) view.findViewById(R.id.cancel_not_wifi_warn_dialog);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mDialogView = new DialogView(context, view);
        mDialogView.setGravity(Gravity.CENTER);
        mDialogView.setDimBehind(true);
        mDialogView.setCancelable(true);
        mDialogView.setCanceledOnTouchOutside(true);
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
