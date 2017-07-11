package com.goodwarehouse.goodwarehouse.controller.fragment.commodityfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-11.
 */

public class CommodityDetailsFragment extends BaseFragment {
    @InjectView(R.id.brand_narrate_tv)
    TextView brandNarrateTv;
    @InjectView(R.id.after_sale)
    Button afterSale;

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_commodity_details;
    }

    @Override
    public void initListener() {
        afterSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "售后服务", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        String s = getArguments().getString("good_guide");
        brandNarrateTv.setText(s);
    }

}
