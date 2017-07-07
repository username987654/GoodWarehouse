package com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ShopBrandBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.BrandAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class BrandFragment extends BaseFragment {
    @InjectView(R.id.shop_brand_lv)
    ListView shopBrandLv;
    private List<ShopBrandBean.DataBean.ItemsBean> items;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        shopBrandLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(context, "" + items.get(i).getBrand_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void processData(String response) {
        ShopBrandBean shopBrandBean = JSON.parseObject(response, ShopBrandBean.class);
        items = shopBrandBean.getData().getItems();
        BrandAdapter brandAdapter = new BrandAdapter(context, items);
        shopBrandLv.setAdapter(brandAdapter);
    }

    @Override
    public String getUrl() {
        return NetRequestSite.BRAND_URL;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_brand;
    }

}
