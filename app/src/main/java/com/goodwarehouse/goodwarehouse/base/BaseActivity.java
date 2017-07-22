package com.goodwarehouse.goodwarehouse.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by HaoMeng on 2017-07-05.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static final String GOODSID = "goods_id";
    public static String PURCHASE = "purchase";
    public static String JOINCART = "joinCart";
    public static String SELECT = "select";
    public static String ISADD = "isAdd";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        ButterKnife.inject(this);
        initTitle();
        initView();
        initData();
        initListener();
    }

    public void initTitle() {

    }

    public abstract void initListener();

    public abstract void initData();

    public void initView() {

    }

    public abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
