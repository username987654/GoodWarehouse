package com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ShopSpecialBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.SpecialAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class SpecialFragment extends BaseFragment {
    @InjectView(R.id.shop_special_lv)
    ListView shopSpecialLv;
    private SpecialAdapter specialAdapter;
    private List<ShopSpecialBean.DataBean.ItemsBean> items;

    @Override
    public void initListener() {
        shopSpecialLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "" + items.get(i), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public String getUrl() {
        return NetRequestSite.SPECIAL_URL;
    }

    @Override
    public void processData(String response) {
        ShopSpecialBean shopSpecialBean = JSON.parseObject(response, ShopSpecialBean.class);
        items = shopSpecialBean.getData().getItems();
        specialAdapter = new SpecialAdapter(context, items);
        shopSpecialLv.setAdapter(specialAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_special;
    }

}
