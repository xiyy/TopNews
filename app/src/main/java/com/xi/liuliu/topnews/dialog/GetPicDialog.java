package com.xi.liuliu.topnews.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.activity.ImageSelectorActivity;
import com.xi.liuliu.topnews.event.FeedbackPicDeleteEvent;

import java.io.File;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/26.
 */

public class GetPicDialog implements View.OnClickListener {
    public static final int FROM_FEED_BACK = 0;
    public static int FROM_BROKE_NEWS = 1;
    private Context mContext;
    private DialogView mDialogView;
    private TextView mSelectFromAblum;
    private TextView mSelectFromCamera;
    private TextView mCancle;
    private Activity mActivity;
    private TextView mSelectFromAblumFromDelete;
    private TextView mSelectFromCameraFromDelete;
    private TextView mCancleFromDelete;
    private TextView mDelete;
    private int mLayoutId;
    private File mCameraFile;
    private int mFrom;

    public GetPicDialog(Context context, Activity activity, int layoutId, int from) {
        mContext = context;
        mActivity = activity;
        mLayoutId = layoutId;
        mFrom = from;
        init();
    }

    private void init() {
        View view;
        if (mLayoutId == R.layout.dialog_get_pic) {
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_get_pic, null);
            mSelectFromAblum = (TextView) view.findViewById(R.id.feedback_get_pic_select_from_album);
            mSelectFromAblum.setOnClickListener(this);
            mSelectFromCamera = (TextView) view.findViewById(R.id.feedback_get_pic_select_from_camera);
            mSelectFromCamera.setOnClickListener(this);
            mCancle = (TextView) view.findViewById(R.id.feedback_get_pic_cancle);
            mCancle.setOnClickListener(this);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.dialog_feedback_get_pic_delete, null);
            mSelectFromAblumFromDelete = (TextView) view.findViewById(R.id.feedback_get_pic_select_from_album_delete);
            mSelectFromAblumFromDelete.setOnClickListener(this);
            mSelectFromCameraFromDelete = (TextView) view.findViewById(R.id.feedback_get_pic_select_from_camera_delete);
            mSelectFromCameraFromDelete.setOnClickListener(this);
            mCancleFromDelete = (TextView) view.findViewById(R.id.feedback_get_pic_cancle_from_delete);
            mCancleFromDelete.setOnClickListener(this);
            mDelete = (TextView) view.findViewById(R.id.feedback_get_pic_select_delete);
            mDelete.setOnClickListener(this);
        }
        mDialogView = new DialogView(mContext, view, R.style.share_dialog_animation);
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

    public void dismiss() {
        if (mDialogView != null) {
            mDialogView.dismissDialog();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedback_get_pic_select_from_album:
            case R.id.feedback_get_pic_select_from_album_delete:
                if (mFrom == GetPicDialog.FROM_BROKE_NEWS) {
                    selectFromAlbumForBrokeNews();
                } else {
                    selectFromAlbumForFeedback();
                }
                break;
            case R.id.feedback_get_pic_select_from_camera:
            case R.id.feedback_get_pic_select_from_camera_delete:
                selectFromCamera();
                break;
            case R.id.feedback_get_pic_cancle:
            case R.id.feedback_get_pic_cancle_from_delete:
                dismiss();
                break;
            case R.id.feedback_get_pic_select_delete:
                EventBus.getDefault().post(new FeedbackPicDeleteEvent());
                dismiss();
        }

    }

    private void selectFromAlbumForFeedback() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(intent, 1001);
        dismiss();
    }

    private void selectFromAlbumForBrokeNews() {
        Intent intent = new Intent(mActivity, ImageSelectorActivity.class);
        mActivity.startActivityForResult(intent, 1001);
        dismiss();
    }

    private void selectFromCamera() {
        Uri outputFileUri = Uri.fromFile(mCameraFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        mActivity.startActivityForResult(intent, 1002);
        dismiss();
    }


    public void setCameraFile(File file) {
        mCameraFile = file;
    }
}
