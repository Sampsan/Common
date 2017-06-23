package com.sag.common.api;

import android.app.Application;

import com.sag.library.api.Api;

/**
 * Created by SAG on 2017/6/12 0012.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Api.init(this);
    }
}
