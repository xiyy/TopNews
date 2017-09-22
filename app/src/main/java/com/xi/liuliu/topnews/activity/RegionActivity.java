package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.RegionAdapter;

import java.util.ArrayList;

public class RegionActivity extends AppCompatActivity {
    private RelativeLayout mExitRl;
    private RecyclerView mRecyclerView;
    private ArrayList<Integer> mRegionResIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        initData();
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
        mRecyclerView.setAdapter(new RegionAdapter(mRegionResIdList));
    }

    private void initData() {
        mRegionResIdList = new ArrayList();
        mRegionResIdList.add(R.string.edit_user_info_region_beijing);
        mRegionResIdList.add(R.string.edit_user_info_region_tianjin);
        mRegionResIdList.add(R.string.edit_user_info_region_hebei);
        mRegionResIdList.add(R.string.edit_user_info_region_shanxi);
        mRegionResIdList.add(R.string.edit_user_info_region_neimenggu);
        mRegionResIdList.add(R.string.edit_user_info_region_liaoning);
        mRegionResIdList.add(R.string.edit_user_info_region_jilin);
        mRegionResIdList.add(R.string.edit_user_info_region_heilongjiang);
        mRegionResIdList.add(R.string.edit_user_info_region_shanghai);
        mRegionResIdList.add(R.string.edit_user_info_region_jiangsu);
        mRegionResIdList.add(R.string.edit_user_info_region_zhejiang);
        mRegionResIdList.add(R.string.edit_user_info_region_anhui);
        mRegionResIdList.add(R.string.edit_user_info_region_fujian);
        mRegionResIdList.add(R.string.edit_user_info_region_jiangxi);
        mRegionResIdList.add(R.string.edit_user_info_region_shandong);
        mRegionResIdList.add(R.string.edit_user_info_region_henan);
        mRegionResIdList.add(R.string.edit_user_info_region_hubei);
        mRegionResIdList.add(R.string.edit_user_info_region_hunan);
        mRegionResIdList.add(R.string.edit_user_info_region_guangdong);
        mRegionResIdList.add(R.string.edit_user_info_region_guangxi);
        mRegionResIdList.add(R.string.edit_user_info_region_hainan);
        mRegionResIdList.add(R.string.edit_user_info_region_chongqing);
        mRegionResIdList.add(R.string.edit_user_info_region_sichuan);
        mRegionResIdList.add(R.string.edit_user_info_region_guizhou);
        mRegionResIdList.add(R.string.edit_user_info_region_yunnan);
        mRegionResIdList.add(R.string.edit_user_info_region_xizang);
        mRegionResIdList.add(R.string.edit_user_info_region_shanxi_3);
        mRegionResIdList.add(R.string.edit_user_info_region_gansu);
        mRegionResIdList.add(R.string.edit_user_info_region_qinghai);
        mRegionResIdList.add(R.string.edit_user_info_region_ningxia);
        mRegionResIdList.add(R.string.edit_user_info_region_xinjiang);
        mRegionResIdList.add(R.string.edit_user_info_region_taiwan);
        mRegionResIdList.add(R.string.edit_user_info_region_xianggang);
        mRegionResIdList.add(R.string.edit_user_info_region_aomen);
        mRegionResIdList.add(R.string.edit_user_info_region_over_seas);

    }
}
