package com.sag.library.request;

import android.content.Context;

import com.google.gson.Gson;
import com.sag.library.api.Constant;
import com.sag.library.model.impl.BaseModel;
import com.sag.library.presenter.Presenter;
import com.sag.library.util.DEVUtils;
import com.sag.library.util.LOGUtils;
import com.sag.library.util.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import rx.Subscriber;
import rx.Subscription;

import static com.sag.library.api.Constant.isDebug;

/**
 * Created by SAG on 2017/3/8
 */

public class ClientHelper<T extends BaseModel> extends Subscriber<String> {

    public static void init(Context context) {
        RetrofitHelper.init(context);
    }

    private ClientHelper(Presenter presenter) {
        mPresenter = presenter;
    }

    private static final RetrofitHelper mHelper = new RetrofitHelper();
    public static final Gson json = new Gson();

    private Class clazz;
    private String url;

    private int isPrompt;//Constant.Prompt_ALWAYS,Constant.Prompt_NEVER,Constant.Prompt_ON_ERROR
    private boolean isLoading;
    private boolean isPost;

    private Presenter mPresenter;
    private OnSuccess<T> onSuccess;

    private HashMap<String, Object> parameters = new HashMap<>();
    private static final HashMap<String, ArrayList<Subscription>> Map = new HashMap<>();

    public static ClientHelper with(Presenter presenter) {
        final String key = presenter.getClass().getSimpleName();
        if (!Map.containsKey(key)) {
            Map.put(key, new ArrayList<>());
        }

        //这里配置接口公有参数
        ClientHelper helper = new ClientHelper(presenter);
        helper.setParameter("app", "interface");

        return helper;
    }

    public ClientHelper setParameter(String key, Object value) {
        if (value != null) {
            parameters.put(key, value + "");
        }
        return this;
    }

    public ClientHelper isLoading(boolean isLoading) {
        this.isLoading = isLoading;
        return this;
    }

    public ClientHelper isPrompt(int isPrompt) {
        this.isPrompt = isPrompt;
        return this;
    }

    public ClientHelper isPost(boolean isPost) {
        this.isPost = isPost;
        return this;
    }

    public ClientHelper clazz(Class clazz) {
        this.clazz = clazz;
        return this;
    }

    public ClientHelper url(String url) {
        this.url = url;
        return this;
    }

    public void setSubscription(Subscription subscription) {
        final String key = mPresenter.getClass().getSimpleName();
        Map.get(key).add(subscription);
    }

    public void request(OnSuccess onSuccess) {
        this.onSuccess = onSuccess;
        if (isLoading) {
            mPresenter.showLoading();
        }
        if (isDebug) {
            String url = this.url + "?";
            Set<String> set = parameters.keySet();
            Iterator<String> iterator = set.iterator();
            if (iterator.hasNext()) {
                String key = iterator.next();
                url += key + "=" + parameters.get(key);
            }
            while (iterator.hasNext()) {
                String key = iterator.next();
                url += "&" + key + "=" + parameters.get(key);
            }
            LOGUtils.debug("ClientRequest->" + this.url.substring(this.url.lastIndexOf("/", this.url.length())), url);
        }
        if (isPost) {
            mHelper.post(url, parameters, this);
        } else {
            mHelper.get(url, parameters, this);
        }
    }

    @Override
    public void onCompleted() {
        if (isLoading) {
            mPresenter.hideLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isLoading) {
            mPresenter.hideLoading();
        }
        if (isDebug) {
            LOGUtils.debug("ClientRequest->Error", e.getMessage());
            UIUtils.showDialog(mPresenter.getContext(), "RequestError", e.getMessage() + "\n" + "备注：点击确定复制错误信息", (dialogInterface, i) -> DEVUtils.copyMessage(mPresenter.getContext(), e.getMessage()));
        }
    }

    @Override
    public void onNext(String json) {
        if (isDebug) {
            String path = url.substring(url.lastIndexOf("/", url.length()));
            final int length = json.length();
            if (length > 4000) {
                for (int i = 0; i < length; i += 4000) {
                    if (i + 4000 < length)
                        LOGUtils.debug("ClientResult" + i + "->" + path, json.substring(i, i + 4000));
                    else
                        LOGUtils.debug("ClientResult->" + path, json.substring(i, json.length()));
                }
            } else {
                LOGUtils.debug("ClientResult->" + path, json);
            }
            LOGUtils.saveLogs(path, json);
        }

        T t = null;
        try {
            t = (T) this.json.fromJson(json, clazz);
        } catch (Exception e) {
            if (isDebug) {
                LOGUtils.debug("ClientResult->Error", e.getMessage());
                UIUtils.showDialog(mPresenter.getContext(), "ResultError", e.getMessage() + "\n" + "备注：点击确定复制错误信息", (dialogInterface, i) -> DEVUtils.copyMessage(mPresenter.getContext(), e.getMessage()));
            }
        }

        if (t != null) {
            switch (isPrompt) {
                case Constant.Prompt.NEVER:
                    break;
                case Constant.Prompt.ALWAYS:
                    UIUtils.toast(mPresenter.getContext(), t.getMessage());
                    break;
                case Constant.Prompt.ON_ERROR:
                    if (!t.isOk()) {
                        UIUtils.toast(mPresenter.getContext(), t.getMessage());
                    }
                    break;
            }
        }

        if (t != null) {
            onSuccess.call(t);
        }
    }

    public static void onDestroy(Presenter presenter) {
        final String key = presenter.getClass().getSimpleName();
        if (Map.containsKey(key)) {
            ArrayList<Subscription> lists = Map.get(key);
            if (lists != null && lists.size() != 0) {
                final int total = lists.size();
                for (int i = 0; i < total; i++) {
                    Subscription subscription = lists.get(i);
                    if (subscription != null && subscription.isUnsubscribed()) {
                        subscription.unsubscribe();
                    }
                }
                lists.clear();
            }
            Map.remove(key);
        }
    }
}
