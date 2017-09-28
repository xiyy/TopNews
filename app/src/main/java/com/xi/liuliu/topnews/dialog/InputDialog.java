package com.xi.liuliu.topnews.dialog;

/**
 * Created by zhangxb171 on 2017/9/27.
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.event.InputContentEvent;
import com.xi.liuliu.topnews.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import de.greenrobot.event.EventBus;

/**
 * 输入用户名、介绍
 */
public class InputDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private TextView mInputTitle;
    private EditText mInputContent;
    private TextView mTip;
    private TextView mCancel;
    private TextView mConfirm;
    private int mFrom;
    private String mContent;

    public InputDialog(Context context, int from, String content) {
        mContext = context;
        mFrom = from;
        mContent = content;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_input, null);
        mInputTitle = (TextView) view.findViewById(R.id.title_dialog_input);
        mInputContent = (EditText) view.findViewById(R.id.content_dialog_input);
        startSoftInput();
        mTip = (TextView) view.findViewById(R.id.tip_dialog_input);
        mCancel = (TextView) view.findViewById(R.id.cancel_dialog_input);
        mCancel.setOnClickListener(this);
        mConfirm = (TextView) view.findViewById(R.id.confirm_dialog_input);
        mConfirm.setOnClickListener(this);
        mDialogView = new DialogView(mContext, view);
        mDialogView.setCancelable(true);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setDimBehind(true);
        mDialogView.setFullWidth(true);
        mDialogView.setGravity(Gravity.BOTTOM);
        if (mFrom == InputContentEvent.INPUT_INTRODUCE) {
            mInputTitle.setText(R.string.edit_user_info_introduce_input_title);
            mTip.setVisibility(View.GONE);
        } else {
            mInputTitle.setText(R.string.edit_user_info_user_name_input_title);
            mTip.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(mContent)) {
            mInputContent.setText(mContent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_dialog_input:
                dismiss();
                break;
            case R.id.confirm_dialog_input:
                String inputContent = mInputContent.getText().toString().trim();
                if (TextUtils.isEmpty(inputContent)) {
                    ToastUtil.toastInCenter(mContext, R.string.toast_empty_input_dialog);
                } else {
                    EventBus.getDefault().post(new InputContentEvent(inputContent, mFrom));
                }
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

    public void startSoftInput() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) mInputContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(mInputContent, 0);
                           }
                       },
                200);
    }
}
