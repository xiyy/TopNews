package com.xi.liuliu.topnews.location;


import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;


public class TrackerSettings {

    public static final TrackerSettings DEFAULT = new TrackerSettings();
    public static final long DEFAULT_MIN_TIME_BETWEEN_UPDATES = 5 * 60 * 1000;
    public static final float DEFAULT_MIN_METERS_BETWEEN_UPDATES = 100;
    public static final int DEFAULT_TIMEOUT = 60 * 1000;
    private long mTimeBetweenUpdates = -1;
    private float mMetersBetweenUpdates = -1;
    private int mTimeout = -1;
    private boolean mUseGPS = true;
    private boolean mUseNetwork = true;
    private boolean mUsePassive = true;

    public TrackerSettings setTimeBetweenUpdates(@FloatRange(from = 1) long timeBetweenUpdates) {
        if (timeBetweenUpdates > 0) {
            mTimeBetweenUpdates = timeBetweenUpdates;
        }
        return this;
    }

    public long getTimeBetweenUpdates() {
        return mTimeBetweenUpdates <= 0 ? DEFAULT_MIN_TIME_BETWEEN_UPDATES : mTimeBetweenUpdates;
    }

    public TrackerSettings setMetersBetweenUpdates(@FloatRange(from = 1) float metersBetweenUpdates) {
        if (metersBetweenUpdates > 0) {
            mMetersBetweenUpdates = metersBetweenUpdates;
        }
        return this;
    }

    public float getMetersBetweenUpdates() {
        return mMetersBetweenUpdates <= 0 ? DEFAULT_MIN_METERS_BETWEEN_UPDATES : mMetersBetweenUpdates;
    }


    public TrackerSettings setTimeout(@IntRange(from = 1) int timeout) {
        if (timeout > 0) {
            mTimeout = timeout;
        }
        return this;
    }

    public int getTimeout() {
        return this.mTimeout <= -1 ? DEFAULT_TIMEOUT : mTimeout;
    }


    public TrackerSettings setUseGPS(boolean useGPS) {
        mUseGPS = useGPS;
        return this;
    }

    public boolean shouldUseGPS() {
        return mUseGPS;
    }


    public TrackerSettings setUseNetwork(boolean useNetwork) {
        mUseNetwork = useNetwork;
        return this;
    }

    public boolean shouldUseNetwork() {
        return mUseNetwork;
    }

    public TrackerSettings setUsePassive(boolean usePassive) {
        mUsePassive = usePassive;
        return this;
    }

    public boolean shouldUsePassive() {
        return mUsePassive;
    }
}
