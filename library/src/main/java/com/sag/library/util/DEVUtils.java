package com.sag.library.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;

import com.sag.library.api.Constant;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 设备管理器
 * <p>
 * Created by SAG on 2017/6/12 0012.
 */

public class DEVUtils {

    /**
     * 获取手机操作系统版本
     */
    public static int getVersionNumber() {
        int sdkVersion;
        try {
            sdkVersion = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            sdkVersion = 0;
        }
        return sdkVersion;
    }

    /**
     * 复制文本到剪切板
     */
    public static void copyMessage(Context context, String message) {
        if (getVersionNumber() >= 11) {
            android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(message);
        } else {
            // 得到剪贴板管理器
            android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, message));
        }
        UIUtils.toast(context, "复制成功！");
    }

    private static Date QUIET_TIME;

    /**
     * 连按两次返回键，关闭应用
     */
    public static void quickQuiet(Activity activity) {
        if (QUIET_TIME == null) {
            QUIET_TIME = new Date();
            UIUtils.toast(activity, "再按一次退出程序");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    QUIET_TIME = null;
                }
            }, 2000);
        } else {
            activity.finish();
            QUIET_TIME = null;
        }

    }

}
