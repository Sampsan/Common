package com.sag.library.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.sag.library.R;
import com.sag.library.databinding.ActivitySwipeRecyclerBinding;
import com.sag.library.listener.OnItemClickListener;
import com.sag.library.module.RecyclerViewSwipeModule;
import com.sag.library.presenter.BaseActivity;

/**
 * 简单的列表视图activity，带有下拉刷新和上拉加载功能
 * <p>
 * Created by SAG on 2017/6/14
 */

public abstract class SimpleRecyclerViewActivity<T extends ActivitySwipeRecyclerBinding> extends BaseActivity<T> implements RecyclerViewSwipeModule.Client, OnItemClickListener {

    private RecyclerViewSwipeModule module = new RecyclerViewSwipeModule(this);

    @Override
    public int getLayoutID() {
        return R.layout.activity_swipe_recycler;
    }

    @Override
    protected void initUI() {
        mLayoutBinding.swipeLayout.setOnRefreshListener(module);
        mLayoutBinding.swipeLayout.setOnLoadMoreListener(module);
        mLayoutBinding.swipeTarget.setLayoutManager(new LinearLayoutManager(this));
        mLayoutBinding.swipeTarget.setAdapter(module.getAdapter());
    }

    @Override
    protected void initData() {
        module.initData();
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
