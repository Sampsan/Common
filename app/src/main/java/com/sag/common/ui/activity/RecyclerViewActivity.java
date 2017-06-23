package com.sag.common.ui.activity;

import com.sag.common.api.URLConstant;
import com.sag.common.model.History;
import com.sag.common.model.IndexModel;
import com.sag.library.activity.SimpleRecyclerViewActivity;
import com.sag.library.api.Constant;
import com.sag.library.request.ClientHelper;

/**
 * Created by SAG on 2017/6/14
 */

public class RecyclerViewActivity extends SimpleRecyclerViewActivity {

    @Override
    protected void initUI() {
        super.initUI();
        mToolbarBinding.title.setText("测试");
    }

    @Override
    public ClientHelper clientHelper(int index, boolean isLoading) {
        return ClientHelper.with(this)
                .url(URLConstant.MAIN_URL)
                .isPost(true)
                .isLoading(isLoading)
                .isPrompt(Constant.Prompt.ON_ERROR)
                .setParameter("wwi", URLConstant.my_tracks)
                .setParameter("member_id", "2")
                .setParameter("page", index)
                .clazz(History.class);
    }

}
