package com.xi.liuliu.topnews.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.LiveListAdapter;

public class LiveListActivity extends AppCompatActivity {
    private Button mGoBack;
    private ListView mLiveList;
    private TextView mChannelTitle;

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
        int liveChannelId = getIntent().getExtras().getInt("live_channel_title_id");
        initData(liveChannelId);
    }

    private void initData(int channel) {
        String[] channels = null;
        int channelTitleId = -1;
        switch (channel) {
            case 0:
                channelTitleId = R.string.live_list_cctv_channel;
                channels = new String[]{"CCTV1", "CCTV3", "CCTV5-高清", "CCTV5-超清", "CCTV6", "CHC-高清", "CCTV8"};
                break;
            case 1:
                channelTitleId = R.string.live_list_wei_shi_channel;
                channels = new String[]{"安徽卫视", "北京卫视", "东方卫视", "广东卫视", "黑龙江卫视", "湖北卫视", "湖南卫视",
                        "江苏卫视", "辽宁卫视", "山东卫视", "深圳卫视", "天津卫视", "浙江卫视", "重庆卫视", "香港卫视", "澳门卫视"};

                break;
            case 2:
                channelTitleId = R.string.live_list_di_fang_channel;
                channels = new String[]{"北京纪实", "北京体育", "北京文艺", "凤凰中文", "凤凰资讯", "湖南国际", "湖南电视剧"};
                break;
            case 3:
                channelTitleId = R.string.live_list_ti_yu_channel;
                channels = new String[]{"CCTV5-高清", "CCTV5-超清", "北京体育"};
                break;
            case 4:
                channelTitleId = R.string.live_list_ying_shi_channel;
                channels = new String[]{"CCTV6", "CCTV8", "CHC-高清", "湖南电视剧"};
                break;
        }
        mChannelTitle.setText(channelTitleId);
        mLiveList.setAdapter(new LiveListAdapter(channels, this));
    }

}
