package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.CityAdapter;
import com.xi.liuliu.topnews.impl.OnItemClickListener;
import com.xi.liuliu.topnews.utils.RegionUtil;

import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {
    private RelativeLayout mExitRl;
    private TextView mCityName;
    private RecyclerView mRecyclerView;
    private CityAdapter mCityAdapter;
    private int mRegionPosition;
    private ArrayList<Integer> mCityList;
    private int mRegionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initData();
        initView();
    }

    private void initData() {
        mRegionPosition = getIntent().getIntExtra("region_position", 0);
        mRegionName = RegionUtil.getRegionName(mRegionPosition);
        mCityList = new ArrayList<>();
        mCityAdapter = new CityAdapter(this, RegionUtil.getCityList(mRegionPosition, mCityList));
        mCityAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("city_name", mCityList.get(position));
                setResult(1009, intent);
                finish();
            }
        });
    }

    private void initView() {
        mExitRl = (RelativeLayout) findViewById(R.id.go_back_city_activity);
        mExitRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mCityName = (TextView) findViewById(R.id.city_name_city_activity);
        mCityName.setText(mRegionName);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_city_activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mCityAdapter);
    }


}
