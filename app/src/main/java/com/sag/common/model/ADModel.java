package com.sag.common.model;

import android.view.View;

import com.sag.common.R;
import com.sag.library.model.BaseBindModel;
import com.sag.library.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAG on 2017/6/23 0023
 */

public class ADModel {

    public List<Item> data;

    public ADModel() {
        data = new ArrayList<>();
        data.add(new Item());
        data.add(new Item());
        data.add(new Item());
    }

    public static class Item implements BaseBindModel {

        public static final String TYPE = "com.sag.common.model.ADModel.Item";

        public String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498198115296&di=04efb3eb43b45f78e9a41792e9da433b&imgtype=0&src=http%3A%2F%2Fwww.bz55.com%2Fuploads%2Fallimg%2F150701%2F140-150F1142638.jpg";

        @Override
        public int getLayoutID() {
            return R.layout.item_ad;
        }

        @Override
        public void onDo(View v) {
            UIUtils.toast(v.getContext(), TYPE);
        }

    }

}
