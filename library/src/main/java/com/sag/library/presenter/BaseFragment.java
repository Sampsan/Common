package com.sag.library.presenter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sag.library.request.ClientHelper;
import com.sag.library.util.PROUtils;

/**
 * Created by SAG on 2017/6/12
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment implements Presenter {

    private PROUtils PROUtils;
    protected T mLayoutBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterManager.register(this);
        PROUtils = new PROUtils(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mLayoutBinding == null) {
            mLayoutBinding = DataBindingUtil.inflate(inflater, getLayoutID(), container, false);
            initUI();
            initData();
        }
        return mLayoutBinding.getRoot();
    }

    protected void initUI() {
    }

    protected void initData() {
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
    public void onDestroyView() {
        super.onDestroyView();
        ClientHelper.onDestroy(this);
        PresenterManager.onDestroy(this);
    }

    protected abstract int getLayoutID();

}
