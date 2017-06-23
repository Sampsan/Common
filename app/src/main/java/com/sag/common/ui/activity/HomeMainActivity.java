package com.sag.common.ui.activity;

import com.sag.common.R;
import com.sag.common.databinding.ActivityHomeMainBinding;
import com.sag.common.model.History;
import com.sag.common.ui.fragment.RecyclerViewFragment;
import com.sag.library.adapter.viewpager.FragmentPagerBindAdapter;
import com.sag.library.module.HomeMainModule;
import com.sag.library.presenter.BaseActivity;
import com.sag.library.presenter.BaseFragment;
import com.sag.library.util.DEVUtils;

/**
 * Created by SAG on 2017/6/14
 */

public class HomeMainActivity extends BaseActivity<ActivityHomeMainBinding> {

    private HomeMainModule module = new HomeMainModule();

    @Override
    public int getLayoutID() {
        return R.layout.activity_home_main;
    }

    @Override
    protected void initUI() {
        mToolbarBinding.title.setText("测试");
        mLayoutBinding.pager.setAdapter(new FragmentPagerBindAdapter(getSupportFragmentManager(), new BaseFragment[]{new RecyclerViewFragment(), new RecyclerViewFragment()}));
        module.setNavigation(mLayoutBinding.pager, mLayoutBinding.navigation);

    }

    @Override
    protected void initData() {
        mLayoutBinding.setModel(new History());
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        DEVUtils.quickQuiet(this);
    }

}
