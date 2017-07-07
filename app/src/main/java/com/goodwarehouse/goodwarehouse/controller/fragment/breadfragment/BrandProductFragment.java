package com.goodwarehouse.goodwarehouse.controller.fragment.breadfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.BrandDetailsBean;
import com.goodwarehouse.goodwarehouse.controller.activity.BrandDetailsActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.BrandProductAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-07.
 */

public class BrandProductFragment extends BaseFragment {
    @InjectView(R.id.shop_type_gv)
    GridView shopTypeGv;
    private String path;
    private List<BrandDetailsBean.DataBean.ItemsBean> items;
    private BrandProductAdapter brandProductAdapter;

    @Override
    public String getUrl() {
        return path;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_brand_product;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        BrandDetailsActivity detailsActivity = (BrandDetailsActivity) getActivity();
        path = NetRequestSite.BRAND_INFO_FROEPART_URL
                + detailsActivity.getItembean().getBrand_id()
                + NetRequestSite.BRAND_INFO_END_URL;

    }

    @Override
    public void processData(String response) {
        super.processData(response);
        BrandDetailsBean brandDetailsBean = JSON.parseObject(response, BrandDetailsBean.class);
        items = brandDetailsBean.getData().getItems();
        brandProductAdapter = new BrandProductAdapter(context, items);
        shopTypeGv.setAdapter(brandProductAdapter);
    }
}
