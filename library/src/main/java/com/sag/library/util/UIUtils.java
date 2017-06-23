package com.sag.library.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * UI工具
 * <p>
 * Created by SAG on 2017/6/12
 */

public class UIUtils {

    public static void showDialog(Context context, String title, String message, DialogInterface.OnClickListener onClickListener) {
        if (context != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton("确认", onClickListener);
            builder.setNegativeButton("取消", (dialog, which) -> dialog.dismiss());
            builder.create().show();
        }
    }

    public static void showDebug(Context context, String title, String message, DialogInterface.OnClickListener onSure, DialogInterface.OnClickListener onCancel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确认", onSure);
        builder.setNegativeButton("取消", onCancel);
        builder.create().show();
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
