package com.sag.library.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sag.library.R;
import com.sag.library.databinding.ActivitySwipeRecyclerBinding;
import com.sag.library.listener.OnItemClickListener;
import com.sag.library.module.RecyclerViewSwipeModule;
import com.sag.library.presenter.BaseFragment;

/**
 * 简单列表视图Fragment基类
 * <p>
 * Created by SAG on 2017/6/14
 */

public abstract class SimpleRecyclerViewFragment<T extends ActivitySwipeRecyclerBinding> extends BaseFragment<T> implements RecyclerViewSwipeModule.Client, OnItemClickListener {

    private RecyclerViewSwipeModule module = new RecyclerViewSwipeModule(this);

    @Override
    protected int getLayoutID() {
        return R.layout.activity_swipe_recycler;
    }

    @Override
    protected void initUI() {
        mLayoutBinding.swipeLayout.setOnRefreshListener(module);
        mLayoutBinding.swipeLayout.setOnLoadMoreListener(module);
        mLayoutBinding.swipeTarget.setLayoutManager(new LinearLayoutManager(getContext()));
        mLayoutBinding.swipeTarget.setAdapter(module.getAdapter());
    }

    @Override
    protected void initData() {
        module.initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        module.onDestroy();
    }

    @Override
    public void closeRefresh() {
        mLayoutBinding.swipeLayout.setRefreshing(false);
    }

    @Override
    public void closeLoadMore() {
        mLayoutBinding.swipeLayout.setLoadingMore(false);
    }

    @Override
    public void loadMore(boolean isLoadMore) {
        mLayoutBinding.swipeLayout.setLoadMoreEnabled(isLoadMore);
    }

    @Override
    public void onItemClick(View v, Object model) {

    }

}
