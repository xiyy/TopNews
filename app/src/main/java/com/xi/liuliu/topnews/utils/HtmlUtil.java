package com.xi.liuliu.topnews.utils;

import android.util.Log;

import com.xi.liuliu.topnews.event.NewsPhotoUrlsEvent;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.greenrobot.event.EventBus;

/**
 * Created by zhangxb171 on 2017/7/28.
 */

public class HtmlUtil {
    private static final String TAG = "HtmlUtil";

    public static String getHtmlSourceCode(String path) {
        String sourceCode = null;
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            InputStream inStream = conn.getInputStream();
            byte[] data = readInputStream(inStream);
            sourceCode = new String(data, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "getHtmlSourceCode Exception");
        }
        return sourceCode;
    }

    public static byte[] readInputStream(InputStream inStream) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            inStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "readInputStream Exception");
        }
        return outStream.toByteArray();
    }

    public static void getImagesUrlFromHtml(final String path) {
        new Thread() {
            @Override
            public void run() {
                List<String> imageSrcList = new ArrayList<String>();
                String htmlCode = getHtmlSourceCode(path);
                //<img/>标签正则表达式
                Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(htmlCode);
                String quote = null;
                String src = null;
                while (m.find()) {
                    quote = m.group(1);
                    src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
                    imageSrcList.add(src);
                }
                if (imageSrcList == null || imageSrcList.size() == 0) {
                    Log.i(TAG, "新闻中未匹配到图片链接");
                }
                EventBus.getDefault().post(new NewsPhotoUrlsEvent(imageSrcList.toArray(new String[imageSrcList.size()])));
            }
        }.start();
    }
}
