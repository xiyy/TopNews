package com.xi.liuliu.topnews.utils;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zhangxb171 on 2017/9/1.
 */

public class AnimUtil {
    public static final String ROTATE = "rotation";
    public static final String ROTATE_X = "rotationX";
    public static final String ROTATE_Y = "rotationY";
    public static final String ALPHA = "alpha";

    public static void rotate(ImageView imageView, String orientation, float from, float to, int duration) {
        if (imageView != null) {
            ObjectAnimator.ofFloat(imageView, orientation, from, to).setDuration(duration).start();
        }
    }

    public static void alpha(ImageView imageView, float from, float to, int duration) {
        if (imageView != null) {
            ObjectAnimator.ofFloat(imageView, ALPHA, from, to).setDuration(duration).start();
        }
    }

    /**
     * 对于gone/invisible的ImageView，逐渐出现
     *
     * @param imageView
     * @param from
     * @param to
     * @param duration
     */
    public static void alphaAndVisible(ImageView imageView, float from, float to, int duration) {
        if (imageView != null) {
            imageView.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(imageView, ALPHA, from, to).setDuration(duration).start();
        }
    }

    /**
     * imageView逐渐消失，动画结束时，将imageView gone掉
     *
     * @param imageView
     * @param from
     * @param to
     * @param duration
     */
    public static void alphaAndGone(final ImageView imageView, float from, float to, int duration) {
        if (imageView != null) {
            ObjectAnimator.ofFloat(imageView, ALPHA, from, to).setDuration(duration).start();
            imageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setVisibility(View.GONE);
                }
            }, duration);
        }
    }

    /**
     * imageView逐渐消失，动画结束时，将imageView inVisible
     *
     * @param imageView
     * @param from
     * @param to
     * @param duration
     */
    public static void alphaAndInVisible(final ImageView imageView, float from, float to, int duration) {
        if (imageView != null) {
            ObjectAnimator.ofFloat(imageView, ALPHA, from, to).setDuration(duration).start();
            imageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setVisibility(View.INVISIBLE);
                }
            }, duration);
        }
    }
}
