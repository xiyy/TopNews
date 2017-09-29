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
    String LOGIN_TYPE_SP_KEY = "loginType";
    String WEI_BO_NICK_NAME_SP_KEY = "weiboNickName";
    String LOCATION_LATITUDE_SP_KEY = "latitude";
    String LOCATION_lONGITUDE_SP_KEY = "longitude";
    String LOCATION_ADDRESS_SP_KEY = "address";
    String GENDER_SP_KEY = "gender";
    String CITY_SP_KEY = "city";
    String BIRTH_SP_KEY = "birth";
    String BIRTH_YEAR_SP_KEY = "birth_year";
    String BIRTH_MONTH_SP_KEY = "birth_month";
    String BIRTH_DAY_SP_KEY = "birth_day";
    String USER_NAME_SP_KEY = "userName";
    String USER_PORTRAIT_PATH_SP_KEY = "userPortraitPath";
    String INTRODUCE_SP_KEY = "introduce";
    String WEI_BO_Portrait_URL = "weiboPortraitUrl";
    String LOGIN_SMS_CODE_HOST = "https://api.leancloud.cn/1.1/requestSmsCode";
    String VERIFY_SMS_CODE = "https://api.leancloud.cn/1.1/verifySmsCode";
    String LEAN_CLOUND_APP_KEY = "LSe5Tdf9RP0kHbBsp0FYWrwL";
    String LEAN_CLOUND_APP_ID = "J9jGoaYEbyYl1iB4Nu3W0swr-gzGzoHsz";
    String WEI_XIN_APP_ID = "wx757db96f61a9ba7b";
    String WEI_XIN_APP_SECRET = "d8873042d045bbc07a93c4cef31b97ef";
    String QQ_APP_ID = "1106167045";
    String WEI_BO_APP_KEY = "3432080154";
    String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
    String WEI_BO_INFO_URL = "https://api.weibo.com/2/users/show.json";
    String[] LIVE_CHANNEL = {"央视", "卫视", "地方", "体育", "影视", "海外"};
    int[] LIVE_CHANNEL_ICON_ID = {R.drawable.selector_live_cctv, R.drawable.selector_live_weishi, R.drawable.selector_live_local, R.drawable.selector_live_sports, R.drawable.selector_live_movie, R.drawable.selector_live_overseas};
    //央视
    String CCTV1 = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
    String CCTV2 = "http://ivi.bupt.edu.cn/hls/cctv2.m3u8";
    String CCTV3 = "http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8";
    String CCTV4 = "http://ivi.bupt.edu.cn/hls/cctv4.m3u8";
    String CCTV5 = "http://ivi.bupt.edu.cn/hls/cctv5.m3u8";
    String CCTV5P = "http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8";
    String CCTV6 = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
    String CCTV7 = "http://ivi.bupt.edu.cn/hls/cctv7.m3u8";
    String CCTV8 = "http://ivi.bupt.edu.cn/hls/cctv8hd.m3u8";
    String CCTV9 = "http://ivi.bupt.edu.cn/hls/cctv9.m3u8";
    String CCTV10 = "http://ivi.bupt.edu.cn/hls/cctv10.m3u8";
    String CCTV11 = "http://ivi.bupt.edu.cn/hls/cctv11.m3u8";
    String CCTV12 = "http://ivi.bupt.edu.cn/hls/cctv12.m3u8";
    String CCTV13 = "http://ivi.bupt.edu.cn/hls/cctv13.m3u8";
    String CCTV14 = "http://ivi.bupt.edu.cn/hls/cctv14.m3u8";
    String CCTV15 = "http://ivi.bupt.edu.cn/hls/cctv15.m3u8";
    String CHC = "http://ivi.bupt.edu.cn/hls/chchd.m3u8";
    //卫视
    String AN_HUI = "http://ivi.bupt.edu.cn/hls/ahhd.m3u8";
    String BEI_JING = "http://ivi.bupt.edu.cn/hls/btv1hd.m3u8";
    String DONG_FANG = "http://ivi.bupt.edu.cn/hls/dfhd.m3u8";
    String GUANG_DONG = "http://ivi.bupt.edu.cn/hls/gdhd.m3u8";
    String HEI_LONG_JIANG = "http://ivi.bupt.edu.cn/hls/hljhd.m3u8";
    String HU_BEI = "http://ivi.bupt.edu.cn/hls/hbhd.m3u8";
    String HU_NAN = "http://ivi.bupt.edu.cn/hls/hunanhd.m3u8";
    String JIANG_SU = "http://ivi.bupt.edu.cn/hls/jshd.m3u8";
    String LIAO_NING = "http://ivi.bupt.edu.cn/hls/lnhd.m3u8";
    String SHAN_DONG = "http://ivi.bupt.edu.cn/hls/sdhd.m3u8";
    String SHEN_ZHEN = "http://ivi.bupt.edu.cn/hls/szhd.m3u8";
    String TIAN_JIN = "http://ivi.bupt.edu.cn/hls/tjhd.m3u8";
    String ZHE_JIANG = "http://ivi.bupt.edu.cn/hls/zjhd.m3u8";
    String CHONE_QING = "http://ivi.bupt.edu.cn/hls/cqhd.m3u8";
    String XIANG_GANG = "http://fms.cntv.lxdns.com/live/flv/channel84.flv";
    String AO_MEN = "http://live.mastvnet.com/iVx460D/live.m3u8";

    //地方
    String BEI_JING_JI_SHI = "http://ivi.bupt.edu.cn/hls/btv11hd.m3u8";
    String BEI_JING_TI_YU = "http://ivi.bupt.edu.cn/hls/btv6hd.m3u8";
    String BEI_JING_WEN_YI = "http://ivi.bupt.edu.cn/hls/btv2hd.m3u8";
    String FENG_HUANG_ZI_XUN = "http://223.110.245.139:80/PLTV/3/224/3221226980/index.m3u8";
    String HU_NAN_GUO_JI = "http://phoneliveal.mgtv.com/nn_live/nn_x64/aWQ9SE5HSk1QUDM2MCZjZG5leF9pZD1hbF9waG9uZV9saXZlMyZuZnQ9dHMz/HNGJMPP360.m3u8";
    String HU_NAN_DIAN_SHI_JU = "http://phoneliveal.mgtv.com/nn_live/nn_x64/aWQ9SE5EU0pNUFAzNjAmY2RuZXhfaWQ9YWxfcGhvbmVfbGl2ZTMmbmZ0PXRz/HNDSJMPP360.m3u8";
    //境外
    String USA_ABC_NEWS = "http://abclive.abcnews.com/i/abc_live4@136330/index_1200_av-b.m3u8";
    String BBC_NEWS = "http://edge01p.friday.tw/live/fet001/chunklist_w1500640123099.m3u8";
    String XIN_TANG_REN_TV = "http://174.127.67.246/live400/playlist.m3u8";
    String DIVA_ZHONG_WEN = "http://61.58.60.247:9000/live/178.m3u8";
    String NHK_WORLD_TV = "http://nhkwtvglobal-i.akamaihd.net/hls/live/263941/nhkwtvglobal/index_1180.m3u8";
    String MTV_CHINA = "http://27.254.63.162/stream/totnew.php?channel_id=deb56e700dbc4d9ea5af08e9d300a675";
    String MTV_HD = "http://27.254.63.162/stream/totnew.php?channel_id=8d45473ff4ca44d79e1abc4516c2fee0";
    String TIAN_YING_JING_DIAN = "http://27.254.63.162/stream/totnew.php?channel_id=1c2276a2f1884b269ce281c3334e8f85";
    String JING_SONG_TV = "http://27.254.63.162/stream/totnew.php?channel_id=c316ed9333e54442b25077f0bb3cc269";
    String FEI_CUI_TV = "http://pull3.a8.com/live/jade/playlist.m3u8";
    String California_Music_Channel = "http://cmctv.ios.internapcdn.net/cmctv_vitalstream_com/live_1/CMCUSA/CCURstream0.m3u8";
    String FOX_23_NEWS = "http://cmghlslive-i.akamaihd.net:80/hls/live/224709/KOKI/904k/prog.m3u8";
    String FOX_25_NEWS = "http://cmghlslive-i.akamaihd.net:80/hls/live/224671/WFXT/904k/prog.m3u8";
    String FOX_30_NEWS = "http://cmghlslive-i.akamaihd.net:80/hls/live/224710/WFOX/904k/prog.m3u8";
    String Pac_12_Mountain = "http://xrxs.net/video/live-p12moun-2328.m3u8";
    String Pac_12_Los_Angeles = "http://xrxs.net/video/live-p12losa-2328.m3u8";
    //体育
    String CCTV5_SPORT = CCTV5;
    String CCTV5_P_SPORT = CCTV5P;
    String BEI_JING_TI_YU_SPORT = BEI_JING_TI_YU;
    //影视
    String CCTV6_MOIVE = CCTV6;
    String CCTV8_MOIVE = CCTV8;
    String CHC_MOIVE = CHC;
    String HU_NAN_DIAN_SHI_JU_MOIVE = HU_NAN_DIAN_SHI_JU;
    String TIAN_YING_JING_DIAN_MOIVE = TIAN_YING_JING_DIAN;
    String JING_SONG_TV_MOIVE = JING_SONG_TV;
    //定位相关
    String GET_NEARBY_ADDRESSES_URL = "http://api.map.baidu.com/geocoder/v2/?ak=6eea93095ae93db2c77be9ac910ff311";

}