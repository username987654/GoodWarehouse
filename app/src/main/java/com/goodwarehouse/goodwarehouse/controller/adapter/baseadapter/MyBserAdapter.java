package com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter;

import android.content.Context;
import android.os.Process;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;

import java.util.List;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public abstract class MyBserAdapter<T> extends BaseAdapter {
    private final Context context;
    private final List<T> datas;

    public MyBserAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseViewHolder baseViewHolder = null;
        if (view == null) {
            baseViewHolder = getHolder();
        } else {
            baseViewHolder = (BaseViewHolder) view.getTag();
        }
        baseViewHolder.setData(datas.get(i));
        return baseViewHolder.getView();
    }

    public abstract BaseViewHolder getHolder();
}
