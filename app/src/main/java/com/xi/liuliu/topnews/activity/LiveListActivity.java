package com.xi.liuliu.topnews.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.adapter.LiveListAdapter;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.utils.NetWorkUtil;

public class LiveListActivity extends AppCompatActivity {
    private RelativeLayout mGoBack;
    private ListView mLiveList;
    private TextView mChannelTitle;
    private String[] mLiveUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_list);
        mGoBack = (RelativeLayout) findViewById(R.id.rl_go_back_activity_live_list);
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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if (!NetWorkUtil.isWiFi(LiveListActivity.this) && NetWorkUtil.isMobile(LiveListActivity.this)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LiveListActivity.this);
                    builder.setTitle(R.string.alert_dialog_mobile_network_title).setMessage(R.string.alert_dialog_mobile_network_message).setPositiveButton(R.string.alert_dialog_mobile_network_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent intent = new Intent(getApplicationContext(), LiveActivity.class);
                            intent.putExtra("live_url", mLiveUrls[position]);
                            startActivity(intent);

                        }
                    }).setNegativeButton(R.string.alert_dialog_mobile_network_cancle, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
                    return;
                }
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
                channels = new String[]{"CCTV6", "CCTV8", "CHC-高清", "湖南电视剧", "天映经典", "惊悚频道"};
                mLiveUrls = new String[]{Constants.CCTV6_MOIVE, Constants.CCTV8_MOIVE, Constants.CHC_MOIVE,
                        Constants.HU_NAN_DIAN_SHI_JU_MOIVE, Constants.TIAN_YING_JING_DIAN_MOIVE, Constants.JING_SONG_TV_MOIVE};
                break;
            case 5:
                channelTitleId = R.string.live_list_overseas_channel;
                channels = new String[]{"美国ABC新闻", "美国BBC新闻", "新唐人频道", "DIVA中文频道", "NHK World"
                        , "MTV中国", "MTV高清", "天映经典", "惊悚频道", "翡翠频道", "加州音乐频道", "美国Fox新闻频道", "美国Pac-12频道"};
                mLiveUrls = new String[]{Constants.USA_ABC_NEWS, Constants.BBC_NEWS, Constants.XIN_TANG_REN_TV, Constants.DIVA_ZHONG_WEN
                        , Constants.NHK_WORLD_TV, Constants.MTV_CHINA, Constants.MTV_HD, Constants.TIAN_YING_JING_DIAN, Constants.JING_SONG_TV,
                        Constants.FEI_CUI_TV, Constants.California_Music_Channel, Constants.FOX_25_NEWS, Constants.Pac_12_Los_Angeles};
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
