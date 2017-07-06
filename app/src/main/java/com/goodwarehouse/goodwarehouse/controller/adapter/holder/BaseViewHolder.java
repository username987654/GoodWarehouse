package com.goodwarehouse.goodwarehouse.controller.adapter.holder;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public abstract class BaseViewHolder<T> {


    private final View rootView;
    private T t;

    public BaseViewHolder() {
        rootView = initView();
        ButterKnife.inject(this, rootView);
        rootView.setTag(this);
    }


    public void setData(T t) {
        this.t = t;
        setContent(t);
    }

    public View getView() {
        return rootView;
    }


    /*
    * 创建布局
    *
    * 设置数据
    *
    * */
    protected abstract void setContent(T t);

    public abstract View initView();
}
