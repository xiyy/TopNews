package com.xi.liuliu.topnews.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zhangxiaobei on 2017/1/16.
 */

public class AesEncryptUtil {
    private static final String TAG = "AesEncryptUtil";
    private static final String UTF8 = "UTF-8";
    private static final String AES = "AES";
    private static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";
    private static final String AES_CBC_NO_PADDING = "AES/CBC/NoPadding";

    /**
     * 创建16位的密钥，key小于16位后面补0，大于16位，截取前16位；
     * 也可以不限制密钥的位数，后端给定什么值就用什么值，直接返回new SecretKeySpec(key.getBytes(), "AES");
     **/
    private static SecretKeySpec create16BitsKey(String key) {
        if (key == null) {
            key = "";
        }
        byte[] data = null;
        StringBuffer buffer = new StringBuffer(16);
        buffer.append(key);
        //小于16位后面补0
        while (buffer.length() < 16) {
            buffer.append("0");
        }
        //大于16位，截取前16位
        if (buffer.length() > 16) {
            buffer.setLength(16);
        }
        try {
            data = buffer.toString().getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, AES);
    }

    /**
     * 创建16位的偏移量，iv小于16位后面补0，大于16位，截取前16位;
     * 也可以不限定iv的位数，后端给定什么值就用什么值，直接返回new IvParameterSpec(iv.getBytes())
     *
     * @param iv
     * @return
     */
    private static IvParameterSpec create16BitsIV(String iv) {
        if (iv == null) {
            iv = "";
        }
        byte[] data = null;
        StringBuffer buffer = new StringBuffer(16);
        buffer.append(iv);
        while (buffer.length() < 16) {
            buffer.append("0");
        }
        if (buffer.length() > 16) {
            buffer.setLength(16);
        }
        try {
            data = buffer.toString().getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }


    public static byte[] aesCbcPkcs5PaddingEncrypt(byte[] srcContent, String password, String iv) {
        SecretKeySpec key = create16BitsKey(password);
        IvParameterSpec ivParameterSpec = create16BitsIV(iv);
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
            byte[] encryptedContent = cipher.doFinal(srcContent);
            return encryptedContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static byte[] aesCbcPkcs5PaddingDecrypt(byte[] encryptedContent, String password, String iv) {
        SecretKeySpec key = create16BitsKey(password);
        IvParameterSpec ivParameterSpec = create16BitsIV(iv);
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            byte[] decryptedContent = cipher.doFinal(encryptedContent);
            return decryptedContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] aesCbcNoPaddingEncrypt(byte[] sSrc, String aesKey, String aesIV) {
        SecretKeySpec skeySpec = create16BitsKey(aesKey);
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = create16BitsIV(aesIV);
        Cipher cipher = null;
        try {
            //算法/模式/补码方式
            cipher = Cipher.getInstance(AES_CBC_NO_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "aesCbcNoPaddingEncrypt Exception");
        }
        //AES/CBC/NoPadding加密模式只能加密长度是16的整数倍的数据，原始数据不满足16的整数倍时，要扩充到16的整数倍
        //加密的数据长度不是16的整数倍时，原始数据后面补0，直到长度满足16的整数倍
        int len = sSrc.length;
        //计算补0后的长度
        while (len % 16 != 0) len++;
        byte[] result = new byte[len];
        //在最后补0
        for (int i = 0; i < len; ++i) {
            if (i < sSrc.length) {
                result[i] = sSrc[i];
            } else {
                result[i] = 0;
            }
        }
        byte[] encrypted = null;
        try {
            encrypted = cipher.doFinal(result);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "aesCbcNoPaddingEncrypt  Exception");
        }
        return encrypted;
    }

    public static byte[] aesCbcNoPaddingDecrypt(byte[] sSrc, String aesKey, String aesIV) {
        SecretKeySpec skeySpec = create16BitsKey(aesKey);
        IvParameterSpec iv = create16BitsIV(aesIV);
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_NO_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] decryptContent = cipher.doFinal(sSrc);
            return decryptContent;
        } catch (Exception ex) {
            Log.i(TAG, "aesCbcNoPaddingDecrypt Exception");
        }
        return null;
    }
}
