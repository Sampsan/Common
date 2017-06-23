package com.sag.library.model;

import android.view.View;

/**
 * DataBinding实体类接口
 * <p>
 * Created by SAG on 2017/5/18 0018.
 */

public interface BaseBindModel {

    int getLayoutID();

    void onDo(View v);

}
