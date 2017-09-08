package com.xi.liuliu.topnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.ImgPickerAdapter;
import com.xi.liuliu.topnews.bean.Address;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.BrokeNewsGetPicDialog;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;
import com.xi.liuliu.topnews.view.ImgPickerGridView;

import java.util.ArrayList;

public class BrokeNewsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "BrokeNewsActivity";
    private TextView mCancle;
    private TextView mPublish;
    private EditText mTitle;
    private EditText mContent;
    private LinearLayout mImgLayer2;
    private LinearLayout mImgLayer3;
    private EditText mContact;
    private LinearLayout mLocationLl;
    private TextView mLocationTv;
    private ImageView mLocationImg;
    private ImgPickerGridView mGridView;
    private ArrayList<Address> mAddressList;
    private boolean isLocated;
    private BrokeNewsGetPicDialog mBrokeNewsGetPicDialog;
    private ArrayList<Bitmap> mBitmapList;
    private int mImgCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broke_news);
        initData();
        initView();
    }

    private void initView() {
        mCancle = (TextView) findViewById(R.id.cancle_broke_news_activity);
        mCancle.setOnClickListener(this);
        mPublish = (TextView) findViewById(R.id.broke_news_publish);
        mPublish.setOnClickListener(this);
        mTitle = (EditText) findViewById(R.id.edit_title_broke_news);
        mContent = (EditText) findViewById(R.id.edit_content_brokes_news);
        mContact = (EditText) findViewById(R.id.edit_contact_broke_news);
        mLocationLl = (LinearLayout) findViewById(R.id.ll_location_broke_news);
        mLocationLl.setOnClickListener(this);
        mLocationTv = (TextView) findViewById(R.id.location_broke_news);
        mLocationImg = (ImageView) findViewById(R.id.location_img_broke_news);
        mGridView = (ImgPickerGridView) findViewById(R.id.grid_view_broke_news_activity);
        mGridView.setAdapter(new ImgPickerAdapter(this, mBitmapList));
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //不满9张时，最后一张永远是“添加”图片
                if (position == mBitmapList.size() - 1) {
                    if (mImgCount != 9) {
                        mBrokeNewsGetPicDialog.show();
                    }
                }
            }
        });
    }

    private void initData() {
        mAddressList = getIntent().getParcelableArrayListExtra("addressList");
        mBrokeNewsGetPicDialog = new BrokeNewsGetPicDialog(this, this);
        mBitmapList = new ArrayList<>();
        Bitmap addImg = BitmapFactory.decodeResource(getResources(), R.drawable.layer_list_broke_news_add_img);
        mBitmapList.add(addImg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancle_broke_news_activity:
                finish();
                break;
            case R.id.broke_news_publish:
                publish();
                break;
            case R.id.ll_location_broke_news:
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putParcelableArrayListExtra("addressList", mAddressList);
                startActivityForResult(intent, 1003);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            handleAlbumResult(requestCode, resultCode, data);
        } else if (requestCode == 1002) {
            handleCameraResult(requestCode, resultCode, data);

        } else if (requestCode == 1003) {
            handleAddressResult(requestCode, resultCode, data);
        }
    }

    private void handleAddressResult(int requestCode, int resultCode, Intent data) {
        String addressName = data.getStringExtra("address_name");
        if (addressName != null) {
            if (addressName.equals("")) {
                mLocationTv.setText(R.string.broke_news_location_address);
                isLocated = false;
                mLocationImg.setImageResource(R.drawable.selector_broke_news_location);
            } else {
                mLocationTv.setText(addressName);
                isLocated = true;
                mLocationImg.setImageResource(R.drawable.broke_news_activity_location_success);
            }
            SharedPrefUtil.getInstance(this).putString(Constants.LOCATION_ADDRESS_SP_KEY, addressName);
        }
    }

    private void handleAlbumResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK && data != null) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumns = {MediaStore.Images.Media.DATA};
//            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePathColumns[0]);
//            mImagesPath[mImageCount] = c.getString(columnIndex);
//            Bitmap bm = BitmapUtil.BytesToBitmap(BitmapUtil.decodeBitmap(mImagesPath[mImageCount]));
//            mBitmapList.add(mImageCount, bm);
//            mImageViews[mImageCount].setImageBitmap(bm);
//            mImageViews[mImageCount].setScaleType(ImageView.ScaleType.FIT_XY);
//            c.close();
//            hasImagesSelected[mImageCount] = true;
//            if (mBrokeNewsGetPicDialog != null) {
//                mBrokeNewsGetPicDialog.dismiss();
//            }
//            mImageCount++;
        }
    }

    private void handleCameraResult(int requestCode, int resultCode, Intent data) {

    }

    private void publish() {

    }


}
