package com.xi.liuliu.topnews.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.xi.liuliu.topnews.R;
import com.xi.liuliu.topnews.constants.Constants;
import com.xi.liuliu.topnews.location.LocationTracker;
import com.xi.liuliu.topnews.location.TrackerSettings;
import com.xi.liuliu.topnews.utils.SharedPrefUtil;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getLocation();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    //禁止用返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void getLocation() {
        //允许GPS、WiFi、基站定位，设置超时时间5秒
        TrackerSettings trackerSettings = new TrackerSettings();
        trackerSettings.setUseGPS(true).setUseNetwork(true).setUsePassive(true).setTimeout(5000);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationTracker locationTracker = new LocationTracker(this, trackerSettings) {
            @Override
            public void onLocationFound(@NonNull Location location) {
                Log.i(TAG, "latitude:" + location.getLatitude() + "longitude" + location.getLongitude());
                SharedPrefUtil.getInstance(SplashActivity.this).putString(Constants.LOCATION_LATITUDE_SP_KEY, location.getLatitude() + "");
                SharedPrefUtil.getInstance(SplashActivity.this).putString(Constants.LOCATION_lONGITUDE_SP_KEY, location.getLongitude() + "");
            }

            @Override
            public void onTimeout() {
                Log.i(TAG, "location time out");
            }
        };
        locationTracker.startListening();
    }
}
