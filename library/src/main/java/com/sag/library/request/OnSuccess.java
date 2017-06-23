package com.sag.library.request;

/**
 * Created by Administrator on 2017/6/12
 */

public interface OnSuccess<T> {
    void call(T model);
}
