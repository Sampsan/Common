package com.sag.library.util;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * 等待进度条
 * <p>
 * Created by SAG on 2017/6/12 0012.
 */

public class PROUtils {

    private KProgressHUD mKProgressHUD;
    private int index = 0;

    public PROUtils(Context context) {
        mKProgressHUD = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("正在加载")
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);
    }

    public void show() {
        if (index == 0 && !mKProgressHUD.isShowing()) {
            mKProgressHUD.setCancellable(true);
            mKProgressHUD.show();
        }
        index++;
    }

    public void dismiss() {
        if (index == 1 && mKProgressHUD.isShowing()) {
            mKProgressHUD.dismiss();
        }
        index--;
    }

}
