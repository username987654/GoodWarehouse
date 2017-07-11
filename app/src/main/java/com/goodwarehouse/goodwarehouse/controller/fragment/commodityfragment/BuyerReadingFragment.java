package com.goodwarehouse.goodwarehouse.controller.fragment.commodityfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.CommodityDetailsAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-11.
 */

public class BuyerReadingFragment extends BaseFragment {
    @InjectView(R.id.commodity_details_rv)
    RecyclerView commodityDetailsRv;
    @InjectView(R.id.brand_introduce)
    TextView brandIntroduce;
    @InjectView(R.id.recommend)
    TextView recommend;
    @InjectView(R.id.ll_recommend)
    LinearLayout llRecommend;


    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commodity_details;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        CommodityDetailsBean.DataBean.ItemsBean items = (CommodityDetailsBean.DataBean.ItemsBean) getArguments().getSerializable("items");
        String brand_desc = items.getBrand_info().getBrand_desc();
        brandIntroduce.setText(brand_desc);
        String goods_desc = items.getGoods_desc();
        if (goods_desc.length() > 1) {
            recommend.setText(goods_desc);
        } else {
            llRecommend.setVisibility(View.GONE);
            CommodityDetailsAdapter adapter = new CommodityDetailsAdapter(context, items.getGoods_info());
            commodityDetailsRv.setAdapter(adapter);
            commodityDetailsRv.setLayoutManager(new LinearLayoutManager(context));
        }
    }

}
