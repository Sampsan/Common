package com.sag.library.adapter.recyclerview;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.sag.library.model.BaseBindModel;

/**
 * RecyclerView + DataBinding 多类型Item适配器
 * <p>
 * Created by SAG on 2017/5/18
 */

public class BindMultiAdapter<T extends BaseBindModel> extends BindRecyclerViewAdapter<ViewDataBinding, T> {

    public BindMultiAdapter() {
        super(0);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (mData.size() > viewType) {
            return createBaseViewHolder(parent, mData.get(viewType).getLayoutID());
        } else {
            return super.onCreateDefViewHolder(parent, viewType);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int header = getHeaderLayoutCount();
        if (header > position) {
            return super.getItemViewType(position);
        }
        if (mData.size() > position - header) {
            return position;
        } else {
            return super.getItemViewType(position);
        }
    }

}
