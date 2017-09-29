package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.event.GenderSelectorEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by zhangxb171 on 2017/9/26.
 */

public class GetGenderDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private RadioButton mMaleBtn;
    private RadioButton mFemaleBtn;
    private TextView mCancel;
    private int mLastTimeGender;

    public GetGenderDialog(Context context, int lastTimeGender) {
        mContext = context;
        this.mLastTimeGender = lastTimeGender;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_gender_selector, null);
        mMaleBtn = (RadioButton) view.findViewById(R.id.male_dialog_gender_selector);
        mMaleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EventBus.getDefault().post(new GenderSelectorEvent(1));
                }
                dismiss();
            }
        });
        mFemaleBtn = (RadioButton) view.findViewById(R.id.female_dialog_gender_selector);
        mFemaleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    EventBus.getDefault().post(new GenderSelectorEvent(0));
                }
                dismiss();
            }
        });
        if (mLastTimeGender == 1) {
            mMaleBtn.setChecked(true);
        } else if (mLastTimeGender == 0) {
            mFemaleBtn.setChecked(true);
        }
        mCancel = (TextView) view.findViewById(R.id.cancel_dialog_gender_selector);
        mCancel.setOnClickListener(this);
        mDialogView = new DialogView(mContext, view);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_dialog_gender_selector) {
            dismiss();
        }
    }
}
