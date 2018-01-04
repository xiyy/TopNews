package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.RegionAdapter;
import com.xi.liuliu.topnews.impl.OnItemClickListener;
import com.xi.liuliu.topnews.utils.RegionUtil;

import java.util.ArrayList;


public class RegionActivity extends AppCompatActivity {
    private RelativeLayout mExitRl;
    private RecyclerView mRecyclerView;
    private ArrayList<Integer> mRegionResIdList;
    private RegionAdapter mRegionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        initData();
        initView();
    }

    private void initData() {
        mRegionResIdList = new ArrayList();
        RegionUtil.getRegionList(mRegionResIdList);
        mRegionAdapter = new RegionAdapter(mRegionResIdList, this);
        mRegionAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == mRegionResIdList.size() - 1 || position == mRegionResIdList.size() - 2 ||
                        position == mRegionResIdList.size() - 3 || position == mRegionResIdList.size() - 4) {//对于台湾、香港、澳门、海外
                    Intent intent = new Intent();
                    intent.putExtra("city_name", RegionUtil.getRegionName(position));
                    setResult(1007, intent);
                    finish();
                } else {//其他省份
                    Intent intent = new Intent();
                    intent.setClass(RegionActivity.this, CityActivity.class);
                    intent.putExtra("region_position", position);
                    startActivityForResult(intent, 1008);
                }
            }
        });
    }

    private void initView() {
        mExitRl = (RelativeLayout) findViewById(R.id.go_back_region_activity);
        mExitRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_region_activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRegionAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 1008 && resultCode == 1009 && data != null)) {
            setResult(1007, data);
            finish();
        }
    }
}
