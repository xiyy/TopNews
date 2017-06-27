package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/27.
 */

public class LoginDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private static final int LOGIN_WITH_OUT_PASS = 1001;
    private static final int LOGIN_WITH_PASS = 1002;
    private int mCurrentLoginWay = LOGIN_WITH_OUT_PASS;
    private Button mClose;
    private TextView mLoginWay;
    private TextView mLoginHint;
    private EditText mInput;
    private TextView mFindPassword;
    private TextView mSendIdentifyCode;
    private TextView mLoginWayTitle;

    public LoginDialog(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_login, null);
        mClose = (Button) view.findViewById(R.id.dialog_login_close);
        mClose.setOnClickListener(this);
        mLoginWay = (TextView) view.findViewById(R.id.dialog_login_way);
        mLoginWay.setOnClickListener(this);
        mLoginHint = (TextView) view.findViewById(R.id.dialog_login_hint);
        mInput = (EditText) view.findViewById(R.id.dialog_login_input);
        mFindPassword = (TextView) view.findViewById(R.id.dialog_login_find_password);
        mSendIdentifyCode = (TextView) view.findViewById(R.id.dialog_login_send_identify_code);
        mLoginWayTitle = (TextView) view.findViewById(R.id.dialog_login_title);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_login_close:
                dismiss();
                break;
            case R.id.dialog_login_way:
                changeLoginWay();
                break;
        }
    }

    private void changeLoginWay() {
        if (mCurrentLoginWay == LOGIN_WITH_OUT_PASS) {
            mCurrentLoginWay = LOGIN_WITH_PASS;
            mLoginWay.setText(R.string.login_with_not_password);
            mLoginHint.setVisibility(View.INVISIBLE);
            mInput.setHint(R.string.logint_dialog_password);
            mFindPassword.setVisibility(View.VISIBLE);
            mSendIdentifyCode.setVisibility(View.GONE);
            mLoginWayTitle.setText(R.string.login_with_count_password);
        } else {
            mCurrentLoginWay = LOGIN_WITH_OUT_PASS;
            mLoginWay.setText(R.string.login_with_count_password);
            mLoginHint.setVisibility(View.VISIBLE);
            mInput.setHint(R.string.login_input_identifying_code);
            mFindPassword.setVisibility(View.GONE);
            mSendIdentifyCode.setVisibility(View.VISIBLE);
            mLoginWayTitle.setText(R.string.login_dialog_with_identify_code_title);
        }
    }
}
