package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.AddressListAdapter;
import com.xi.liuliu.topnews.bean.Address;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

import java.util.ArrayList;

public class AddressListActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mCancle;
    private RecyclerView mRecyclerView;
    private ArrayList<Address> mAddressList;
    private LinearLayoutManager mLinearLayoutManager;
    private AddressListAdapter mAddressListAdapter;
    private SharedPrefUtil mSharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        mAddressList = getIntent().getParcelableArrayListExtra("addressList");
        mSharedPrefUtil = SharedPrefUtil.getInstance(this);
        mCancle = (TextView) findViewById(R.id.go_back_btn_address_list);
        mCancle.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_address_list);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        //如果每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mAddressListAdapter = new AddressListAdapter(mAddressList);
        mRecyclerView.setAdapter(mAddressListAdapter);
        mAddressListAdapter.setOnItemClickListener(new AddressListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                String addressName;
                if (position == 0) {
                    addressName = "";
                } else {
                    addressName = mAddressList.get(position - 1).getName();
                }
                intent.putExtra("address_name", addressName);
                mSharedPrefUtil.putString(Constants.LOCATION_ADDRESS_SP_KEY, addressName);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back_btn_address_list:
                //AddressListActivity是由startActivityForResult启动的，所以AddressListActivity关闭前，必须setResult，否则crash
                String lastAddress = mSharedPrefUtil.getString(Constants.LOCATION_ADDRESS_SP_KEY);
                Intent intent = new Intent();
                if (lastAddress != null) {
                    if (lastAddress.equals("")) {
                        intent.putExtra("address_name", "");
                    } else {
                        intent.putExtra("address_name", lastAddress);
                    }
                }
                setResult(0, intent);
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //AddressListActivity是由startActivityForResult启动的，所以AddressListActivity关闭前，必须setResult，否则crash
            String lastAddress = mSharedPrefUtil.getString(Constants.LOCATION_ADDRESS_SP_KEY);
            Intent intent = new Intent();
            if (lastAddress != null) {
                if (lastAddress.equals("")) {
                    intent.putExtra("address_name", "");
                } else {
                    intent.putExtra("address_name", lastAddress);
                }
            }
            setResult(0, intent);
        }
        return super.onKeyDown(keyCode, event);
    }
}
