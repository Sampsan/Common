package com.sag.library.module;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.sag.library.R;
import com.sag.library.adapter.recyclerview.BindMultiAdapter;
import com.sag.library.databinding.LayoutEmptyBinding;
import com.sag.library.listener.OnItemClickListener;
import com.sag.library.model.impl.EmptyModelBase;
import com.sag.library.model.impl.SimpleRecyclerViewModel;
import com.sag.library.request.ClientHelper;

/**
 * 刷新加载模块
 * <p>
 * Created by SAG on 2017/6/15 0015.
 */

public class RecyclerViewSwipeModule implements OnRefreshListener, OnLoadMoreListener {

    private int index = 1;//上拉加载页码
    private View mEmptyView;
    private BindMultiAdapter mAdapter = new BindMultiAdapter();

    private Client mClient;

    public RecyclerViewSwipeModule(Client client) {
        mClient = client;
    }

    public BindMultiAdapter getAdapter() {
        return mAdapter;
    }

    public void initData() {
        mClient.clientHelper(0, true).request(obj -> {
            mClient.closeRefresh();
            SimpleRecyclerViewModel model = checkType(obj);
            if (model != null && model.isOk()) {
                index = 1;
                mAdapter.setNewData(model.getData());
                mClient.loadMore(true);
            } else {
                setEmpty();
                mClient.loadMore(false);
            }
        });
        if (mClient instanceof OnItemClickListener) {
            mAdapter.setOnClickListener((OnItemClickListener) mClient);
        }
    }

    @Override
    public void onLoadMore() {
        mClient.clientHelper(index, false).request(obj -> {
            mClient.closeLoadMore();
            SimpleRecyclerViewModel model = checkType(obj);
            if (model != null && model.isOk()) {
                index++;
                mAdapter.addData(model.getData());
            } else {
                mClient.loadMore(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        mClient.clientHelper(0, false).request(obj -> {
            mClient.closeRefresh();
            SimpleRecyclerViewModel model = checkType(obj);
            if (model != null && model.isOk()) {
                index = 1;
                mAdapter.setNewData(model.getData());
                mClient.loadMore(true);
            } else {
                setEmpty();
                mClient.loadMore(false);
            }
        });
    }

    public void onDestroy() {
        mClient = null;
    }

    private SimpleRecyclerViewModel checkType(Object obj) {
        if (!(obj instanceof SimpleRecyclerViewModel)) {
            try {
                throw new ClassCastException("列表视图activity model必须继承自SimpleRecyclerViewModel");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return (SimpleRecyclerViewModel) obj;
    }

    private void setEmpty() {
        if (mEmptyView == null) {
            LayoutEmptyBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mClient.getContext()), R.layout.layout_empty, null, false);
            binding.setModel(new EmptyModelBase("暂无数据"));
            mEmptyView = binding.getRoot();
            mAdapter.setEmptyView(mEmptyView);
        }
    }

    public interface Client {

        //子类负责提供网络访问接口
        ClientHelper clientHelper(int index, boolean isLoading);

        Context getContext();

        void closeRefresh();

        void closeLoadMore();

        void loadMore(boolean isLoadMore);
    }

}
