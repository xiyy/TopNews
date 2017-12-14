package com.xi.liuliu.topnews.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.feedback.Comment;
import com.avos.avoscloud.feedback.FeedbackAgent;
import com.avos.avoscloud.feedback.FeedbackThread;
import com.xi.liuliu.topnews.bean.UserInfo;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.event.GenderSelectorEvent;
import com.xi.liuliu.topnews.event.LoginEvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by zhangxb171 on 2017/12/13.
 */

public class DataMonitor {
    private static final String TAG = "DataMonitor";
    private static DataMonitor mInstance = null;
    //注意传入getApplicationContext，不能传入Activity，防止内存泄漏
    private static Context mContext;

    private DataMonitor(Context context) {
        mContext = context;
    }

    public static DataMonitor getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DataMonitor.class) {
                if (mInstance == null) {
                    mInstance = new DataMonitor(context);
                }
            }
        }
        return mInstance;
    }

    public static UserInfo getUserInfo() {
        UserInfo userInfo = null;
        SharedPrefUtil sharedPrefUtil = SharedPrefUtil.getInstance(mContext);
        boolean isUserLoggedIn = sharedPrefUtil.getBoolean(Constants.LOGIN_SP_KEY);
        if (isUserLoggedIn) {
            String userName = sharedPrefUtil.getString(Constants.USER_NAME_SP_KEY);
            int gender = sharedPrefUtil.getInt(Constants.GENDER_SP_KEY);
            String birthDay = sharedPrefUtil.getString(Constants.BIRTH_SP_KEY);
            int homeTownResCode = sharedPrefUtil.getInt(Constants.CITY_SP_KEY);
            String homeTown = "";
            if (homeTownResCode != -1) {
                homeTown = mContext.getString(homeTownResCode);
            }
            String introduce = sharedPrefUtil.getString(Constants.INTRODUCE_SP_KEY);
            String phoneNumber = sharedPrefUtil.getString(Constants.USER_PHONE_NUMBER_SP_KEY);
            int loginType = sharedPrefUtil.getInt(Constants.LOGIN_TYPE_SP_KEY);
            userInfo = new UserInfo();
            userInfo.setUserName(userName);
            userInfo.setBirthDay(birthDay);
            userInfo.setGender(gender);
            userInfo.setHometown(homeTown);
            userInfo.setIntroduce(introduce);
            userInfo.setPhoneNumber(phoneNumber);
            userInfo.setLoginType(loginType);
        }
        return userInfo;
    }

    public static JSONObject getDeviceInfo() {
        JSONObject deviceInfo = null;
        String imei = DeviceUtil.getImei(mContext);
        String brand = DeviceUtil.getBrand();
        String model = DeviceUtil.getModel();
        String osVersion = DeviceUtil.getOsVersion();
        String cpuType = DeviceUtil.getCpuType();
        String notificationState = DeviceUtil.getNotificationState(mContext);
        try {
            deviceInfo = new JSONObject();
            deviceInfo.put("imei", imei).put("brand", brand).put("model", model).put("osVersion", osVersion)
                    .put("cpuType", cpuType).put("notificationState", notificationState);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deviceInfo;
    }

    public static JSONObject getAppInfo() {
        JSONObject appInfo = null;
        int versionCode = AppUtil.getVersionCode(mContext);
        String versionName = AppUtil.getVersionName(mContext);
        try {
            appInfo = new JSONObject();
            appInfo.put("versionCode", versionCode);
            appInfo.put("versionName", versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appInfo;
    }

    public static JSONObject getNetWorkInfo() {
        JSONObject netWorkInfo = null;
        try {
            netWorkInfo = new JSONObject();
            netWorkInfo.put("netWorkType", NetWorkUtil.getNetWorkType(mContext));
            netWorkInfo.put("ip", NetWorkUtil.getIp(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return netWorkInfo;
    }

    public static JSONObject getLocationInfo() {
        SharedPrefUtil sharedPrefUtil = SharedPrefUtil.getInstance(mContext);
        String longitude = sharedPrefUtil.getString(Constants.LOCATION_lONGITUDE_SP_KEY);
        String latitude = sharedPrefUtil.getString(Constants.LOCATION_LATITUDE_SP_KEY);
        JSONObject locationInfo = null;
        if (!TextUtils.isEmpty(longitude) && !TextUtils.isEmpty(latitude)) {
            locationInfo = new JSONObject();
            try {
                locationInfo.put("longitude", longitude);
                locationInfo.put("latitude", latitude);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return locationInfo;

    }

    public static String getCrashInfo() {
        return null;
    }

    /**
     * @param isEncrypted,是否加密发送
     */
    public static void sendData(boolean isEncrypted) {
        JSONObject collectedData = new JSONObject();
        JSONObject userInfoObject = null;
        UserInfo userInfo = getUserInfo();
        if (userInfo != null) {
            userInfoObject = new JSONObject();
            int loginType = userInfo.getLoginType();
            String loginTypeStr = null;
            if (loginType == LoginEvent.LOGIN_PHONE) {
                loginTypeStr = "手机登录";
            } else if (loginType == LoginEvent.LOGIN_WEIBO) {
                loginTypeStr = "微博登录";
            }
            int genderType = userInfo.getGender();
            String genderTypeStr = null;
            if (genderType == GenderSelectorEvent.GENDER_MALE) {
                genderTypeStr = "男";
            } else if (genderType == GenderSelectorEvent.GENDER_FEMALE) {
                genderTypeStr = "女";
            }
            try {
                userInfoObject.put("userName", userInfo.getUserName()).put("gender", genderTypeStr)
                        .put("hometown", userInfo.getHometown()).put("birthDay", userInfo.getBirthDay())
                        .put("introduce", userInfo.getIntroduce()).put("phoneNumber", userInfo.getPhoneNumber())
                        .put("loginType", loginTypeStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject deviceInfoObject = getDeviceInfo();
        JSONObject appInfoObject = getAppInfo();
        JSONObject netWorkInfoObject = getNetWorkInfo();
        JSONObject locationInfoObject = getLocationInfo();
        try {
            if (userInfoObject != null) {
                collectedData.put("userInfo", userInfoObject);
            }
            if (deviceInfoObject != null) {
                collectedData.put("deviceInfo", deviceInfoObject);
            }
            if (appInfoObject != null) {
                collectedData.put("appInfo", appInfoObject);
            }
            if (netWorkInfoObject != null) {
                collectedData.put("netWorkInfo", netWorkInfoObject);
            }
            if (locationInfoObject != null) {
                collectedData.put("locationInfo", locationInfoObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String collectData = collectedData.toString();
        String sendData;
        if (isEncrypted) {
            byte[] aesEncrypt = AesEncryptUtil.aesCbcPkcs5PaddingEncrypt(collectData.getBytes(), Constants.DATA_MONITOR_SEND_DATA_AES_KEY, Constants.DATA_MONITOR_SEND_DATA_AES_IV);
            byte[] base64Encode = Base64Encoder.encode(aesEncrypt);
            sendData = new String(base64Encode);
            Log.i(TAG, "ase encrypt data:" + sendData);
            byte[] base64Decode = Base64Encoder.decode(base64Encode);
            byte[] aesDecrypt = AesEncryptUtil.aesCbcPkcs5PaddingDecrypt(base64Decode, Constants.DATA_MONITOR_SEND_DATA_AES_KEY, Constants.DATA_MONITOR_SEND_DATA_AES_IV);
            Log.i(TAG, "aes desEncrypt:" + new String(aesDecrypt));
        } else {
            sendData = collectData;
            Log.i(TAG, "send data:" + collectData);
        }
        FeedbackAgent agent = new FeedbackAgent(mContext);
        FeedbackThread thread = agent.getDefaultThread();
        thread.setContact(SharedPrefUtil.getInstance(mContext).getString(Constants.USER_PHONE_NUMBER_SP_KEY));
        Comment feedbackContent = new Comment(sendData);
        thread.add(feedbackContent);
        thread.sync(new FeedbackThread.SyncCallback() {
            @Override
            public void onCommentsSend(List<Comment> list, AVException e) {
                Log.i(TAG, "发送成功！");
            }

            @Override
            public void onCommentsFetch(List<Comment> list, AVException e) {

            }
        });

    }
}
