package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

import com.xi.liuliu.topnews.R;

public class DialogView {
    protected Context mContext;
    protected View view;
    protected android.app.Dialog dialog;
    private OnDismissListener mOnDialogDismissListener;
    private int animationId = -1;

    protected DialogView(Context context) {
        this.mContext = context;
        initDialog();
    }

    protected DialogView(Context context, int layoutId) {
        this.mContext = context;
        this.view = loadLayout(layoutId);
        initDialog();
    }

    public DialogView(Context context, View view) {
        this.mContext = context;
        this.view = view;
        initDialog();
    }

    public DialogView(Context context, View view, int animationId) {
        this.mContext = context;
        this.view = view;
        this.animationId = animationId;
        initDialog();
    }

    private View loadLayout(int layoutId) {
        View v = LayoutInflater.from(mContext).inflate(layoutId, null);
        onLoadLayout(v);
        return v;
    }


    protected void onLoadLayout(View view) {
        // Should be override if init with #DialogView(Context context, int
        // layoutId)
    }

    protected void initDialog() {
        dialog = new android.app.Dialog(mContext, R.style.dialog_view_theme);
        // 设置dialog显示的位置
        dialog.getWindow().setGravity(Gravity.CENTER);
        // 设置dialog进出动画
        if (animationId != -1) {
            dialog.getWindow().setWindowAnimations(animationId);
        } else {
            dialog.getWindow().setWindowAnimations(R.style.dialog_view_animation);
        }
        // 设置dialog 点击返回键的时候不消失
        dialog.setCancelable(false);
        // 设置在点击dialog外面的时候不消失dialog
        dialog.setCanceledOnTouchOutside(false);
        // 设置dialog的内容 这里已经通过theme 把dialog的title去掉了
        if (view != null) {
            dialog.setContentView(view);
        }
        // 设置去掉昏暗背景
        dialog.getWindow().clearFlags(LayoutParams.FLAG_DIM_BEHIND);
        LayoutParams lp = new LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.flags &= ~LayoutParams.FLAG_FULLSCREEN;
        dialog.getWindow().setAttributes(lp);
    }

    //设置是否有灰色背景
    public void setDimBehind(boolean visable) {
        if (dialog == null) {
            return;
        }

        Window mWindow = dialog.getWindow();

        if (mWindow == null) {
            return;
        }

        if (visable) {
            mWindow.addFlags(LayoutParams.FLAG_DIM_BEHIND);
        } else {
            mWindow.clearFlags(LayoutParams.FLAG_DIM_BEHIND);
        }
    }

    /**
     * 留下一个设置显示位置的方法 用于扩展<br/>
     * 默认是GRAVITY.CENTER 在屏幕居中显示
     *
     * @param gravity 参考android.view.Gravity 的值
     */
    public void setGravity(int gravity) {
        dialog.getWindow().setGravity(gravity);
    }

    /**
     * 如果没有显示就显示
     */
    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            try {
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isShowing() {
        if (dialog != null && dialog.isShowing()) {
            return true;
        }
        return false;
    }

    /**
     * 如果正在显示就消失Dialog
     */
    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void setSoftInputMode(int mode) {
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setSoftInputMode(mode);
        }
    }

    public void setFullWidth(boolean isFull) {
        if (isFull && dialog != null) {
            LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = LayoutParams.WRAP_CONTENT;
        }
    }

    public void setFullScreen(boolean isFull) {
        if (isFull && dialog != null) {
            LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = LayoutParams.MATCH_PARENT;
        }
    }

    /**
     * 设置对话框的宽度
     *
     * @param width
     */
    public void setWidth(int width) {
        if (dialog != null) {
            LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = width;
        }
    }

    /**
     * 设置对话框的高度
     *
     * @param height
     */
    public void setHeight(int height) {
        if (dialog != null) {
            LayoutParams lp = dialog.getWindow().getAttributes();
            lp.height = height;
        }
    }

    public void setCancelable(boolean cancel) {
        if (dialog != null) {
            dialog.setCancelable(cancel);
        }
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(cancel);
        }
    }

    /**
     * @return the mOnDialogDismissListener
     */
    public OnDismissListener getOnDialogDismissListener() {
        return mOnDialogDismissListener;
    }

    /**
     * @param onDialogDismissListener the mOnDialogDismissListener to set
     */
    public void setOnDialogDismissListener(OnDismissListener onDialogDismissListener) {
        this.mOnDialogDismissListener = onDialogDismissListener;
        if (dialog != null) {
            dialog.setOnDismissListener(mOnDialogDismissListener);
        }
    }

    public void setOnShowListener(DialogInterface.OnShowListener listener) {
        if (dialog != null) {
            dialog.setOnShowListener(listener);
        }
    }

    public void setContentView(View view) {
        this.view = view;
        if (dialog != null) {
            dialog.setContentView(view);
        }
    }

    public View getView() {
        return view;
    }


    public void setView(View view) {
        this.view = view;
    }
}
