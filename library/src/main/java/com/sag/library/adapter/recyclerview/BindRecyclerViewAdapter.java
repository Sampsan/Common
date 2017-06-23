package com.sag.library.adapter.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sag.library.BR;
import com.sag.library.api.Constant;
import com.sag.library.listener.OnItemClickListener;
import com.sag.library.model.BaseBindModel;
import com.sag.library.util.LOGUtils;

import java.util.ArrayList;

/**
 * RecyclerView + DataBinding适配器
 * <p>
 * Created by SAG on 2017/5/17
 */

public class BindRecyclerViewAdapter<V extends ViewDataBinding, T extends BaseBindModel> extends BaseQuickAdapter<T, BaseViewHolder> {

    private OnItemClickListener mListener;

    public BindRecyclerViewAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<>());
    }

    public void setOnClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        V binding = (V) helper.getConvertView().getTag();
        binding.setVariable(BR.model, item);
        if (mListener != null) {
            binding.setVariable(BR.listener, (View.OnClickListener) view -> mListener.onItemClick(view, item));
        } else {
            binding.setVariable(BR.listener, (View.OnClickListener) view -> item.onDo(view));
        }
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), layoutResId, parent, false);
        View root = binding.getRoot();
        root.setTag(binding);
        return root;
    }

}
