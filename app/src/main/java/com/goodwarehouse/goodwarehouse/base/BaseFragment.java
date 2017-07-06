package com.goodwarehouse.goodwarehouse.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import butterknife.ButterKnife;

/**
 * Created by HaoMeng on 2017-07-05.
 */

public abstract class BaseFragment extends Fragment {
    public Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            TextView textView = new TextView(getActivity());
            textView.setText("哈哈哈");
            textView.setTextColor(Color.RED);
            textView.setTextSize(30);
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            return textView;
        }
        if (getUrl() != null) {
            HttpUtils.HttpNet(getUrl(), new HttpUtils.onNetRequestContent() {
                @Override
                public void onError(String content) {
                    Log.e("onError", content);
                }

                @Override
                public void onResponse(String response) {
                    Log.e("TAG", "解析成功");
                    processData(response);
                }
            });
        }

        View view = View.inflate(getActivity(), getLayoutId(), null);
        ButterKnife.inject(this, view);
        initTitle();
        initData();
        initListener();
        return view;
    }

    public void processData(String response) {

    }

    public abstract String getUrl();

    protected abstract int getLayoutId();

    public abstract void initListener();

    public abstract void initData();

    public void initTitle() {

    }

    public void showToash(String s) {
        Toast.makeText(context, "" + s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
