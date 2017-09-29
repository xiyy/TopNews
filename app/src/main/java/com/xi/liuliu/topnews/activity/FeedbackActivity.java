package com.xi.liuliu.topnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.feedback.Comment;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avos.avoscloud.feedback.FeedbackThread;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.dialog.ExitTipDialog;
import com.xi.liuliu.topnews.dialog.GetPicDialog;
import com.xi.liuliu.topnews.dialog.SendingDialog;
import com.xi.liuliu.topnews.event.FeedbackPicDeleteEvent;
import com.xi.liuliu.topnews.utils.BitmapUtil;
import com.xi.liuliu.topnews.utils.FileUtils;
import com.xi.liuliu.topnews.utils.ToastUtil;

import java.io.File;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/26.
 */

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mFeedbackContent;
    private EditText mFeedbackContact;
    private RelativeLayout mGoBack;
    private RelativeLayout mSend;
    private ImageView mFeedbackPic;
    private String mImagePath;
    private SendingDialog mSendingDialog;
    private GetPicDialog mFeedbackGetPicDialog;
    private ExitTipDialog mExitTipDialog;
    private boolean hasPicSelected;
    private File mCameraFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mFeedbackContent = (EditText) findViewById(R.id.activity_feedback_content);
        mFeedbackContact = (EditText) findViewById(R.id.activity_feedback_contact);
        mGoBack = (RelativeLayout) findViewById(R.id.activity_feedback_go_back);
        mGoBack.setOnClickListener(this);
        mSend = (RelativeLayout) findViewById(R.id.activity_feedback_send);
        mSend.setOnClickListener(this);
        mFeedbackPic = (ImageView) findViewById(R.id.activity_feedback_pic);
        mFeedbackPic.setOnClickListener(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_feedback_go_back:
                if (hasEditContent()) {
                    if (mExitTipDialog == null) {
                        mExitTipDialog = new ExitTipDialog(this);
                    }
                    mExitTipDialog.show();
                } else {
                    finish();
                }
                break;
            case R.id.activity_feedback_send:
                sendFeedback();
                break;
            case R.id.activity_feedback_pic:
                if (!hasPicSelected) {
                    mFeedbackGetPicDialog = new GetPicDialog(this, this, R.layout.dialog_get_pic, GetPicDialog.FROM_FEED_BACK);
                } else {
                    mFeedbackGetPicDialog = new GetPicDialog(this, this, R.layout.dialog_feedback_get_pic_delete, GetPicDialog.FROM_FEED_BACK);
                }
                mCameraFile = FileUtils.createImageFile();
                mFeedbackGetPicDialog.setCameraFile(mCameraFile);
                mFeedbackGetPicDialog.show();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK && data != null) {
            handleAlbumResult(requestCode, resultCode, data);
        }
        if (requestCode == 1002 && resultCode == Activity.RESULT_OK) {
            //data==null
            handleCameraResult();
        }
    }

    private void sendFeedback() {
        if (TextUtils.isEmpty(mFeedbackContent.getText().toString())) {
            ToastUtil.toastInCenter(this, R.string.feedback_toast_say_something);
            return;
        }
        if (mSendingDialog == null) {
            mSendingDialog = new SendingDialog(this);
        }
        mSendingDialog.show();
        FeedbackAgent agent = new FeedbackAgent(this);
        FeedbackThread thread = agent.getDefaultThread();
        thread.setContact(mFeedbackContact.getText().toString().trim());
        Comment feedbackContent = new Comment(mFeedbackContent.getText().toString().trim());
        thread.add(feedbackContent);
        if (!TextUtils.isEmpty(mImagePath)) {
            File feedbackPic = new File(mImagePath);
            try {
                thread.add(new Comment(feedbackPic));
            } catch (AVException e) {
                e.printStackTrace();
            }
        }
        thread.sync(new FeedbackThread.SyncCallback() {
            @Override
            public void onCommentsSend(List<Comment> list, AVException e) {
                if (mSendingDialog != null) {
                    mSendingDialog.dissmiss();
                    ToastUtil.toastInCenter(FeedbackActivity.this, R.string.feedback_toast_sending_success);
                    finish();
                }
            }

            @Override
            public void onCommentsFetch(List<Comment> list, AVException e) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onEventMainThread(FeedbackPicDeleteEvent event) {
        if (event != null) {
            mFeedbackPic.setImageDrawable(getResources().getDrawable(R.drawable.feedback_camera_icon));
            hasPicSelected = false;
        }
    }

    /**
     * 软键盘是否弹出
     *
     * @return
     */
    private boolean isSoftInputShow() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.zoomout);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (hasEditContent()) {
                if (mExitTipDialog == null) {
                    mExitTipDialog = new ExitTipDialog(this);
                }
                mExitTipDialog.show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean hasEditContent() {
        String content = mFeedbackContent.getText().toString().trim();
        String contact = mFeedbackContact.getText().toString().trim();
        if (!TextUtils.isEmpty(content) || !TextUtils.isEmpty(contact) || hasPicSelected) {
            return true;
        }
        return false;
    }

    private void handleAlbumResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        mImagePath = c.getString(columnIndex);
        //压缩原始图片，预防oom
        Bitmap bm = BitmapUtil.BytesToBitmap(BitmapUtil.decodeBitmap(mImagePath));
        mFeedbackPic.setImageBitmap(bm);
        mFeedbackPic.setScaleType(ImageView.ScaleType.FIT_XY);
        c.close();
        hasPicSelected = true;

    }

    private void handleCameraResult() {
        Uri fileUri = FileUtils.getImageContentUri(this, mCameraFile);
        mImagePath = FileUtils.getImagePathWithUri(this, fileUri, null);
        //拍摄的图片要压缩，图片过大，导致Bitmap对象装不下图片，将图片的长和宽缩小为原来的1/2
        Bitmap bitmap = BitmapFactory.decodeFile(mImagePath, BitmapUtil.getBitmapOptions(2));
        mFeedbackPic.setImageBitmap(bitmap);
        mFeedbackPic.setScaleType(ImageView.ScaleType.FIT_XY);
        hasPicSelected = true;
    }
}
