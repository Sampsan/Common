package com.sag.library.model.impl;

import android.view.View;

import com.sag.library.R;
import com.sag.library.model.BaseBindModel;

/**
 * RecyclerView + DataBinding 空页面model
 * <p>
 * Created by SAG on 2017/6/13
 */

public class EmptyModelBase implements BaseBindModel {

    public String title;

    public EmptyModelBase(String title) {
        this.title = title;
    }

    @Override
    public int getLayoutID() {
        return R.layout.layout_empty;
    }

    @Override
    public void onDo(View v) {

    }

}
