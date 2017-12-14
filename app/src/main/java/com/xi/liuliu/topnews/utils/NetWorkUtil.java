package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.http.HttpClient;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liuliu on 2017/6/26.
 */

public class NetWorkUtil {
    private static final String TAG = "NetWorkUtil";
    private static final String NETWORK_TYPE_2G = "2G";
    private static final String NETWORK_TYPE_3G = "3G";
    private static final String NETWORK_TYPE_4G = "4G";
    private static final String NETWORK_TYPE_WIFI = "wifi";
    private static final String UN_KNOW = "未知";

    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //判断WiFi是否打开
    public static boolean isWiFi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    //判断移动数据是否打开
    public static boolean isMobile(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    public static String getNetWorkType(Context context) {
        String netWorkType = UN_KNOW;
        ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                netWorkType = NETWORK_TYPE_WIFI;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String subTypeName = networkInfo.getSubtypeName();
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netWorkType = NETWORK_TYPE_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netWorkType = NETWORK_TYPE_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netWorkType = NETWORK_TYPE_4G;
                        break;
                    default:
                        if (subTypeName.equalsIgnoreCase("TD-SCDMA")
                                || subTypeName.equalsIgnoreCase("WCDMA")
                                || subTypeName
                                .equalsIgnoreCase("CDMA2000")) {
                            netWorkType = NETWORK_TYPE_3G;
                        } else {
                            netWorkType = subTypeName;
                        }
                        break;
                }
            }
        }
        return netWorkType;
    }

    public static boolean ping(String ip) {
        Runtime run = Runtime.getRuntime();
        Process process;
        try {
            String pingCmd = "ping -c 1 -i 0.2 -W 1 " + ip;
            Log.i(TAG, "pingCmd:" + pingCmd);
            process = run.exec(pingCmd);
            int result = process.waitFor();
            if (result == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "ping Exception");
        }
        return false;
    }

    public static String getIp(Context context) {
        return SharedPrefUtil.getInstance(context).getString(Constants.IP_SP_KEY);
    }

    /**
     * 应用程序启动时获取IP，通过panda接口获取IP地址；IP接口需要附带guid参数，此参数需要加密传输，先aes加密，再base64转码
     */
    public static void requestIP(final Context context) {
        byte[] aesEncrypt = AesEncryptUtil.aesCbcPkcs5PaddingEncrypt(DeviceUtil.getGuid(context).getBytes(), Constants.IP_FROM_PANDA_AES_KEY, Constants.IP_FROM_PANDA_AES_IV);
        String base64Encode = new String(Base64Encoder.encode(aesEncrypt));
        new HttpClient().setCallback(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "get ip onFailure:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Log.i(TAG, "get ip onResponse:" + data);
                String ip = JsonUtil.getIp(data);
                SharedPrefUtil.getInstance(context).putString(Constants.IP_SP_KEY, ip);
            }
        }).requestIP(base64Encode);
    }
}
