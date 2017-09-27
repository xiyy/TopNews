package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.DatePickerEvent;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;
import com.xi.liuliu.topnews.utils.ToastUtil;

import java.util.Calendar;

import de.greenrobot.event.EventBus;

/**
 * Created by zhangxb171 on 2017/9/27.
 */

public class DatePickerDialog {
    private Context mContext;
    private DialogView mDialogView;
    private DatePicker mDatePicker;
    private TextView mDate;
    private TextView mFinished;
    private int mYear = -1;
    private int mMonth = -1;
    private int mDay = -1;

    public DatePickerDialog(Context context) {
        mContext = context;
        initView();
        initDate();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_date_picker, null);
        mDatePicker = (DatePicker) view.findViewById(R.id.date_picker);
        mDate = (TextView) view.findViewById(R.id.date_dialog_date_picker);
        mFinished = (TextView) view.findViewById(R.id.finish_dialog_date_picker);
        mFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDateLegal(mYear, mMonth, mDay)) {
                    EventBus.getDefault().post(new DatePickerEvent(getDate(mYear, mMonth, mDay)));
                    SharedPrefUtil.getInstance(mContext).putInt(Constants.BIRTH_YEAR_SP_KEY, mYear);
                    SharedPrefUtil.getInstance(mContext).putInt(Constants.BIRTH_MONTH_SP_KEY, mMonth);
                    SharedPrefUtil.getInstance(mContext).putInt(Constants.BIRTH_DAY_SP_KEY, mDay);
                } else {
                    ToastUtil.toastInCenter(mContext, R.string.toast_error_date_picker_dialog);
                }
                dismiss();
            }
        });
        mDialogView = new DialogView(mContext, view);
        mDialogView.setCancelable(true);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setDimBehind(true);
        mDialogView.setGravity(Gravity.CENTER);
    }

    private void initDate() {
        mYear = SharedPrefUtil.getInstance(mContext).getInt(Constants.BIRTH_YEAR_SP_KEY);
        mMonth = SharedPrefUtil.getInstance(mContext).getInt(Constants.BIRTH_MONTH_SP_KEY);
        mDay = SharedPrefUtil.getInstance(mContext).getInt(Constants.BIRTH_DAY_SP_KEY);
        //第一次选择，显示当前日期
        if (mYear == -1) {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            calendar.add(Calendar.MONTH, 1);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);
        }
        mDate.setText(getDate(mYear, mMonth, mDay));
        mDatePicker.init(mYear, mMonth - 1, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear + 1;
                mDay = dayOfMonth;
                mDate.setText(getDate(mYear, mMonth, mDay));
            }
        });
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

    private String getDate(int year, int month, int day) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(year).append("年").append(month).append("月").append(day).append("日");
        return buffer.toString();
    }

    private boolean isDateLegal(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.MONTH, 1);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (year < nowYear) {
            return true;
        } else if (year == nowYear) {
            if (month < nowMonth) {
                return true;
            } else if (month == nowMonth) {
                if (day < nowDay) {
                    return true;
                }
            }
        }
        return false;
    }
}
