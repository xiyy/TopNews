package com.xi.liuliu.topnews.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/26.
 */

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mFeedbackContent;
    private Button mGoBack;
    private TextView mSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        mFeedbackContent = (EditText) findViewById(R.id.activity_feedback_content);
        Drawable drawable = getResources().getDrawable(R.drawable.feedback_camera_icon);
        drawable.setBounds(0, 0, 50, 50);
        mFeedbackContent.setCompoundDrawables(null, null, drawable, null);
        mGoBack = (Button) findViewById(R.id.activity_feedback_go_back);
        mGoBack.setOnClickListener(this);
        mSend = (TextView) findViewById(R.id.activity_feedback_send);
        mSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_feedback_go_back:

                break;
            case R.id.activity_feedback_send:

                break;
        }
    }
}
