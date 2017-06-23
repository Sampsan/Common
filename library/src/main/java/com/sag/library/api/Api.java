package com.sag.library.api;

import android.app.Application;

import com.sag.library.request.ClientHelper;
import com.sag.library.util.LOGUtils;

/**
 * library接入应用入口
 * <p>
 * Created by SAG on 2017/6/12
 */

public class Api {

    public static void init(Application application) {
        ClientHelper.init(application);
        LOGUtils.createLogPath();
    }

}
