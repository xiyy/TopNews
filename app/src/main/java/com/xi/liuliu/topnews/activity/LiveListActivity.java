package com.xi.liuliu.topnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.LiveListAdapter;
import com.xi.liuliu.topnews.constants.Constants;

public class LiveListActivity extends AppCompatActivity {
    private Button mGoBack;
    private ListView mLiveList;
    private TextView mChannelTitle;
    private String[] mLiveUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_list);
        mGoBack = (Button) findViewById(R.id.go_back_activity_live_list);
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mChannelTitle = (TextView) findViewById(R.id.text_view_activity_live_list);
        mLiveList = (ListView) findViewById(R.id.list_view_activity_live_list);
        mLiveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), LiveActivity.class);
                intent.putExtra("live_url", mLiveUrls[position]);
                startActivity(intent);
            }
        });
        int liveChannelId = getIntent().getExtras().getInt("live_channel_title_id");
        initData(liveChannelId);
    }

    private void initData(int channel) {
        String[] channels = null;
        int channelTitleId = -1;
        switch (channel) {
            case 0:
                channelTitleId = R.string.live_list_cctv_channel;
                channels = new String[]{"CCTV1", "CCTV2", "CCTV3", "CCTV4", "CCTV5-高清", "CCTV5-超清", "CCTV6", "CCTV7", "CCTV8", "CCTV9", "CCTV10", "CCTV11",
                        "CCTV12", "CCTV13", "CCTV14", "CCTV15", "CHC-高清"};
                mLiveUrls = new String[]{Constants.CCTV1, Constants.CCTV2, Constants.CCTV3, Constants.CCTV4, Constants.CCTV5, Constants.CCTV5P, Constants.CCTV6,
                        Constants.CCTV7, Constants.CCTV8, Constants.CCTV9, Constants.CCTV10, Constants.CCTV11, Constants.CCTV12, Constants.CCTV13, Constants.CCTV14,
                        Constants.CCTV15, Constants.CHC};
                break;
            case 1:
                channelTitleId = R.string.live_list_wei_shi_channel;
                channels = new String[]{"安徽卫视", "北京卫视", "东方卫视", "广东卫视", "黑龙江卫视", "湖北卫视", "湖南卫视",
                        "江苏卫视", "辽宁卫视", "山东卫视", "深圳卫视", "天津卫视", "浙江卫视", "重庆卫视", "香港卫视", "澳门卫视"};
                mLiveUrls = new String[]{Constants.AN_HUI, Constants.BEI_JING, Constants.DONG_FANG, Constants.GUANG_DONG, Constants.HEI_LONG_JIANG,
                        Constants.HU_BEI, Constants.HU_NAN, Constants.JIANG_SU, Constants.LIAO_NING, Constants.SHAN_DONG, Constants.SHEN_ZHEN,
                        Constants.TIAN_JIN, Constants.ZHE_JIANG, Constants.CHONE_QING, Constants.XIANG_GANG, Constants.AO_MEN};

                break;
            case 2:
                channelTitleId = R.string.live_list_di_fang_channel;
                channels = new String[]{"北京纪实", "北京体育", "北京文艺", "凤凰资讯", "湖南国际", "湖南电视剧"};
                mLiveUrls = new String[]{Constants.BEI_JING_JI_SHI, Constants.BEI_JING_TI_YU, Constants.BEI_JING_WEN_YI,
                        Constants.FENG_HUANG_ZI_XUN, Constants.HU_NAN_GUO_JI, Constants.HU_NAN_DIAN_SHI_JU,};
                break;
            case 3:
                channelTitleId = R.string.live_list_ti_yu_channel;
                channels = new String[]{"CCTV5-高清", "CCTV5-超清", "北京体育"};
                mLiveUrls = new String[]{Constants.CCTV5_SPORT, Constants.CCTV5_P_SPORT, Constants.BEI_JING_TI_YU_SPORT};
                break;
            case 4:
                channelTitleId = R.string.live_list_ying_shi_channel;
                channels = new String[]{"CCTV6", "CCTV8", "CHC-高清", "湖南电视剧"};
                mLiveUrls = new String[]{Constants.CCTV6_MOIVE, Constants.CCTV8_MOIVE, Constants.CHC_MOIVE, Constants.HU_NAN_DIAN_SHI_JU_MOIVE};
                break;
        }
        mChannelTitle.setText(channelTitleId);
        mLiveList.setAdapter(new LiveListAdapter(channels, this));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.zoomout);
    }
}
