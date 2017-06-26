package com.xi.liuliu.topnews.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.feedback.Comment;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avos.avoscloud.feedback.FeedbackThread;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.dialog.FeedbackGetPicDialog;
import com.xi.liuliu.topnews.dialog.SendingDialog;
import com.xi.liuliu.topnews.event.FeedbackPicDeleteEvent;
import com.xi.liuliu.topnews.utils.BitmapUtil;

import java.io.File;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by liuliu on 2017/6/26.
 */

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mFeedbackContent;
    private EditText mFeedbackContact;
    private Button mGoBack;
    private TextView mSend;
    private ImageView mFeedbackPic;
    private String mImagePath;
    private SendingDialog mSendingDialog;
    private FeedbackGetPicDialog mFeedbackGetPicDialog;
    private boolean hasPicSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mFeedbackContent = (EditText) findViewById(R.id.activity_feedback_content);
        mFeedbackContact = (EditText) findViewById(R.id.activity_feedback_contact);
        mGoBack = (Button) findViewById(R.id.activity_feedback_go_back);
        mGoBack.setOnClickListener(this);
        mSend = (TextView) findViewById(R.id.activity_feedback_send);
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
                finish();
                break;
            case R.id.activity_feedback_send:
                sendFeedback();
                break;
            case R.id.activity_feedback_pic:
                if (!hasPicSelected) {
                    mFeedbackGetPicDialog = new FeedbackGetPicDialog(this, this, R.layout.dialog_feedback_get_pic);
                } else {
                    mFeedbackGetPicDialog = new FeedbackGetPicDialog(this, this, R.layout.dialog_feedback_get_pic_delete);
                }
                //isSoftInputShow有问题，总是返回TRUE
                if (isSoftInputShow()) {
                    //隐藏软键盘
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //软键盘消失后，再show，软件盘消失需要时间
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mFeedbackGetPicDialog.show();
                        }
                    }, 150);
                } else {
                    mFeedbackGetPicDialog.show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK && data != null) {
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
            if (mFeedbackGetPicDialog != null) {
                mFeedbackGetPicDialog.dissmiss();
            }
        }
    }

    private void sendFeedback() {
        if (TextUtils.isEmpty(mFeedbackContent.getText().toString())) {
            Toast.makeText(this, R.string.feedback_toast_say_something, Toast.LENGTH_SHORT).show();
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
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.feedback_toast_sending_success, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
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
}
