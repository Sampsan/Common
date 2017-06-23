package com.sag.library.util;

import android.util.Log;

import com.sag.library.api.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 调试工具
 * <p>
 * Created by SAG on 2017/6/12 0012.
 */

public class LOGUtils {

    private static final String KEY = LOGUtils.class.getSimpleName();

    public static void debug(String key, String message) {
        if (Constant.isDebug) {
            Log.e(key, message);
        }
    }

    public static void debug(String message) {
        if (Constant.isDebug) {
            Log.e(KEY, message);
        }
    }

    public static void createLogPath() {
        if (Constant.isDebug) {
            File file = new File(Constant.debug_path);
            if (!file.exists()) {
                file.mkdir();
            }
        }
    }

    public static void saveLogs(String key, String message) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(Constant.debug_path + key + ".txt"));
            writer.write(message);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
