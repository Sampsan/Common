package com.sag.library.presenter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.sag.library.R;
import com.sag.library.databinding.SimpleToolbarBinding;
import com.sag.library.request.ClientHelper;
import com.sag.library.util.LOGUtils;
import com.sag.library.util.PROUtils;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static android.provider.Contacts.SettingsColumns.KEY;

/**
 * Created by SAG on 2017/6/12
 */

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity implements Presenter {

    private PROUtils PROUtils;

    protected T mLayoutBinding;
    protected SimpleToolbarBinding mToolbarBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        PresenterManager.register(this);
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        PROUtils = new PROUtils(this);
        mToolbarBinding = DataBindingUtil.setContentView(this, R.layout.simple_toolbar);
        mLayoutBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutID(), (ViewGroup) mToolbarBinding.getRoot(), true);
        mToolbarBinding.back.setOnClickListener(v -> finish());
        initUI();
        initData();
    }

    public abstract int getLayoutID();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        PROUtils.show();
    }

    @Override
    public void hideLoading() {
        PROUtils.dismiss();
    }

    @Override
    public void onDo(int code, Object... objects) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClientHelper.onDestroy(this);
        PresenterManager.onDestroy(this);
    }

    protected void initUI() {
    }

    protected void initData() {
    }

}
