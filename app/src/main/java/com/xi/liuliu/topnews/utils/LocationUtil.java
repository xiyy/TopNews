package com.xi.liuliu.topnews.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.http.HttpClient;
import com.xi.liuliu.topnews.location.LocationTracker;
import com.xi.liuliu.topnews.location.TrackerSettings;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by zhangxb171 on 2017/12/14.
 */

public class LocationUtil {
    private static final String TAG = "LocationUtil";

    public static void getLongitudeAndLatitude(final Context context) {
        //允许GPS、WiFi、基站定位，设置超时时间5秒
        TrackerSettings trackerSettings = new TrackerSettings();
        trackerSettings.setUseGPS(true).setUseNetwork(true).setUsePassive(true).setTimeout(5000);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationTracker locationTracker = new LocationTracker(context, trackerSettings) {
            @Override
            public void onLocationFound(@NonNull Location location) {
                String latitude = location.getLatitude() + "";
                String longitude = location.getLongitude() + "";
                Log.i(TAG, "latitude:" + latitude + "longitude:" + longitude);
                SharedPrefUtil.getInstance(context).putString(Constants.LOCATION_LATITUDE_SP_KEY, latitude);
                SharedPrefUtil.getInstance(context).putString(Constants.LOCATION_lONGITUDE_SP_KEY, longitude);
            }

            @Override
            public void onTimeout() {
                Log.i(TAG, "location time out");
            }
        };
        locationTracker.startListening();
    }

    /**
     * 实时定位，当打开新闻报料页时才定位，定位成功再获取附近位置列表
     *
     * @param context
     * @param nearbyPositionList
     */
    public static void getNearbyPositionList(final Context context, final ArrayList nearbyPositionList) {
        TrackerSettings trackerSettings = new TrackerSettings();
        trackerSettings.setUseGPS(true).setUseNetwork(true).setUsePassive(true).setTimeout(5000);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationTracker locationTracker = new LocationTracker(context, trackerSettings) {
            @Override
            public void onLocationFound(@NonNull Location location) {
                String latitude = location.getLatitude() + "";
                String longitude = location.getLongitude() + "";
                Log.i(TAG, "latitude:" + latitude + "longitude:" + longitude);
                SharedPrefUtil.getInstance(context).putString(Constants.LOCATION_LATITUDE_SP_KEY, latitude);
                SharedPrefUtil.getInstance(context).putString(Constants.LOCATION_lONGITUDE_SP_KEY, longitude);
                if (!TextUtils.isEmpty(latitude) && !TextUtils.isEmpty(longitude)) {
                    HttpClient httpClient = new HttpClient();
                    httpClient.setCallback(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG, "getLocation onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String jsonResponse = response.body().string();
                            Log.i(TAG, "getLocation onResponse:" + jsonResponse);
                            JsonUtil.getAddresses(jsonResponse, nearbyPositionList);
                        }
                    }).requestAddresses(latitude, longitude);
                }
            }

            @Override
            public void onTimeout() {
                Log.i(TAG, "location time out");
            }
        };
        locationTracker.startListening();
    }
}
