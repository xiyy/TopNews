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
     * JDK只支持AES-128加密，也就是密钥长度必须是128bit；参数为密钥key，key的长度小于16字符时用"0"补充，key长度大于16字符时截取前16位
     **/
    private static SecretKeySpec create128BitsKey(String key) {
        if (key == null) {
            key = "";
        }
        byte[] data = null;
        StringBuffer buffer = new StringBuffer(16);
        buffer.append(key);
        //小于16后面补0
        while (buffer.length() < 16) {
            buffer.append("0");
        }
        //大于16，截取前16个字符
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
     * 创建128位的偏移量，iv的长度小于16时后面补0，大于16，截取前16个字符;
     *
     * @param iv
     * @return
     */
    private static IvParameterSpec create128BitsIV(String iv) {
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

    /**
     * 填充方式为Pkcs5Padding时，最后一个块需要填充χ个字节，填充的值就是χ，也就是填充内容由JDK确定
     *
     * @param srcContent
     * @param password
     * @param iv
     * @return
     */
    public static byte[] aesCbcPkcs5PaddingEncrypt(byte[] srcContent, String password, String iv) {
        SecretKeySpec key = create128BitsKey(password);
        IvParameterSpec ivParameterSpec = create128BitsIV(iv);
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
        SecretKeySpec key = create128BitsKey(password);
        IvParameterSpec ivParameterSpec = create128BitsIV(iv);
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

    /**
     * 填充方式为NoPadding时，最后一个块的填充内容由程序员确定，通常为0.
     * AES/CBC/NoPadding加密的明文长度必须是16的整数倍，明文长度不满足16时，程序员要扩充到16的整数倍
     *
     * @param sSrc
     * @param aesKey
     * @param aesIV
     * @return
     */
    public static byte[] aesCbcNoPaddingEncrypt(byte[] sSrc, String aesKey, String aesIV) {
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
                //填充字符'a'
                //result[i] = 'a';
                result[i] = 0;
            }
        }
        SecretKeySpec skeySpec = create128BitsKey(aesKey);
        //使用CBC模式，需要一个初始向量iv，可增加加密算法的强度
        IvParameterSpec iv = create128BitsIV(aesIV);
        Cipher cipher = null;
        try {
            //算法/模式/补码方式
            cipher = Cipher.getInstance(AES_CBC_NO_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "aesCbcNoPaddingEncrypt Exception");
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
        SecretKeySpec skeySpec = create128BitsKey(aesKey);
        IvParameterSpec iv = create128BitsIV(aesIV);
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
