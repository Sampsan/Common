package com.sag.common.ui.activity;

import android.os.Bundle;

import com.sag.common.R;
import com.sag.common.api.Channel;
import com.sag.common.databinding.ActivityMainBinding;
import com.sag.common.model.ADModel;
import com.sag.common.ui.fragment.RecyclerViewFragment;
import com.sag.library.adapter.convenient.BindConvenientAdapter;
import com.sag.library.presenter.BaseActivity;
import com.sag.library.util.FILEUtils;
import com.sag.library.util.UIUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    private BindConvenientAdapter mConvenientAdapter = new BindConvenientAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new RecyclerViewFragment()).commit();
        FILEUtils utils = FILEUtils.with(this);
        if (utils.obtainBoolean("isSecond")) {
            UIUtils.toast(this, "isSecond");
        } else {
            UIUtils.toast(this, "isFirst");
            utils.beginTransaction()
                    .holdData("isSecond", true)
                    .commit();
        }
    }

    @Override
    protected void initUI() {
        mConvenientAdapter.setNewData(mLayoutBinding.banner, new ADModel().data);
    }

    @Override
    public void onDo(int code, Object... objects) {
        switch (code) {
            case Channel.HOME_TOOLBAR_OPERATION:
                mToolbarBinding.title.setText((String) objects[0]);
                break;
        }
    }

}
