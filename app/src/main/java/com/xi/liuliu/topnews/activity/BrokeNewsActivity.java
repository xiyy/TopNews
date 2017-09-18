package com.xi.liuliu.topnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.feedback.Comment;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avos.avoscloud.feedback.FeedbackThread;
import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.ImgPickerAdapter;
import com.xi.liuliu.topnews.bean.Address;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.dialog.BrokeNewsGetPicDialog;
import com.xi.liuliu.topnews.dialog.SendingDialog;
import com.xi.liuliu.topnews.utils.BitmapUtil;
import com.xi.liuliu.topnews.utils.CheckPhone;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;
import com.xi.liuliu.topnews.utils.ToastUtil;
import com.xi.liuliu.topnews.view.ImgPickerGridView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private ArrayList<String> mImgPathList;
    private int mImgCount = 1;
    private ImgPickerAdapter mImgPickerAdapter;
    private SendingDialog mSendingDialog;

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
        mGridView.setAdapter(mImgPickerAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //不满9张时，最后一张永远是“添加”图片
                if (position == mBitmapList.size() - 1) {//点击最后一张图片
                    if (mImgCount <= 9) {
                        mBrokeNewsGetPicDialog.show();
                    }

                }
            }
        });
    }

    private void initData() {
        mAddressList = getIntent().getParcelableArrayListExtra("addressList");
        mBitmapList = new ArrayList<>();
        mImgPathList = new ArrayList<>();
        Bitmap addImg = BitmapFactory.decodeResource(getResources(), R.drawable.layer_list_broke_news_add_img);
        mBitmapList.add(addImg);
        mImgPickerAdapter = new ImgPickerAdapter(this, mBitmapList);
        mBrokeNewsGetPicDialog = new BrokeNewsGetPicDialog(this, this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imgPath = c.getString(columnIndex);
            mImgPathList.add(imgPath);
            Bitmap bm = BitmapUtil.BytesToBitmap(BitmapUtil.decodeBitmap(imgPath));
            //mImgCount!=9时，最后一张显示“+”图片
            if (mImgCount != 9) {
                mBitmapList.add(mImgCount - 1, bm);
            } else {
                //mImgCount==9时，删除最后的“+”图片,再把第9张图片加入
                mBitmapList.remove(mImgCount - 1);
                mBitmapList.add(mImgCount - 1, bm);
            }
            mImgCount++;
            mImgPickerAdapter.notifyDataSetChanged();
            c.close();
            if (mBrokeNewsGetPicDialog != null) {
                mBrokeNewsGetPicDialog.dismiss();
            }
        }
    }

    private void handleCameraResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            Bundle bundle = (Bundle) data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = df.format(new Date()) + ".jpg";
            String filePath = Environment.getExternalStorageDirectory().getAbsoluteFile() + "/topNews" + fileName;
            FileOutputStream fos;
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    fos = new FileOutputStream(filePath);
                } else {
                    fos = new FileOutputStream(getFilesDir() + fileName);
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mImgPathList.add(filePath);
            //mImgCount!=9时，最后一张显示“+”图片
            if (mImgCount != 9) {
                mBitmapList.add(mImgCount - 1, bitmap);
            } else {
                //mImgCount==9时，删除最后的“+”图片,再把第9张图片加入
                mBitmapList.remove(mImgCount - 1);
                mBitmapList.add(mImgCount - 1, bitmap);
            }
            mImgCount++;
            mImgPickerAdapter.notifyDataSetChanged();
            if (mBrokeNewsGetPicDialog != null) {
                mBrokeNewsGetPicDialog.dismiss();
            }
        }

    }

    private void publish() {
        String title = mTitle.getText().toString();
        String content = mContent.getText().toString();
        String contact = mContact.getText().toString();
        String address = null;
        if (isLocated) {
            address = mLocationTv.getText().toString();
        }
        String brokeNewsContent = "title : " + title + "content : " + content + "address:" + address;
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
            ToastUtil.toastInCenter(this, R.string.broke_news_toast_say_something);
            return;
        }
        if (TextUtils.isEmpty(contact)) {
            ToastUtil.toastInCenter(this, R.string.broke_news_toast_phone_empty);
            return;
        }
        if (!CheckPhone.isPhoneNum(contact)) {
            ToastUtil.toastInCenter(this, R.string.broke_news_toast_phone_invalid);
            return;
        }
        if (mSendingDialog == null) {
            mSendingDialog = new SendingDialog(this);
        }
        mSendingDialog.show();
        FeedbackAgent agent = new FeedbackAgent(this);
        FeedbackThread thread = agent.getDefaultThread();
        thread.setContact(contact);
        Comment feedbackContent = new Comment(brokeNewsContent);
        thread.add(feedbackContent);
        if (mImgPathList != null && mImgPathList.size() > 0) {
            for (String imgPath : mImgPathList) {
                File file = new File(imgPath);
                try {
                    thread.add(new Comment(file));
                } catch (AVException e) {
                    e.printStackTrace();
                }
            }
        }
        thread.sync(new FeedbackThread.SyncCallback() {
            @Override
            public void onCommentsSend(List<Comment> list, AVException e) {
                if (mSendingDialog != null) {
                    mSendingDialog.dissmiss();
                    ToastUtil.toastInCenter(BrokeNewsActivity.this, R.string.broke_news_toast_send_success);
                }
            }

            @Override
            public void onCommentsFetch(List<Comment> list, AVException e) {

            }
        });
    }
}
