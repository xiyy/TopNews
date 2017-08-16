package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.LoginResultEvent;
import com.xi.liuliu.topnews.http.HttpClient;
import com.xi.liuliu.topnews.utils.CheckPhone;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liuliu on 2017/6/27.
 */

public class LoginDialog implements View.OnClickListener {
    private static final String TAG = "LoginDialog";
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
    private TextView mEntry;
    private EditText mPhoneNumber;
    private SendingDialog mSendingDialog;

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
        mSendIdentifyCode.setOnClickListener(this);
        mLoginWayTitle = (TextView) view.findViewById(R.id.dialog_login_title);
        mEntry = (TextView) view.findViewById(R.id.dialog_login_entry);
        mEntry.setOnClickListener(this);
        mPhoneNumber = (EditText) view.findViewById(R.id.dialog_login_input_phone_number);
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
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.dialog_login_close:
                dismiss();
                break;
            case R.id.dialog_login_way:
                changeLoginWay();
                break;
            case R.id.dialog_login_entry:
                login();
                break;
            case R.id.dialog_login_send_identify_code:
                sendSmsCode();
                break;
        }
    }

    private void sendSmsCode() {
        if (!isPhoneNumberLegal(mPhoneNumber.getText().toString().trim())) {
            Toast toast = Toast.makeText(mContext.getApplicationContext(), R.string.login_dialog_toast_phone_number_illegal, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            if (mSendingDialog == null) {
                mSendingDialog = new SendingDialog(mContext, false);
            }
            mSendingDialog.show();
            new HttpClient().setCallback(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (mSendingDialog != null) {
                                mSendingDialog.dissmiss();
                            }
                            Toast toast = Toast.makeText(mContext.getApplicationContext(), R.string.login_dialog_toast_has_send_sms_code, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    });

                }
            }).requestLoginSmsCode(mPhoneNumber.getText().toString().trim());
        }

    }

    private void login() {
        if (isSmsCodeLegal(mInput.getText().toString().trim())) {
            if (mSendingDialog == null) {
                mSendingDialog = new SendingDialog(mContext, false);
            }
            mSendingDialog.show();
            new HttpClient().setCallback(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (mSendingDialog != null) {
                                mSendingDialog.dissmiss();
                            }
                            Toast toast = Toast.makeText(mContext.getApplicationContext(), R.string.login_dialog_toast_sms_code_error, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        }
                    });

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            SharedPrefUtil.getInstance(mContext).putBoolean(Constants.LOGIN_SP_KEY, true);
                            SharedPrefUtil.getInstance(mContext).putString(Constants.USER_PHONE_NUMBER_SP_KEY, mPhoneNumber.getText().toString().trim());
                            if (mSendingDialog != null) {
                                mSendingDialog.dissmiss();
                            }
                            EventBus.getDefault().post(new LoginResultEvent(true));
                            dismiss();
                        }
                    });
                }
            }).verifySmsCode(mInput.getText().toString().trim(), mPhoneNumber.getText().toString().trim());
        } else {
            Toast toast = Toast.makeText(mContext.getApplicationContext(), R.string.login_dialog_toast_sms_code_error, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
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

    private boolean isPhoneNumberLegal(String phoneNumber) {
        return CheckPhone.isPhoneNum(phoneNumber);
    }

    /**
     * 判断验证码是否是6位数字
     *
     * @param smsCode 验证码
     * @return true代码验证码是6位数字
     */
    private boolean isSmsCodeLegal(String smsCode) {
        if (TextUtils.isEmpty(smsCode)) {
            return false;
        }
        if (smsCode.matches("\\d{6}")) {
            return true;
        }
        return false;
    }
}
