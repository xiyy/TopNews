package com.xi.liuliu.topnews.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.event.FeedbackPicDeleteEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/26.
 */

public class FeedbackGetPicDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private TextView mSelectFromAblum;
    private TextView mCancle;
    private Activity mActivity;
    private TextView mSelectFromAblumFromDelete;
    private TextView mCancleFromDelete;
    private TextView mDelete;
    private int mLayoutId;

    public FeedbackGetPicDialog(Context context, Activity activity, int layoutId) {
        mContext = context;
        mActivity = activity;
        mLayoutId = layoutId;
        init();
    }

    private void init() {
        View view = null;
        if (mLayoutId == R.layout.dialog_feedback_get_pic) {
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_feedback_get_pic, null);
            mSelectFromAblum = (TextView) view.findViewById(R.id.feedback_get_pic_select);
            mSelectFromAblum.setOnClickListener(this);
            mCancle = (TextView) view.findViewById(R.id.feedback_get_pic_cancle);
            mCancle.setOnClickListener(this);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_feedback_get_pic_delete, null);
            mSelectFromAblumFromDelete = (TextView) view.findViewById(R.id.feedback_get_pic_select_from_delete);
            mSelectFromAblumFromDelete.setOnClickListener(this);
            mCancleFromDelete = (TextView) view.findViewById(R.id.feedback_get_pic_cancle_from_delete);
            mCancleFromDelete.setOnClickListener(this);
            mDelete = (TextView) view.findViewById(R.id.feedback_get_pic_select_delete);
            mDelete.setOnClickListener(this);
        }
        mDialogView = new DialogView(mContext, view,R.style.share_dialog_animation);
        mDialogView.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mDialogView.setGravity(Gravity.BOTTOM);
        mDialogView.setFullWidth(true);
        mDialogView.setCanceledOnTouchOutside(true);
        mDialogView.setDimBehind(true);

    }

    public void show() {
        if (mDialogView != null) {
            mDialogView.showDialog();
        }
    }

    public void dissmiss() {
        if (mDialogView != null) {
            mDialogView.dismissDialog();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_get_pic_select:
                selectFromAlbum();
                break;

            case R.id.feedback_get_pic_cancle:
                dissmiss();
                break;
            case R.id.feedback_get_pic_select_from_delete:
                selectFromAlbum();
                break;
            case R.id.feedback_get_pic_cancle_from_delete:
                dissmiss();
                break;
            case R.id.feedback_get_pic_select_delete:
                EventBus.getDefault().post(new FeedbackPicDeleteEvent());
                dissmiss();
        }

    }

    private void selectFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(intent, 1001);
    }
}
