package com.xi.liuliu.topnews.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.NewsPhotoPagerAdapter;
import com.xi.liuliu.topnews.impl.ZoomInTransform;
import com.xi.liuliu.topnews.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxb171 on 2017/7/31.
 */

public class PhotoBrowserDialog implements View.OnClickListener {
    private static final String TAG = "PhotoBrowserDialog";
    private ViewPager mViewPager;
    private ImageView mCloseBtn;
    private ImageView mLoading;
    private TextView mCurrentPhoto;
    private TextView mSaveBtn;
    private String mCurImageUrl;
    private String[] mImageUrls;
    private List<PhotoView> mPhotoViewList;
    private int mCurrentPosition = -1;
    private DialogView mDialogView;
    private Context mContext;

    public PhotoBrowserDialog(Context context, String currentUrl, String[] imageUrls) {
        mContext = context;
        mCurImageUrl = currentUrl;
        mImageUrls = imageUrls;
        initData();
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_photo_browser, null);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager_news_photo_browser);
        mViewPager.setAdapter(new NewsPhotoPagerAdapter(mPhotoViewList));
        mViewPager.setCurrentItem(mCurrentPosition);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPhoto.setText(position + 1 + "/" + mImageUrls.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setPageTransformer(true, new ZoomInTransform());
        mCloseBtn = (ImageView) view.findViewById(R.id.news_photo_close_btn);
        mCloseBtn.setOnClickListener(this);
        mLoading = (ImageView) view.findViewById(R.id.loading_news_photo);
        mCurrentPhoto = (TextView) view.findViewById(R.id.current_positon_photo);
        mCurrentPhoto.setText(mCurrentPosition + 1 + "/" + mImageUrls.length);
        mSaveBtn = (TextView) view.findViewById(R.id.save_phonto_btn);
        mSaveBtn.setOnClickListener(this);
        mDialogView = new DialogView(mContext, view);
        mDialogView.setWindowFullScreen();
    }


    private void initData() {
        mPhotoViewList = new ArrayList<>(mImageUrls.length);
        RequestOptions options = new RequestOptions();
        options.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).fitCenter();
        for (int i = 0; i < mImageUrls.length; i++) {
            //确定当前图片的position
            if (mCurImageUrl.equals(mImageUrls[i])) {
                mCurrentPosition = i;
            }
            final PhotoView photoView = new PhotoView(mContext);
            photoView.enable();
            Glide.with(mContext).load(mImageUrls[i]).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    photoView.setImageDrawable(resource);
                }

                @Override
                public void onLoadFailed(Drawable errorDrawable) {

                }
            });

            mPhotoViewList.add(photoView);
            Log.i(TAG, "imageUrl-->" + mImageUrls[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_photo_close_btn:
                dismiss();
                break;
            case R.id.save_phonto_btn:
                savePhoto();
        }

    }

    private void savePhoto() {
        PhotoView currentPhotoView = mPhotoViewList.get(mViewPager.getCurrentItem());
        BitmapDrawable bitmapDrawable = (BitmapDrawable) currentPhotoView.getDrawable();
        FileUtils.savePhoto(mContext, bitmapDrawable.getBitmap(), new FileUtils.SaveResultCallback() {
            @Override
            public void onSavedSuccess() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //主线程更新UI
                        Toast.makeText(mContext, R.string.news_detail_save_photo_toast_success, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSavedFailed() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //主线程更新UI
                        Toast.makeText(mContext, R.string.news_detail_save_photo_toast_failed, Toast.LENGTH_SHORT).show();
                    }
                });
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
}
