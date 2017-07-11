package com.xi.liuliu.topnews.constants;

import com.xi.liuliu.topnews.R;

/**
 * Created by liuliu on 2017/6/13.
 */

public interface Constants {
    String[] CHANNELS = new String[]{"头条", "社会", "国内", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    String[] CHANNELS_PARAM = new String[]{"toutiao", "shehui", "guonei", "yule", "tiyu", "junshi", "keji", "caijing", "shishang"};
    String HOST = "http://v.juhe.cn/toutiao/index";
    String JU_HE_APP_KEY = "6a0f8f312dfdc046f4132d0a6761ecc8";
    String[] FAVOURITE_HISTORY = new String[]{"我的收藏", "阅读历史"};
    String LOGIN_SP_KEY = "isUserLoggedIn";
    String USER_PHONE_NUMBER_SP_KEY = "phoneNumber";
    String LOGIN_SMS_CODE_HOST = "https://api.leancloud.cn/1.1/requestSmsCode";
    String VERIFY_SMS_CODE = "https://api.leancloud.cn/1.1/verifySmsCode";
    String LEAN_CLOUND_APP_KEY = "LSe5Tdf9RP0kHbBsp0FYWrwL";
    String LEAN_CLOUND_APP_ID = "J9jGoaYEbyYl1iB4Nu3W0swr-gzGzoHsz";
    String WEI_XIN_APP_ID = "wx757db96f61a9ba7b";
    String WEI_XIN_APP_SECRET = "d8873042d045bbc07a93c4cef31b97ef";
    String[] LIVE_CHANNEL = {"央视", "卫视", "地方", "体育", "影视"};
    int[] LIVE_CHANNEL_ICON_ID = {R.drawable.live_cctv_logo, R.drawable.live_wei_shi_logo, R.drawable.live_di_fang_logo, R.drawable.live_sports_logo, R.drawable.live_moive_logo};
    //央视
    String CCTV1 = "http://58.200.131.2/hls/cctv1hd.m3u8";
    String CCTV3 = "http://58.200.131.2/hls/cctv3hd.m3u8";
    String CCTV5 = "http://58.200.131.2/hls/cctv5hd.m3u8";
    String CCTV5P = "http://58.200.131.2/hls/cctv5phd.m3u8";
    String CCTV6 = "http://58.200.131.2/hls/cctv6hd.m3u8";
    String CHC = "http://58.200.131.2/hls/chchd.m3u8";
    String CCTV8 = "http://58.200.131.2/hls/cctv8hd.m3u8";
    //卫视
    String AN_HUI = "http://58.200.131.2/hls/ahhd.m3u8";
    String BEI_JING = "http://58.200.131.2/hls/btv1hd.m3u8";
    String DONG_FANG = "http://58.200.131.2/hls/dfhd.m3u8";
    String GUANG_DONG = "http://58.200.131.2/hls/gdhd.m3u8";
    String HEI_LONG_JIANG = "http://58.200.131.2/hls/hljhd.m3u8";
    String HU_BEI = "http://58.200.131.2/hls/hbhd.m3u8";
    String HU_NAN = "http://58.200.131.2/hls/hunanhd.m3u8";
    String JIANG_SU = "http://58.200.131.2/hls/jshd.m3u8";
    String LIAO_NING = "http://58.200.131.2/hls/lnhd.m3u8";
    String SHAN_DONG = "http://58.200.131.2/hls/sdhd.m3u8";
    String SHEN_ZHEN = "http://58.200.131.2/hls/szhd.m3u8";
    String TIAN_JIN = "http://58.200.131.2/hls/tjhd.m3u8";
    String ZHE_JIANG = "http://58.200.131.2/hls/zjhd.m3u8";
    String CHONE_QING = "http://58.200.131.2/hls/cqhd.m3u8";
    String XIANG_GANG = "http://fms.cntv.lxdns.com/live/flv/channel84.flv";
    String AO_MEN = "http://live.mastvnet.com/iVx460D/live.m3u8";
    //地方
    String BEI_JING_JI_SHI = "http://58.200.131.2/hls/btv11hd.m3u8";
    String BEI_JING_TI_YU = "http://58.200.131.2/hls/btv6hd.m3u8";
    String BEI_JING_WEN_YI = "http://58.200.131.2/hls/btv2hd.m3u8";
    String FENG_HUANG_ZHONG_WEN = "http://223.82.250.95:8080/ysten-businessmobile/live/fhchinese/dujuejiami.m3u8";
    String FENG_HUANG_ZI_XUN = "http://223.110.245.139:80/PLTV/3/224/3221226980/index.m3u8";
    String HU_NAN_GUO_JI = "http://phoneliveal.mgtv.com/nn_live/nn_x64/aWQ9SE5HSk1QUDM2MCZjZG5leF9pZD1hbF9waG9uZV9saXZlMyZuZnQ9dHMz/HNGJMPP360.m3u8";
    String HU_NAN_DIAN_SHI_JU = "http://phoneliveal.mgtv.com/nn_live/nn_x64/aWQ9SE5EU0pNUFAzNjAmY2RuZXhfaWQ9YWxfcGhvbmVfbGl2ZTMmbmZ0PXRz/HNDSJMPP360.m3u8";
    //体育
    String CCTV5_SPORT = CCTV5;
    String CCTV5_P_SPORT = CCTV5P;
    String BEI_JING_TI_YU_SPORT = BEI_JING_TI_YU;
    //影视
    String CCTV6_MOIVE = CCTV6;
    String CCTV8_MOIVE = CCTV8;
    String CHC_MOIVE = CHC;
    String HU_NAN_DIAN_SHI_JU_MOIVE = HU_NAN_DIAN_SHI_JU;


}