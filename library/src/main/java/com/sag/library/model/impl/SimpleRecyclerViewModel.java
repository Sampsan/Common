package com.sag.library.model.impl;

import com.sag.library.model.BaseBindModel;

import java.util.List;

/**
 * SimpleRecyclerView页面（SimpleRecyclerViewActivity、SimpleRecyclerViewFragment）model
 * <p>
 * Created by SAG on 2017/6/14
 */

public class SimpleRecyclerViewModel<T extends BaseBindModel> extends BaseModel {

    public List<T> data;

    @Override
    public boolean isOk() {
        return super.isOk() && data != null && data.size() != 0;
    }

    public List<T> getData() {
        return data;
    }

}
