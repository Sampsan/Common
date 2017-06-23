package com.sag.library.listener;

import android.view.View;

/**
 * 事件监听器
 * 1、在BindModel中处理，重写onDo(View v)方法
 * 2、在presenter中处理，presenter继承该监听器，重写onItemClick(View v, M model)方法
 * <p>
 * Created by SAG on 2017/6/23 0023
 */

public interface OnItemClickListener<M> {
    void onItemClick(View v, M model);
}
