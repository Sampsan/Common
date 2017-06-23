package com.sag.library.adapter.convenient;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.sag.library.BR;
import com.sag.library.R;
import com.sag.library.listener.OnItemClickListener;
import com.sag.library.model.BaseBindModel;

import java.util.List;

/**
 * 轮播图Adapter
 * <p>
 * Created by SAG on 2017/6/23 0023
 */

public class BindConvenientAdapter<M extends BaseBindModel> implements CBViewHolderCreator {

    private OnItemClickListener mListener;

    public void setNewData(ConvenientBanner banner, List<M> lists) {
        banner.setPages(this, lists)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL) //设置指示器位置（左、中、右）
                .setPointViewVisible(true)    //设置指示器是否可见
                .setPageIndicator(new int[]{R.drawable.shape_dot_white, R.drawable.shape_dot_red})   //设置指示器圆点
                .startTurning(5000) //设置自动切换（同时设置了切换时间间隔）
//                .stopTurning()    //关闭自动切换
                .setManualPageable(true);  //设置手动影响（设置了该项无法手动切换）
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public Object createHolder() {
        return new MyHolder();
    }

    class MyHolder implements Holder<M> {

        private FrameLayout layout;

        @Override
        public View createView(Context context) {
            layout = new FrameLayout(context);
            return layout;
        }

        @Override
        public void UpdateUI(Context context, int position, M model) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), model.getLayoutID(), layout, true);
            binding.setVariable(BR.model, model);
            if (mListener != null) {
                binding.setVariable(BR.listener, (View.OnClickListener) view -> mListener.onItemClick(view, model));
            } else {
                binding.setVariable(BR.listener, (View.OnClickListener) view -> model.onDo(view));
            }
            binding.executePendingBindings();
        }

    }

}
