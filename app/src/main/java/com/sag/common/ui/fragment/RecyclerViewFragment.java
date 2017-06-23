package com.sag.common.ui.fragment;

import android.view.View;

import com.sag.common.api.Channel;
import com.sag.common.api.URLConstant;
import com.sag.common.model.History;
import com.sag.common.ui.activity.HomeMainActivity;
import com.sag.common.ui.activity.MainActivity;
import com.sag.library.api.Constant;
import com.sag.library.fragment.SimpleRecyclerViewFragment;
import com.sag.library.presenter.PresenterManager;
import com.sag.library.request.ClientHelper;

/**
 * Created by SAG on 2017/6/14 0014.
 */

public class RecyclerViewFragment extends SimpleRecyclerViewFragment {

    @Override
    protected void initUI() {
        super.initUI();
        PresenterManager.key(MainActivity.class)
                .onDo(Channel.HOME_TOOLBAR_OPERATION, "测试");
    }

    @Override
    public ClientHelper clientHelper(int index, boolean isLoading) {
        return ClientHelper.with(this)
                .url(URLConstant.MAIN_URL)
                .isPost(false)
                .isLoading(isLoading)
                .isPrompt(Constant.Prompt.ON_ERROR)
                .setParameter("wwi", URLConstant.my_tracks)
                .setParameter("member_id", "2")
                .setParameter("page", index)
                .clazz(History.class);
    }

    @Override
    public void onItemClick(View v, Object model) {
    }

}

