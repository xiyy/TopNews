package com.xi.liuliu.topnews.utils;

import android.text.TextUtils;
import android.util.Log;

import com.xi.liuliu.topnews.bean.Address;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zhangxb171 on 2017/9/5.
 */

public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static void getAddresses(String jsonStr, ArrayList list) {
        if (!TextUtils.isEmpty(jsonStr) && list != null) {
            try {
                JSONObject addresses = new JSONObject(jsonStr);
                JSONObject result = addresses.getJSONObject("result");
                JSONArray pois = result.optJSONArray("pois");
                if (pois != null && pois.length() > 0) {
                    for (int i = 0; i < pois.length(); i++) {
                        JSONObject address = pois.getJSONObject(i);
                        if (address != null) {
                            String name = address.getString("name");
                            String number = address.getString("addr");
                            Address each = new Address(name, number);
                            list.add(each);
                        }
                    }
                }
            } catch (Exception e) {
                Log.i("TAG", e.getMessage());
            }

        }
    }
}
