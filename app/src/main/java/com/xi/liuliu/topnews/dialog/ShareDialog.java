package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.bean.NewsItem;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.utils.BitmapUtil;

/**
 * Created by liuliu on 2017/6/22.
 */

public class ShareDialog implements View.OnClickListener {
    private static final String TAG = "ShareDialog";
    private Context mContext;
    private DialogView mDialogView;
    private TextView mShareCircle;
    private TextView mShareWeixin;
    private TextView mShareQQ;
    private TextView mShareQZone;
    private TextView mShareWeibo;
    private TextView mShareCancle;
    private Bitmap mShareThum;
    private NewsItem mNewsItem;

    public ShareDialog(Context context, NewsItem newsItem, Bitmap shareThum) {
        this.mContext = context;
        this.mNewsItem = newsItem;
        this.mShareThum = shareThum;
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
        mDialogView = new DialogView(mContext, view,R.style.share_dialog_animation);
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
                shareToWX(SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.share_weixin_btn:
                shareToWX(SendMessageToWX.Req.WXSceneSession);
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

    private void shareToWX(int scene) {
        IWXAPI api = WXAPIFactory.createWXAPI(mContext, Constants.WEI_XIN_APP_ID, true);
        api.registerApp(Constants.WEI_XIN_APP_ID);
        if (api.isWXAppInstalled()) {
            if (mNewsItem != null && mShareThum != null) {
                WXWebpageObject webpageObject = new WXWebpageObject();
                //网页URL
                webpageObject.webpageUrl = mNewsItem.getUrl();
                WXMediaMessage mediaMessage = new WXMediaMessage(webpageObject);
                //网页标题
                mediaMessage.title = mNewsItem.getTitle();
                //缩略图
                Bitmap thumbBmp = Bitmap.createScaledBitmap(mShareThum, 50, 30, true);
                mediaMessage.thumbData = BitmapUtil.bmpToByteArray(thumbBmp, true);
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = buildTransaction("webpage");
                req.message = mediaMessage;
                req.scene = scene;
                boolean result = api.sendReq(req);
                Log.i(TAG, "share to circle:" + result);
            } else {
                throw new RuntimeException("ShareDialog 133 line,newsItem or shareThum is null");
            }
        } else {
            Toast toast = Toast.makeText(mContext.getApplicationContext(), R.string.share_dialog_weixin_not_installed, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        dismiss();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
