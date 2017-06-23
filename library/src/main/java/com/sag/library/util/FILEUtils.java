package com.sag.library.util;

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

/**
 * 数据存储工具
 * <p>
 * Created by SAG on 2017/6/15
 */

public class FILEUtils {

    /**
     * 在本地保存文本信息
     */
    public static void holdFile(String path, String message) {
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(path));
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

    /**
     * 从本地读取文本信息
     */
    public static String obtainFile(String path) {
        BufferedReader reader = null;
        StringBuffer message = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            while (reader.ready()) {
                message.append(reader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return message.toString();
    }

    /**
     * ********** 在共享文件中保存数据 **********
     */
    private SharedPreferences mPreferences;

    private FILEUtils(Context context, String fileName) {
        mPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static FILEUtils with(Context context, String fileName) {
        return new FILEUtils(context, fileName);
    }

    public static FILEUtils with(Context context) {
        return new FILEUtils(context, Constant.DEFAULT_FILE_NAME);
    }

    public boolean obtainBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public float obtainFloat(String key) {
        return mPreferences.getFloat(key, 0);
    }

    public int obtainInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public long obtainLong(String key) {
        return mPreferences.getLong(key, 0);
    }

    public String obtainString(String key) {
        return mPreferences.getString(key, "");
    }

    /**
     * ********** 从共享文件中读取数据 **********
     */
    public Editor beginTransaction() {
        return new Editor();
    }

    public class Editor {
        private SharedPreferences.Editor mEditor;

        public Editor() {
            mEditor = mPreferences.edit();
        }

        /**
         * 保存数据到共享文件
         */
        public Editor holdData(String key, Object object) {
            switch (object.getClass().getSimpleName()) {
                case "String":
                    mEditor.putString(key, (String) object);
                    break;
                case "Boolean":
                    mEditor.putBoolean(key, (Boolean) object);
                    break;
                case "Float":
                    mEditor.putFloat(key, (Float) object);
                    break;
                case "Integer":
                    mEditor.putInt(key, (Integer) object);
                    break;
                case "Long":
                    mEditor.putLong(key, (Long) object);
                    break;
            }
            return this;
        }

        public void commit() {
            mEditor.commit();
        }

    }

}
