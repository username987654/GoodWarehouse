package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.BrandDetailsBean;
import com.goodwarehouse.goodwarehouse.bean.ShopBrandBean;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.BrandFragment;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import butterknife.InjectView;

public class BrandDetailsActivity extends BaseActivity {


    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.brand_iocn)
    ImageView brandIocn;
    @InjectView(R.id.brand_name)
    TextView brandName;
    @InjectView(R.id.shop_brandinfo_fm)
    FrameLayout shopBrandinfoFm;
    @InjectView(R.id.activity_brand_info)
    LinearLayout activityBrandInfo;
    @InjectView(R.id.brand_narrate)
    TextView brandNarrate;
    @InjectView(R.id.brand_product)
    TextView brandProduct;
    private BrandDetailsBean brandDetailsBean;
    private Intent intent;
    private ShopBrandBean.DataBean.ItemsBean itemBean;

    @Override
    public void initTitle() {
        super.initTitle();
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("品牌产品");
    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        itemBean = (ShopBrandBean.DataBean.ItemsBean) getIntent().getSerializableExtra(BrandFragment.BRANDBEAN);
        String brandInfoUrl = NetRequestSite.BRAND_INFO_FROEPART_URL + itemBean.getBrand_id() + NetRequestSite.BRAND_INFO_END_URL;
        HttpUtils.requestImage(BrandDetailsActivity.this, itemBean.getBrand_logo(), brandIocn);
        brandName.setText(itemBean.getBrand_name());
        HttpUtils.HttpNet(brandInfoUrl, new HttpUtils.onNetRequestContent() {
            @Override
            public void onError(String content) {
                Log.e("BramdInfoActivity", content);
            }

            @Override
            public void onResponse(String response) {
                processedData(response);
            }
        });

    }

    private void processedData(String json) {
        brandDetailsBean = JSON.parseObject(json, BrandDetailsBean.class);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_details;
    }
}
