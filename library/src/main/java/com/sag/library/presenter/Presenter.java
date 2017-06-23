package com.sag.library.presenter;

import android.content.Context;

/**
 * Created by SAG on 2017/6/12
 */

public interface Presenter {

    Context getContext();

    void showLoading();

    void hideLoading();

    void onDo(int code, Object... objects);

}
