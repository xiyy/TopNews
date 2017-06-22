package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/22.
 */

public class ShareDialog implements View.OnClickListener {
    private Context mContext;
    private DialogView mDialogView;
    private TextView mShareCircle;
    private TextView mShareWeixin;
    private TextView mShareQQ;
    private TextView mShareQZone;
    private TextView mShareWeibo;
    private TextView mShareCancle;

    public ShareDialog(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_share, null);
        mShareCircle = (TextView) view.findViewById(R.id.share_circle_btn);
        mShareWeixin = (TextView) view.findViewById(R.id.share_weixin_btn);
        mShareQQ = (TextView) view.findViewById(R.id.share_qq_btn);
        mShareQZone = (TextView) view.findViewById(R.id.share_qzone_btn);
        mShareWeibo = (TextView) view.findViewById(R.id.share_weibo_btn);
        mShareCancle = (TextView) view.findViewById(R.id.share_cancle);
        mShareCircle.setOnClickListener(this);
        mShareWeixin.setOnClickListener(this);
        mShareQQ.setOnClickListener(this);
        mShareQQ.setOnClickListener(this);
        mShareQZone.setOnClickListener(this);
        mShareWeibo.setOnClickListener(this);
        mShareCancle.setOnClickListener(this);
        mDialogView = new DialogView(mContext, view);
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
            case R.id.share_circle_btn:


                break;
            case R.id.share_weixin_btn:

                break;

            case R.id.share_qq_btn:

                break;

            case R.id.share_qzone_btn:


                break;

            case R.id.share_weibo_btn:


                break;

            case R.id.share_cancle:
                dismiss();
                break;
            default:
        }
    }
}
