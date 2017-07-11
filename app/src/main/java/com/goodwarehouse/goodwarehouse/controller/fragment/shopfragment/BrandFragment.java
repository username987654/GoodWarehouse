package com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.BreanInfoBean;
import com.goodwarehouse.goodwarehouse.bean.ShopBrandBean;
import com.goodwarehouse.goodwarehouse.controller.activity.BrandDetailsActivity;
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
    public static final String BRANDBEAN = "brand_bran";


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        shopBrandLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, BrandDetailsActivity.class);
                int brand_id = items.get(i).getBrand_id();
                String brand_name = items.get(i).getBrand_name();
                String brand_logo = items.get(i).getBrand_logo();
                BreanInfoBean breanInfoBean = new BreanInfoBean(brand_id, brand_name, brand_logo);
                intent.putExtra(BRANDBEAN, breanInfoBean);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public void processData(String response) {
        ShopBrandBean shopBrandBean = JSON.parseObject(response, ShopBrandBean.class);
        List<ShopBrandBean.DataBean.ItemsBean> items = shopBrandBean.getData().getItems();
        this.items = shopBrandBean.getData().getItems();
        BrandAdapter brandAdapter = new BrandAdapter(context, this.items);
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
