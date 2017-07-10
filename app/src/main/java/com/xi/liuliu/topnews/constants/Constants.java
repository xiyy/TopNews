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
    //卫视
    String DONG_FANG_URL = "http://live.hcs.cmvideo.cn:8088/wd_r4/dfl/dongfangwshd/1200/01.m3u8?msisdn=30e333c98378aa36874e6a4ce6b14f39&mdspid=&spid=699001&netType=4&sid=5500058351&pid=2028595851&timestamp=20170710111311&Channel_ID=0116_24000003-99000-200300120100004&ProgramID=619811679&ParentNodeID=-99&preview=1&playseek=000000-000600&client_ip=114.215.153.24&assertID=5500058351&imei=d54237205e18af0858ba1c94f5480f09fe37fa67686d87a5ced6e58d2f2f72ce&chargePhone=&SecurityKey=20170710111311&mtv_session=daa93ec6fee7507a814056e30d87dd28&HlsSubType=1&HlsProfileId=1&encrypt=caaad0010a34aa368b9b133cb7e31a6b&time=1499657013110";
    String HU_NAN_URL = "http://live.hcs.cmvideo.cn:8088/wd-hunanhd-1200/01.m3u8?msisdn=3081643684a3c6084eef42bb9eb7a754&mdspid=&spid=699017&netType=4&sid=2201064496&pid=2028595851&timestamp=20170710121254&Channel_ID=0116_24000003-99000-200300120100004&ProgramID=609153594&ParentNodeID=-99&preview=1&playseek=000000-000600&client_ip=114.215.153.24&assertID=2201064496&imei=d54237205e18af0858ba1c94f5480f09fe37fa67686d87a5ced6e58d2f2f72ce&chargePhone=&SecurityKey=20170710121254&mtv_session=38a81ea4c17ab3be73c2444fc9f00d78&HlsSubType=1&HlsProfileId=1&encrypt=97c5207079b0024c1bc944d3c1d2c0c5&time=1499660169193";
    //央视
    String CCTV5 = "http://live.hcs.cmvideo.cn:8088/wd_r2/cctv/cctv5hd/1200/index.m3u8?msisdn=30a3565fb49c1bb914f1ef8d3dba9681&mdspid=&spid=699004&netType=4&sid=2201062977&pid=2028595851&timestamp=20170710124042&Channel_ID=0116_24000003-99000-200300120100004&ProgramID=608788135&ParentNodeID=-99&preview=1&playseek=000000-000600&client_ip=114.215.153.24&assertID=2201062977&imei=d54237205e18af0858ba1c94f5480f09fe37fa67686d87a5ced6e58d2f2f72ce&chargePhone=&SecurityKey=20170710124042&encrypt=947bd6451f6d37bccff4e5362aa1fdae";
}

