package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.bean.BreanInfoBean;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.BrandDetailsBean;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.bean.ShopBrandBean;
import com.goodwarehouse.goodwarehouse.controller.fragment.breadfragment.BrandNarrateFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.breadfragment.BrandProductFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.BrandFragment;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

public class BrandDetailsActivity extends BaseActivity implements View.OnClickListener {


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
    private BrandProductFragment brandProductFragment;
    private BrandNarrateFragment brandNarrateFragment;
    private List<BrandDetailsBean.DataBean.ItemsBean> items;
    private BreanInfoBean breanInfoBean;

    @Override
    public void initTitle() {
        super.initTitle();
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText("品牌产品");
        defaultSwitchover(brandProduct);
    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        brandNarrate.setOnClickListener(this);
        brandProduct.setOnClickListener(this);

    }

    @Override
    public void initData() {
        breanInfoBean = (BreanInfoBean) getIntent().getSerializableExtra(BrandFragment.BRANDBEAN);
        HttpUtils.loadCricleImage(BrandDetailsActivity.this, breanInfoBean.getImageUrl(), brandIocn);
        brandName.setText(breanInfoBean.getName());
        setFragment();
    }

    private void setFragment() {
        brandProductFragment = new BrandProductFragment();
        brandNarrateFragment = new BrandNarrateFragment();
        showFragment(brandProductFragment);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_details;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.brand_narrate:
                defaultSwitchover(brandNarrate);
                showFragment(brandNarrateFragment);
                break;
            case R.id.brand_product:
                defaultSwitchover(brandProduct);
                showFragment(brandProductFragment);
                break;
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.shop_brandinfo_fm, fragment).commit();
    }

    private void defaultSwitchover(TextView tv) {
        int color = getResources().getColor(R.color.defaultSwitchover);
        brandNarrate.setTextColor(Color.WHITE);
        brandNarrate.setBackgroundColor(color);
        brandProduct.setTextColor(Color.WHITE);
        brandProduct.setBackgroundColor(color);
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundColor(Color.WHITE);
    }

    public BreanInfoBean getItembean() {
        return breanInfoBean;
    }
}
