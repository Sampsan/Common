package com.sag.library.util;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * 加密工具
 *
 * Created by SAG on 2017/6/12
 */

public class DESUtils {

    //加密
    public static String encryptAsDoNet(String message) {
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] bytes = cipher.doFinal(message.getBytes());
            return new String(Base64.encode(bytes, Base64.DEFAULT));
        } catch (Exception e) {
            return message;
        }
    }

    //解密
    public static String decryptDoNet(String message) {
        byte[] bytes = Base64.decode(message.getBytes(), Base64.DEFAULT);
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(KEY.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(KEY.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] retByte = cipher.doFinal(bytes);
            return new String(retByte);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DES", "解密异常：" + message);
            return message;
        }

    }

}
