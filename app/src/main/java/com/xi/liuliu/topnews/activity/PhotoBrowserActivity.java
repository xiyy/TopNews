package com.xi.liuliu.topnews.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
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


public class PhotoBrowserActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "PhotoBrowserActivity";
    private ViewPager mViewPager;
    private ImageView mCloseBtn;
    private ImageView mLoading;
    private TextView mCurrentPhoto;
    private TextView mSaveBtn;
    private String curImageUrl;
    private String[] mImageUrls = new String[]{};
    private List<PhotoView> mPhotoViewList;
    private int mCurrentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_browser);
        initData();
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager_news_photo_browser);
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
        mCloseBtn = (ImageView) findViewById(R.id.news_photo_close_btn);
        mCloseBtn.setOnClickListener(this);
        mLoading = (ImageView) findViewById(R.id.loading_news_photo);
        mCurrentPhoto = (TextView) findViewById(R.id.current_positon_photo);
        mCurrentPhoto.setText(mCurrentPosition + 1 + "/" + mImageUrls.length);
        mSaveBtn = (TextView) findViewById(R.id.save_phonto_btn);
        mSaveBtn.setOnClickListener(this);
    }

    private void initData() {
        curImageUrl = getIntent().getStringExtra("curImageUrl");
        mImageUrls = getIntent().getStringArrayExtra("imagesUrl");
        mPhotoViewList = new ArrayList<>(mImageUrls.length);
        RequestOptions options = new RequestOptions();
        options.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).fitCenter();
        for (int i = 0; i < mImageUrls.length; i++) {
            //确定当前图片的position
            if (curImageUrl.equals(mImageUrls[i])) {
                mCurrentPosition = i;
            }
            final PhotoView photoView = new PhotoView(this);
            photoView.enable();
            Glide.with(this).load(mImageUrls[i]).apply(options).transition(DrawableTransitionOptions.withCrossFade()).into(new SimpleTarget<Drawable>() {
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
                finish();
                break;
            case R.id.save_phonto_btn:
                savePhoto();
        }

    }

    private void savePhoto() {
        PhotoView currentPhotoView = mPhotoViewList.get(mViewPager.getCurrentItem());
        BitmapDrawable bitmapDrawable = (BitmapDrawable) currentPhotoView.getDrawable();
        FileUtils.savePhoto(this, bitmapDrawable.getBitmap(), new FileUtils.SaveResultCallback() {
            @Override
            public void onSavedSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //主线程更新UI
                        Toast.makeText(PhotoBrowserActivity.this, R.string.news_detail_save_photo_toast_success, Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onSavedFailed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //主线程更新UI
                        Toast.makeText(PhotoBrowserActivity.this, R.string.news_detail_save_photo_toast_failed, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.zoomout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

