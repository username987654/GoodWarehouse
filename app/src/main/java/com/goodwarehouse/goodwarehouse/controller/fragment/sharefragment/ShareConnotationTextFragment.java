package com.goodwarehouse.goodwarehouse.controller.fragment.sharefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ConnotationTextBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.ConnTextAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-13.
 */

public class ShareConnotationTextFragment extends BaseFragment {

    @InjectView(R.id.connotation_text)
    ListView connotationText;

    @Override
    public String getUrl() {
        return NetRequestSite.CONNTATION_TEXT_URL;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_connotation_text;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        setListViewHeight(connotationText);
    }

    @Override
    public void processData(String response) {
        ConnotationTextBean connotationTextBean = JSON.parseObject(response, ConnotationTextBean.class);
        List<ConnotationTextBean.ListBean> list = connotationTextBean.getList();
        ConnTextAdapter connTextAdapter = new ConnTextAdapter(context, list);
        connotationText.setAdapter(connTextAdapter);
    }

    public void setListViewHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHerght = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listViewItem = adapter.getView(i, null, listView);
            listViewItem.measure(0, 0);
            totalHerght += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHerght + (listView.getDividerHeight() * adapter.getCount());
        listView.setLayoutParams(params);

    }

}
