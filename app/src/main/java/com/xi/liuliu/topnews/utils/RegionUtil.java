package com.xi.liuliu.topnews.utils;

import com.xi.liuliu.topnews.R;

import java.util.ArrayList;

/**
 * Created by zhangxb171 on 2017/9/25.
 */

public class RegionUtil {
    public static final int CITY_CODE_BEIJING = 0;
    public static final int CITY_CODE_TIANJIN = 1;
    public static final int CITY_CODE_HEBEI = 2;
    public static final int CITY_CODE_SHANXI = 3;
    public static final int CITY_CODE_NEIMENGGU = 4;
    public static final int CITY_CODE_lIAONING = 5;
    public static final int CITY_CODE_JILIN = 6;
    public static final int CITY_CODE_HEILONGJIANG = 7;
    public static final int CITY_CODE_SHANGHAI = 8;
    public static final int CITY_CODE_JIANGSU = 9;
    public static final int CITY_CODE_ZHEJIANG = 10;
    public static final int CITY_CODE_ANHUI = 11;
    public static final int CITY_CODE_FUJIAN = 12;
    public static final int CITY_CODE_JIANGXI = 13;
    public static final int CITY_CODE_SHANDONG = 14;
    public static final int CITY_CODE_HENAN = 15;
    public static final int CITY_CODE_HUBEI = 16;
    public static final int CITY_CODE_HUNAN = 17;
    public static final int CITY_CODE_GUANGDONG = 18;
    public static final int CITY_CODE_GUANGXI = 19;
    public static final int CITY_CODE_HAINAN = 20;
    public static final int CITY_CODE_CHONGQING = 21;
    public static final int CITY_CODE_SICHUAN = 22;
    public static final int CITY_CODE_GUIZHOU = 23;
    public static final int CITY_CODE_YUNNAN = 24;
    public static final int CITY_CODE_XIZANG = 25;
    public static final int CITY_CODE_SHANXI_3 = 26;
    public static final int CITY_CODE_GANSU = 27;
    public static final int CITY_CODE_QINGHAI = 28;
    public static final int CITY_CODE_NINGXIA = 29;
    public static final int CITY_CODE_XINJIANG = 30;
    public static final int CITY_CODE_TAIWAN = 31;
    public static final int CITY_CODE_XIANGGANG = 32;
    public static final int CITY_CODE_AOMEN = 33;
    public static final int CITY_CODE_HAIWAI = 34;

    public static ArrayList<Integer> getRegionList(ArrayList<Integer> mRegionResIdList) {
        if (mRegionResIdList == null) {
            return null;
        }
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
        return mRegionResIdList;

    }

    public static ArrayList<Integer> getCityList(int RegionCode, ArrayList<Integer> list) {
        if (list == null) {
            return null;
        }
        switch (RegionCode) {
            case CITY_CODE_BEIJING:
                list.add(R.string.edit_user_info_region_beijing_dongcheng);
                list.add(R.string.edit_user_info_region_beijing_xicheng);
                list.add(R.string.edit_user_info_region_beijing_chaoyang);
                list.add(R.string.edit_user_info_region_beijing_fengtai);
                list.add(R.string.edit_user_info_region_beijing_shijingshan);
                list.add(R.string.edit_user_info_region_beijing_haidian);
                list.add(R.string.edit_user_info_region_beijing_mentougou);
                list.add(R.string.edit_user_info_region_beijing_fangshan);
                list.add(R.string.edit_user_info_region_beijing_tongzhou);
                list.add(R.string.edit_user_info_region_beijing_shunyi);
                list.add(R.string.edit_user_info_region_beijing_changping);
                list.add(R.string.edit_user_info_region_beijing_daxing);
                list.add(R.string.edit_user_info_region_beijing_huairou);
                list.add(R.string.edit_user_info_region_beijing_pinggu);
                list.add(R.string.edit_user_info_region_beijing_miyun);
                list.add(R.string.edit_user_info_region_beijing_yanqing);
                break;
            case CITY_CODE_TIANJIN:
                list.add(R.string.edit_user_info_region_tianjin_heping);
                list.add(R.string.edit_user_info_region_tianjin_hedong);
                list.add(R.string.edit_user_info_region_tianjin_hebei);
                list.add(R.string.edit_user_info_region_tianjin_nankai);
                list.add(R.string.edit_user_info_region_tianjin_hongqiao);
                list.add(R.string.edit_user_info_region_tianjin_dongli);
                list.add(R.string.edit_user_info_region_tianjin_xiqing);
                list.add(R.string.edit_user_info_region_tianjin_jinnan);
                list.add(R.string.edit_user_info_region_tianjin_beichen);
                list.add(R.string.edit_user_info_region_tianjin_wuqing);
                list.add(R.string.edit_user_info_region_tianjin_baodi);
                list.add(R.string.edit_user_info_region_tianjin_binhai);
                list.add(R.string.edit_user_info_region_tianjin_ninghe);
                list.add(R.string.edit_user_info_region_tianjin_jinghai);
                list.add(R.string.edit_user_info_region_tianjin_jizhou);
                break;
            case CITY_CODE_HEBEI:
                list.add(R.string.edit_user_info_region_hebei_shijiazhuang);
                list.add(R.string.edit_user_info_region_hebei_tangshan);
                list.add(R.string.edit_user_info_region_hebei_qinhuangdao);
                list.add(R.string.edit_user_info_region_hebei_handan);
                list.add(R.string.edit_user_info_region_hebei_baoding);
                list.add(R.string.edit_user_info_region_hebei_zhangjiakou);
                list.add(R.string.edit_user_info_region_hebei_chengde);
                list.add(R.string.edit_user_info_region_hebei_cangzhou);
                list.add(R.string.edit_user_info_region_hebei_langfang);
                list.add(R.string.edit_user_info_region_hebei_hengshui);
                list.add(R.string.edit_user_info_region_hebei_xingtai);
                break;

            case CITY_CODE_SHANXI:
                list.add(R.string.edit_user_info_region_shanxi_taiyuan);
                list.add(R.string.edit_user_info_region_shanxi_datong);
                list.add(R.string.edit_user_info_region_shanxi_yangquan);
                list.add(R.string.edit_user_info_region_shanxi_changzhi);
                list.add(R.string.edit_user_info_region_shanxi_jinzhou);
                list.add(R.string.edit_user_info_region_shanxi_shuozhou);
                list.add(R.string.edit_user_info_region_shanxi_jinzhong);
                list.add(R.string.edit_user_info_region_shanxi_yuncheng);
                list.add(R.string.edit_user_info_region_shanxi_xinzhou);
                list.add(R.string.edit_user_info_region_shanxi_linfen);
                list.add(R.string.edit_user_info_region_shanxi_lvliang);
                break;
            case CITY_CODE_NEIMENGGU:
                list.add(R.string.edit_user_info_region_huhehaote);
                list.add(R.string.edit_user_info_region_baotou);
                list.add(R.string.edit_user_info_region_wuhai);
                list.add(R.string.edit_user_info_region_chifeng);
                list.add(R.string.edit_user_info_region_tongliao);
                list.add(R.string.edit_user_info_region_eerduosi);
                list.add(R.string.edit_user_info_region_hulunbeier);
                list.add(R.string.edit_user_info_region_bayannaoer);
                list.add(R.string.edit_user_info_region_wulanchabu);
                list.add(R.string.edit_user_info_region_xinganmeng);
                list.add(R.string.edit_user_info_region_xilinguole);
                list.add(R.string.edit_user_info_region_alashan);
                break;
            case CITY_CODE_lIAONING:
                list.add(R.string.edit_user_info_region_shenyang);
                list.add(R.string.edit_user_info_region_dalian);
                list.add(R.string.edit_user_info_region_anshan);
                list.add(R.string.edit_user_info_region_fushun);
                list.add(R.string.edit_user_info_region_benxi);
                list.add(R.string.edit_user_info_region_dandong);
                list.add(R.string.edit_user_info_region_jinzhou);
                list.add(R.string.edit_user_info_region_yingkou);
                list.add(R.string.edit_user_info_region_fuxin);
                list.add(R.string.edit_user_info_region_liaoyang);
                list.add(R.string.edit_user_info_region_panjin);
                list.add(R.string.edit_user_info_region_tieling);
                list.add(R.string.edit_user_info_region_chaoyang);
                list.add(R.string.edit_user_info_region_huludao);
                break;

            case CITY_CODE_JILIN:
                list.add(R.string.edit_user_info_region_jilinshi);
                list.add(R.string.edit_user_info_region_siping);
                list.add(R.string.edit_user_info_region_liaoyuan);
                list.add(R.string.edit_user_info_region_tonghua);
                list.add(R.string.edit_user_info_region_baishan);
                list.add(R.string.edit_user_info_region_songyuan);
                list.add(R.string.edit_user_info_region_baicheng);
                list.add(R.string.edit_user_info_region_changchun);
                list.add(R.string.edit_user_info_region_yanbian);
                break;

            case CITY_CODE_HEILONGJIANG:
                list.add(R.string.edit_user_info_region_heilongjiang_qiqihaer);
                list.add(R.string.edit_user_info_region_heilongjiang_jixi);
                list.add(R.string.edit_user_info_region_heilongjiang_hegang);
                list.add(R.string.edit_user_info_region_heilongjiang_shuanyashan);
                list.add(R.string.edit_user_info_region_heilongjiang_daqing);
                list.add(R.string.edit_user_info_region_heilongjiang_yichun);
                list.add(R.string.edit_user_info_region_heilongjiang_jiamusi);
                list.add(R.string.edit_user_info_region_heilongjiang_qitaihe);
                list.add(R.string.edit_user_info_region_heilongjiang_mudanjiang);
                list.add(R.string.edit_user_info_region_heilongjiang_heihe);
                list.add(R.string.edit_user_info_region_heilongjiang_suihua);
                list.add(R.string.edit_user_info_region_heilongjiang_daxinganling);
                break;

            case CITY_CODE_SHANGHAI:
                list.add(R.string.edit_user_info_region_huangpu);
                list.add(R.string.edit_user_info_region_xuhui);
                list.add(R.string.edit_user_info_region_changning);
                list.add(R.string.edit_user_info_region_jingan);
                list.add(R.string.edit_user_info_region_putuo);
                list.add(R.string.edit_user_info_region_hongkou);
                list.add(R.string.edit_user_info_region_yangpu);
                list.add(R.string.edit_user_info_region_minhang);
                list.add(R.string.edit_user_info_region_baoshan);
                list.add(R.string.edit_user_info_region_jiading);
                list.add(R.string.edit_user_info_region_pudongxinqu);
                list.add(R.string.edit_user_info_region_jinshan);
                list.add(R.string.edit_user_info_region_songjiang);
                list.add(R.string.edit_user_info_region_qingpu);
                list.add(R.string.edit_user_info_region_fengxian);
                list.add(R.string.edit_user_info_region_chongming);
                break;
            case CITY_CODE_JIANGSU:
                list.add(R.string.edit_user_info_region_jiangsu_nanjing);
                list.add(R.string.edit_user_info_region_jiangsu_wuxi);
                list.add(R.string.edit_user_info_region_jiangsu_xuzhou);
                list.add(R.string.edit_user_info_region_jiangsu_changzhou);
                list.add(R.string.edit_user_info_region_jiangsu_nantong);
                list.add(R.string.edit_user_info_region_jiangsu_lianyungang);
                list.add(R.string.edit_user_info_region_jiangsu_huaian);
                list.add(R.string.edit_user_info_region_jiangsu_yancheng);
                list.add(R.string.edit_user_info_region_jiangsu_yangzhou);
                list.add(R.string.edit_user_info_region_jiangsu_zhenjiang);
                list.add(R.string.edit_user_info_region_jiangsu_taizhou);
                list.add(R.string.edit_user_info_region_jiangsu_suqian);
                list.add(R.string.edit_user_info_region_jiangsu_suzhou);
                break;

            case CITY_CODE_ZHEJIANG:
                list.add(R.string.edit_user_info_region_hangzhou);
                list.add(R.string.edit_user_info_region_ningbo);
                list.add(R.string.edit_user_info_region_wenzhou);
                list.add(R.string.edit_user_info_region_jiaxing);
                list.add(R.string.edit_user_info_region_shaoxing);
                list.add(R.string.edit_user_info_region_jinhua);
                list.add(R.string.edit_user_info_region_quzhou);
                list.add(R.string.edit_user_info_region_zhoushan);
                list.add(R.string.edit_user_info_region_taizhou);
                list.add(R.string.edit_user_info_region_lishui);
                list.add(R.string.edit_user_info_region_huzhou);
                break;
            case CITY_CODE_ANHUI:
                list.add(R.string.edit_user_info_region_hefei);
                list.add(R.string.edit_user_info_region_wuhu);
                list.add(R.string.edit_user_info_region_fengbu);
                list.add(R.string.edit_user_info_region_huainan);
                list.add(R.string.edit_user_info_region_maanshan);
                list.add(R.string.edit_user_info_region_huaibei);
                list.add(R.string.edit_user_info_region_tongling);
                list.add(R.string.edit_user_info_region_anqing);
                list.add(R.string.edit_user_info_region_huangshan);
                list.add(R.string.edit_user_info_region_chuzhou);
                list.add(R.string.edit_user_info_region_fuyang);
                list.add(R.string.edit_user_info_region_suzhou);
                list.add(R.string.edit_user_info_region_liuan);
                list.add(R.string.edit_user_info_region_bozhou);
                list.add(R.string.edit_user_info_region_chizhou);
                list.add(R.string.edit_user_info_region_xuancheng);
                break;

            case CITY_CODE_FUJIAN:
                list.add(R.string.edit_user_info_region_xiamen);
                list.add(R.string.edit_user_info_region_putian);
                list.add(R.string.edit_user_info_region_fuzhou);
                list.add(R.string.edit_user_info_region_sanming);
                list.add(R.string.edit_user_info_region_quanzhou);
                list.add(R.string.edit_user_info_region_zhangzhou);
                list.add(R.string.edit_user_info_region_nanping);
                list.add(R.string.edit_user_info_region_longyan);
                list.add(R.string.edit_user_info_region_ningde);
                break;
            case CITY_CODE_JIANGXI:
                list.add(R.string.edit_user_info_region_nanchang);
                list.add(R.string.edit_user_info_region_jingdezhen);
                list.add(R.string.edit_user_info_region_pingxiang);
                list.add(R.string.edit_user_info_region_jiujiang);
                list.add(R.string.edit_user_info_region_xinyu);
                list.add(R.string.edit_user_info_region_yingtan);
                list.add(R.string.edit_user_info_region_ganzhou);
                list.add(R.string.edit_user_info_region_jian);
                list.add(R.string.edit_user_info_region_yichun);
                list.add(R.string.edit_user_info_region_shangrao);
                break;
            case CITY_CODE_SHANDONG:
                list.add(R.string.edit_user_info_region_shandong_jinan);
                list.add(R.string.edit_user_info_region_shandong_qingdao);
                list.add(R.string.edit_user_info_region_shandong_zibo);
                list.add(R.string.edit_user_info_region_shandong_zaozhuang);
                list.add(R.string.edit_user_info_region_shandong_dongying);
                list.add(R.string.edit_user_info_region_shandong_yantai);
                list.add(R.string.edit_user_info_region_shandong_weifang);
                list.add(R.string.edit_user_info_region_shandong_jining);
                list.add(R.string.edit_user_info_region_shandong_taian);
                list.add(R.string.edit_user_info_region_shandong_weihai);
                list.add(R.string.edit_user_info_region_shandong_rizhao);
                list.add(R.string.edit_user_info_region_shandong_laiwu);
                list.add(R.string.edit_user_info_region_shandong_linyi);
                list.add(R.string.edit_user_info_region_shandong_dezhou);
                list.add(R.string.edit_user_info_region_shandong_liaocheng);
                list.add(R.string.edit_user_info_region_shandong_binzhou);
                list.add(R.string.edit_user_info_region_shandong_heze);
                break;
            case CITY_CODE_HENAN:
                list.add(R.string.edit_user_info_region_zhengzhou);
                list.add(R.string.edit_user_info_region_kaifeng);
                list.add(R.string.edit_user_info_region_luoyang);
                list.add(R.string.edit_user_info_region_pingdingshan);
                list.add(R.string.edit_user_info_region_anyang);
                list.add(R.string.edit_user_info_region_hebi);
                list.add(R.string.edit_user_info_region_xinxiang);
                list.add(R.string.edit_user_info_region_jiaozuo);
                list.add(R.string.edit_user_info_region_puyang);
                list.add(R.string.edit_user_info_region_xuchang);
                list.add(R.string.edit_user_info_region_luohe);
                list.add(R.string.edit_user_info_region_sanmenxia);
                list.add(R.string.edit_user_info_region_nanyang);
                list.add(R.string.edit_user_info_region_shangqiu);
                list.add(R.string.edit_user_info_region_xinyang);
                list.add(R.string.edit_user_info_region_zhoukou);
                list.add(R.string.edit_user_info_region_zhumadian);
                break;
            case CITY_CODE_HUBEI:
                list.add(R.string.edit_user_info_region_wuhan);
                list.add(R.string.edit_user_info_region_huangshi);
                list.add(R.string.edit_user_info_region_shiyan);
                list.add(R.string.edit_user_info_region_yichang);
                list.add(R.string.edit_user_info_region_xiangyang);
                list.add(R.string.edit_user_info_region_ezhoiu);
                list.add(R.string.edit_user_info_region_jingmen);
                list.add(R.string.edit_user_info_region_xiaogan);
                list.add(R.string.edit_user_info_region_jingzhou);
                list.add(R.string.edit_user_info_region_huanggang);
                list.add(R.string.edit_user_info_region_xianning);
                list.add(R.string.edit_user_info_region_suizhou);
                list.add(R.string.edit_user_info_region_enshi);
                break;
            case CITY_CODE_HUNAN:
                list.add(R.string.edit_user_info_region_changsha);
                list.add(R.string.edit_user_info_region_zhuzhou);
                list.add(R.string.edit_user_info_region_xiangtan);
                list.add(R.string.edit_user_info_region_hengyang);
                list.add(R.string.edit_user_info_region_shaoyang);
                list.add(R.string.edit_user_info_region_yueyang);
                list.add(R.string.edit_user_info_region_changde);
                list.add(R.string.edit_user_info_region_zhangjiajie);
                list.add(R.string.edit_user_info_region_yiyang);
                list.add(R.string.edit_user_info_region_binzhou);
                list.add(R.string.edit_user_info_region_yongzhou);
                list.add(R.string.edit_user_info_region_huaihua);
                list.add(R.string.edit_user_info_region_loudi);
                list.add(R.string.edit_user_info_region_xiangxi);
                break;
            case CITY_CODE_GUANGDONG:
                list.add(R.string.edit_user_info_region_guangdong_guangzhou);
                list.add(R.string.edit_user_info_region_guangdong_shaoguan);
                list.add(R.string.edit_user_info_region_guangdong_shenzhen);
                list.add(R.string.edit_user_info_region_guangdong_zhuhai);
                list.add(R.string.edit_user_info_region_guangdong_shantou);
                list.add(R.string.edit_user_info_region_guangdong_foshan);
                list.add(R.string.edit_user_info_region_guangdong_jiangmen);
                list.add(R.string.edit_user_info_region_guangdong_zhanjiang);
                list.add(R.string.edit_user_info_region_guangdong_maoming);
                list.add(R.string.edit_user_info_region_guangdong_zhaoqing);
                list.add(R.string.edit_user_info_region_guangdong_huizhou);
                list.add(R.string.edit_user_info_region_guangdong_meizhou);
                list.add(R.string.edit_user_info_region_guangdong_shanwei);
                list.add(R.string.edit_user_info_region_guangdong_heyuan);
                list.add(R.string.edit_user_info_region_guangdong_yangjiang);
                list.add(R.string.edit_user_info_region_guangdong_qingyuan);
                list.add(R.string.edit_user_info_region_guangdong_dongguan);
                list.add(R.string.edit_user_info_region_guangdong_zhongshan);
                list.add(R.string.edit_user_info_region_guangdong_chaozhou);
                list.add(R.string.edit_user_info_region_guangdong_jieyang);
                list.add(R.string.edit_user_info_region_guangdong_yunfu);
                break;
            case CITY_CODE_GUANGXI:
                list.add(R.string.edit_user_info_region_guangxi_nanning);
                list.add(R.string.edit_user_info_region_guangxi_liuzhou);
                list.add(R.string.edit_user_info_region_guangxi_guilin);
                list.add(R.string.edit_user_info_region_guangxi_wuzhou);
                list.add(R.string.edit_user_info_region_guangxi_beihai);
                list.add(R.string.edit_user_info_region_guangxi_fangchenggang);
                list.add(R.string.edit_user_info_region_guangxi_guigang);
                list.add(R.string.edit_user_info_region_guangxi_yulin);
                list.add(R.string.edit_user_info_region_guangxi_baise);
                list.add(R.string.edit_user_info_region_guangxi_hezhou);
                list.add(R.string.edit_user_info_region_guangxi_hechi);
                list.add(R.string.edit_user_info_region_guangxi_laibin);
                list.add(R.string.edit_user_info_region_guangxi_chongzuo);
                break;
            case CITY_CODE_HAINAN:
                list.add(R.string.edit_user_info_region_hainan_haikou);
                list.add(R.string.edit_user_info_region_hainan_sanya);
                list.add(R.string.edit_user_info_region_hainan_sansha);
                list.add(R.string.edit_user_info_region_hainan_danzhou);
                break;
            case CITY_CODE_CHONGQING:
                list.add(R.string.edit_user_info_region_chongqing_wanzhou);
                list.add(R.string.edit_user_info_region_chongqing_peiling);
                list.add(R.string.edit_user_info_region_chongqing_yuzhong);
                list.add(R.string.edit_user_info_region_chongqing_dadukou);
                list.add(R.string.edit_user_info_region_chongqing_jiangbei);
                list.add(R.string.edit_user_info_region_chongqing_shapingba);
                list.add(R.string.edit_user_info_region_chongqing_jiulongpo);
                list.add(R.string.edit_user_info_region_chongqing_nanan);
                list.add(R.string.edit_user_info_region_chongqing_qijiang);
                list.add(R.string.edit_user_info_region_chongqing_dazu);
                list.add(R.string.edit_user_info_region_chongqing_banan);
                list.add(R.string.edit_user_info_region_chongqing_qianjiang);
                list.add(R.string.edit_user_info_region_chongqing_changshou);
                list.add(R.string.edit_user_info_region_chongqing_jiangjin);
                list.add(R.string.edit_user_info_region_chongqing_hechuan);
                list.add(R.string.edit_user_info_region_chongqing_yongchuan);
                list.add(R.string.edit_user_info_region_chongqing_nanchuan);
                list.add(R.string.edit_user_info_region_chongqing_bishan);
                list.add(R.string.edit_user_info_region_chongqing_tongliang);
                list.add(R.string.edit_user_info_region_chongqing_tongnan);
                list.add(R.string.edit_user_info_region_chongqing_rongchang);
                list.add(R.string.edit_user_info_region_chongqing_kaizhou);
                list.add(R.string.edit_user_info_region_chongqing_liangping);
                list.add(R.string.edit_user_info_region_chongqing_chengkou);
                list.add(R.string.edit_user_info_region_chongqing_fengdu);
                list.add(R.string.edit_user_info_region_chongqing_dianjiang);
                list.add(R.string.edit_user_info_region_chongqing_wulong);
                list.add(R.string.edit_user_info_region_chongqing_zhongxian);
                list.add(R.string.edit_user_info_region_chongqing_yunyang);
                list.add(R.string.edit_user_info_region_chongqing_fengjie);
                list.add(R.string.edit_user_info_region_chongqing_wushan);
                list.add(R.string.edit_user_info_region_chongqing_wuxi);
                list.add(R.string.edit_user_info_region_chongqing_shizhu);
                list.add(R.string.edit_user_info_region_chongqing_xiushan);
                list.add(R.string.edit_user_info_region_chongqing_youyang);
                list.add(R.string.edit_user_info_region_chongqing_pengshui);
                break;
            case CITY_CODE_SICHUAN:
                list.add(R.string.edit_user_info_region_chengdu);
                list.add(R.string.edit_user_info_region_zigong);
                list.add(R.string.edit_user_info_region_panzhihua);
                list.add(R.string.edit_user_info_region_luzhou);
                list.add(R.string.edit_user_info_region_deyang);
                list.add(R.string.edit_user_info_region_mianyang);
                list.add(R.string.edit_user_info_region_guangyuan);
                list.add(R.string.edit_user_info_region_suining);
                list.add(R.string.edit_user_info_region_neijiang);
                list.add(R.string.edit_user_info_region_leshan);
                list.add(R.string.edit_user_info_region_nanchong);
                list.add(R.string.edit_user_info_region_meishan);
                list.add(R.string.edit_user_info_region_yibin);
                list.add(R.string.edit_user_info_region_guangan);
                list.add(R.string.edit_user_info_region_dazhou);
                list.add(R.string.edit_user_info_region_yaan);
                list.add(R.string.edit_user_info_region_bazhong);
                list.add(R.string.edit_user_info_region_ziyang);
                list.add(R.string.edit_user_info_region_abazangzu);
                list.add(R.string.edit_user_info_region_ganzizangzu);
                list.add(R.string.edit_user_info_region_liangshan);
                break;
            case CITY_CODE_GUIZHOU:
                list.add(R.string.edit_user_info_region_guiyang);
                list.add(R.string.edit_user_info_region_liupanshui);
                list.add(R.string.edit_user_info_region_guizhou_zunyi);
                list.add(R.string.edit_user_info_region_guizhou_zunyi);
                list.add(R.string.edit_user_info_region_guizhou_bijie);
                list.add(R.string.edit_user_info_region_guizhou_tongren);
                list.add(R.string.edit_user_info_region_guizhou_qianxinan);
                list.add(R.string.edit_user_info_region_guizhou_qiandongnan);
                list.add(R.string.edit_user_info_region_guizhou_qiannan);
                break;

            case CITY_CODE_YUNNAN:
                list.add(R.string.edit_user_info_region_yunnan_kunming);
                list.add(R.string.edit_user_info_region_yunnan_qujing);
                list.add(R.string.edit_user_info_region_yunnan_yuxi);
                list.add(R.string.edit_user_info_region_yunnan_baoshan);
                list.add(R.string.edit_user_info_region_yunnan_zhaotong);
                list.add(R.string.edit_user_info_region_yunnan_lijiang);
                list.add(R.string.edit_user_info_region_yunnan_puer);
                list.add(R.string.edit_user_info_region_yunnan_lincang);
                list.add(R.string.edit_user_info_region_yunnan_chuxiong);
                list.add(R.string.edit_user_info_region_yunnan_honghe);
                list.add(R.string.edit_user_info_region_yunnan_wenshan);
                list.add(R.string.edit_user_info_region_yunnan_xishuangbanna);
                list.add(R.string.edit_user_info_region_yunnan_dali);
                list.add(R.string.edit_user_info_region_yunnan_dehong);
                list.add(R.string.edit_user_info_region_yunnan_nujiang);
                list.add(R.string.edit_user_info_region_yunnan_diqing);
                break;
            case CITY_CODE_XIZANG:
                list.add(R.string.edit_user_info_region_xizang_lasa);
                list.add(R.string.edit_user_info_region_xizang_rikaze);
                list.add(R.string.edit_user_info_region_xizang_changdu);
                list.add(R.string.edit_user_info_region_xizang_linzhi);
                list.add(R.string.edit_user_info_region_xizang_shannan);
                list.add(R.string.edit_user_info_region_xizang_naqu);
                list.add(R.string.edit_user_info_region_xizang_ali);
                break;
            case CITY_CODE_SHANXI_3:
                list.add(R.string.edit_user_info_region_shanxi_3_xian);
                list.add(R.string.edit_user_info_region_shanxi_3_tongchuan);
                list.add(R.string.edit_user_info_region_shanxi_3_baoji);
                list.add(R.string.edit_user_info_region_shanxi_3_xianyang);
                list.add(R.string.edit_user_info_region_shanxi_3_weinan);
                list.add(R.string.edit_user_info_region_shanxi_3_yanan);
                list.add(R.string.edit_user_info_region_shanxi_3_hanzhong);
                list.add(R.string.edit_user_info_region_shanxi_3_yulin);
                list.add(R.string.edit_user_info_region_shanxi_3_ankang);
                list.add(R.string.edit_user_info_region_shanxi_3_shangluo);
                break;
            case CITY_CODE_GANSU:
                list.add(R.string.edit_user_info_region_lanzhou);
                list.add(R.string.edit_user_info_region_jiayuguan);
                list.add(R.string.edit_user_info_region_jinchang);
                list.add(R.string.edit_user_info_region_gansu_baiyin);
                list.add(R.string.edit_user_info_region_gansu_tianshui);
                list.add(R.string.edit_user_info_region_gansu_wuwei);
                list.add(R.string.edit_user_info_region_gansu_zhangye);
                list.add(R.string.edit_user_info_region_gansu_pingliang);
                list.add(R.string.edit_user_info_region_gansu_jiuquan);
                list.add(R.string.edit_user_info_region_gansu_qingyang);
                list.add(R.string.edit_user_info_region_gansu_dingxi);
                list.add(R.string.edit_user_info_region_gansu_gannan);
                list.add(R.string.edit_user_info_region_gansu_linxia);
                list.add(R.string.edit_user_info_region_gansu_gannanzangzu);
                break;
            case CITY_CODE_QINGHAI:
                list.add(R.string.edit_user_info_region_qinghai_xining);
                list.add(R.string.edit_user_info_region_qinghai_haidong);
                list.add(R.string.edit_user_info_region_qinghai_haibei);
                list.add(R.string.edit_user_info_region_qinghai_huangnan);
                list.add(R.string.edit_user_info_region_qinghai_hainan);
                list.add(R.string.edit_user_info_region_qinghai_guoluo);
                list.add(R.string.edit_user_info_region_qinghai_yushu);
                list.add(R.string.edit_user_info_region_qinghai_haixi);
                break;
            case CITY_CODE_NINGXIA:
                list.add(R.string.edit_user_info_region_ningxia_yinchuan);
                list.add(R.string.edit_user_info_region_ningxia_shizuishan);
                list.add(R.string.edit_user_info_region_ningxia_wuzhong);
                list.add(R.string.edit_user_info_region_ningxia_guyuan);
                list.add(R.string.edit_user_info_region_ningxia_zhongwei);
                break;
            case CITY_CODE_XINJIANG:
                list.add(R.string.edit_user_info_region_xinjiang_wulumuqi);
                list.add(R.string.edit_user_info_region_xinjiang_kelamayi);
                list.add(R.string.edit_user_info_region_xinjiang_tulufan);
                list.add(R.string.edit_user_info_region_xinjiang_hami);
                list.add(R.string.edit_user_info_region_xinjiang_changji);
                list.add(R.string.edit_user_info_region_xinjiang_boerdalameng);
                list.add(R.string.edit_user_info_region_xinjiang_bayinguolemeng);
                list.add(R.string.edit_user_info_region_xinjiang_akesu);
                list.add(R.string.edit_user_info_region_xinjiang_kezilesukeer);
                list.add(R.string.edit_user_info_region_xinjiang_kashi);
                list.add(R.string.edit_user_info_region_xinjiang_hetian);
                list.add(R.string.edit_user_info_region_xinjiang_yili);
                list.add(R.string.edit_user_info_region_xinjiang_tacheng);
                list.add(R.string.edit_user_info_region_xinjiang_aletai);
                break;
        }

        return list;
    }

    public static int getRegionName(int regionCode) {
        int regionNameResId = 0;
        switch (regionCode) {
            case CITY_CODE_BEIJING:
                regionNameResId = R.string.edit_user_info_region_beijing;
                break;
            case CITY_CODE_TIANJIN:
                regionNameResId = R.string.edit_user_info_region_tianjin;
                break;
            case CITY_CODE_HEBEI:
                regionNameResId = R.string.edit_user_info_region_hebei;
                break;

            case CITY_CODE_SHANXI:
                regionNameResId = R.string.edit_user_info_region_shanxi;
                break;
            case CITY_CODE_NEIMENGGU:
                regionNameResId = R.string.edit_user_info_region_neimenggu;
                break;
            case CITY_CODE_lIAONING:
                regionNameResId = R.string.edit_user_info_region_liaoning;
                break;
            case CITY_CODE_JILIN:
                regionNameResId = R.string.edit_user_info_region_jilin;
                break;
            case CITY_CODE_HEILONGJIANG:
                regionNameResId = R.string.edit_user_info_region_heilongjiang;
                break;

            case CITY_CODE_SHANGHAI:
                regionNameResId = R.string.edit_user_info_region_shanghai;
                break;
            case CITY_CODE_JIANGSU:
                regionNameResId = R.string.edit_user_info_region_jiangsu;
                break;

            case CITY_CODE_ZHEJIANG:
                regionNameResId = R.string.edit_user_info_region_zhejiang;
                break;
            case CITY_CODE_ANHUI:
                regionNameResId = R.string.edit_user_info_region_anhui;
                break;
            case CITY_CODE_FUJIAN:
                regionNameResId = R.string.edit_user_info_region_fujian;

                break;
            case CITY_CODE_JIANGXI:
                regionNameResId = R.string.edit_user_info_region_jiangxi;

                break;
            case CITY_CODE_SHANDONG:
                regionNameResId = R.string.edit_user_info_region_shandong;

                break;
            case CITY_CODE_HENAN:
                regionNameResId = R.string.edit_user_info_region_henan;

                break;
            case CITY_CODE_HUBEI:
                regionNameResId = R.string.edit_user_info_region_hubei;

                break;
            case CITY_CODE_HUNAN:
                regionNameResId = R.string.edit_user_info_region_hunan;

                break;
            case CITY_CODE_GUANGDONG:
                regionNameResId = R.string.edit_user_info_region_guangdong;

                break;
            case CITY_CODE_GUANGXI:
                regionNameResId = R.string.edit_user_info_region_guangxi;

                break;
            case CITY_CODE_HAINAN:
                regionNameResId = R.string.edit_user_info_region_hainan;

                break;
            case CITY_CODE_CHONGQING:
                regionNameResId = R.string.edit_user_info_region_chongqing;

                break;
            case CITY_CODE_SICHUAN:
                regionNameResId = R.string.edit_user_info_region_sichuan;

                break;
            case CITY_CODE_GUIZHOU:
                regionNameResId = R.string.edit_user_info_region_guizhou;

                break;

            case CITY_CODE_YUNNAN:
                regionNameResId = R.string.edit_user_info_region_yunnan;

                break;
            case CITY_CODE_XIZANG:
                regionNameResId = R.string.edit_user_info_region_xizang;

                break;
            case CITY_CODE_SHANXI_3:
                regionNameResId = R.string.edit_user_info_region_tianjin;

                break;
            case CITY_CODE_GANSU:
                regionNameResId = R.string.edit_user_info_region_gansu;

                break;
            case CITY_CODE_QINGHAI:
                regionNameResId = R.string.edit_user_info_region_qinghai;

                break;
            case CITY_CODE_NINGXIA:
                regionNameResId = R.string.edit_user_info_region_ningxia;

                break;
            case CITY_CODE_XINJIANG:
                regionNameResId = R.string.edit_user_info_region_xinjiang;

                break;
            case CITY_CODE_TAIWAN:
                regionNameResId = R.string.edit_user_info_region_taiwan;

                break;
            case CITY_CODE_XIANGGANG:
                regionNameResId = R.string.edit_user_info_region_xianggang;
                break;
            case CITY_CODE_AOMEN:
                regionNameResId = R.string.edit_user_info_region_aomen;
                break;
            case CITY_CODE_HAIWAI:
                regionNameResId = R.string.edit_user_info_region_over_seas;
                break;
        }

        return regionNameResId;

    }
}
