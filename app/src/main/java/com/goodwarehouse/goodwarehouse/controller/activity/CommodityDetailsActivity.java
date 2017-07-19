package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.BreanInfoBean;
import com.goodwarehouse.goodwarehouse.bean.CommodityCartData;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.MyViewPagerAdapetr;
import com.goodwarehouse.goodwarehouse.controller.fragment.commodityfragment.BuyerReadingFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.commodityfragment.CommodityDetailsFragment;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-08.
 */

public class CommodityDetailsActivity extends BaseActivity implements View.OnClickListener {
    @InjectView(R.id.commodity_breand_name)
    TextView commodityBreandName;
    @InjectView(R.id.commdoity_details)
    TextView commdoityDetails;
    @InjectView(R.id.commodity_effect)
    TextView commodityEffect;
    @InjectView(R.id.commdoity_like_count)
    TextView commdoityLikeCount;
    @InjectView(R.id.commodity_back)
    ImageView commodityBack;
    @InjectView(R.id.commodity_now_picre)
    TextView commodityNowPicre;
    @InjectView(R.id.commodity_past_picre)
    TextView commodityPastPicre;
    @InjectView(R.id.commodity_statusbar)
    ImageView commodityStatusbar;
    @InjectView(R.id.commodity_switch_size)
    LinearLayout commoditySwitchSize;
    @InjectView(R.id.commodity_cart)
    ImageView commodityCart;
    @InjectView(R.id.commodity_service)
    ImageView commodityService;
    @InjectView(R.id.commodity_release_cart)
    TextView commodityReleaseCart;
    @InjectView(R.id.commodity_purchase)
    TextView commodityPurchase;

    /*
    * commodityLike : 喜欢心图
    * commoityShare : 分享
    * */
    @InjectView(R.id.commodity_like)
    ImageView commodityLike;
    @InjectView(R.id.commoity_share)
    ImageView commoityShare;


    /*
    * brandNarrate : 购物详情
    * brandProduct : 购物须知
    * */
    @InjectView(R.id.brand_narrate)
    TextView brandNarrate;
    @InjectView(R.id.brand_product)
    TextView brandProduct;
    /*
    * 购物车商品数量
    * */
    @InjectView(R.id.commodity_count)
    TextView commodityCount;


    @InjectView(R.id.commodity_vp)
    ViewPager commodityVp;
    @InjectView(R.id.brand_head)
    ImageView brandHead;
    @InjectView(R.id.brean_name)
    TextView breanName;
    @InjectView(R.id.brean_info)
    LinearLayout breanInfo;
    @InjectView(R.id.linear_switch)
    LinearLayout linearSwitch;
    @InjectView(R.id.ll_indicatepoint)
    LinearLayout llIndicatepoint;
    private BreanInfoBean breanInfoBean;
    private CommodityDetailsBean.DataBean.ItemsBean.GoodGuideBean good_guide;
    private List<CommodityDetailsBean.DataBean.ItemsBean.GoodsInfoBean> goods_info;
    private CommodityDetailsBean.DataBean.ItemsBean items;
    Bundle bundle = new Bundle();
    private List<String> imagesItems;
    private int recordPosition = 0;
    private CommodityDetailsBean commodityDetailsBean;
    private String name;
    private String image;
    private String goods_name;
    private String price;
    private String prices;
    public String discount_price;
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> skuinfobean;
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean> sku_inv;

    @Override
    public void initTitle() {
        defaultSwitchover(brandNarrate);
        brandNarrate.setText("商品详情");
        brandProduct.setText("购物须知");
    }

    @Override
    public void initListener() {
        commoditySwitchSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CommodityDetailsActivity.this, "请选择", Toast.LENGTH_SHORT).show();
            }
        });
        commodityBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        commodityCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(CommodityDetailsActivity.this, "购物车", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CommodityDetailsActivity.this, CartActivity.class));
            }
        });
        commodityService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CommodityDetailsActivity.this, "客服", Toast.LENGTH_SHORT).show();
            }
        });
        commodityReleaseCart.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
//                Toast.makeText(CommodityDetailsActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
                name = commodityDetailsBean.getData().getItems().getOwner_name();
                image = commodityDetailsBean.getData().getItems().getGoods_image();
                goods_name = commodityDetailsBean.getData().getItems().getGoods_name();
                discount_price = commodityDetailsBean.getData().getItems().getDiscount_price();
                price = commodityDetailsBean.getData().getItems().getPrice();
                prices = TextUtils.isEmpty(discount_price) == true ? price : discount_price;
                skuinfobean = commodityDetailsBean.getData().getItems().getSku_info();
                sku_inv = commodityDetailsBean.getData().getItems().getSku_inv();
                CommodityCartData cartData = new CommodityCartData(name, image, goods_name, prices, skuinfobean, sku_inv);
                Intent intent = new Intent(CommodityDetailsActivity.this, JoinCartActivity.class);
                intent.putExtra("CARTDATA", cartData);
                startActivity(intent);
            }
        });
        commodityPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(CommodityDetailsActivity.this, "直接加入购物车", Toast.LENGTH_SHORT).show();
                name = commodityDetailsBean.getData().getItems().getOwner_name();
                image = commodityDetailsBean.getData().getItems().getGoods_image();
                goods_name = commodityDetailsBean.getData().getItems().getGoods_name();
                discount_price = commodityDetailsBean.getData().getItems().getDiscount_price();
                price = commodityDetailsBean.getData().getItems().getPrice();
                prices = TextUtils.isEmpty(discount_price) == true ? price : discount_price;
                skuinfobean = commodityDetailsBean.getData().getItems().getSku_info();
                sku_inv = commodityDetailsBean.getData().getItems().getSku_inv();
                CommodityCartData cartData = new CommodityCartData(name, image, goods_name, prices, skuinfobean, sku_inv);
                Intent intent = new Intent(CommodityDetailsActivity.this, JoinCartActivity.class);
                intent.putExtra("CARTDATA", cartData);
                startActivity(intent);

            }
        });

        breanInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommodityDetailsActivity.this, BrandDetailsActivity.class);
                intent.putExtra("brand_bran", breanInfoBean);
                startActivity(intent);
            }
        });

        commodityLike.setOnClickListener(new View.OnClickListener() {
            boolean i = true;

            @Override
            public void onClick(View view) {
                if (i) {
                    commodityLike.setImageResource(R.drawable.ic_my_wish);
                    i = false;
                } else {
                    commodityLike.setImageResource(R.drawable.like_not_big);
                    i = true;
                }
            }
        });
        brandNarrate.setOnClickListener(this);
        brandProduct.setOnClickListener(this);
        commodityVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                llIndicatepoint.getChildAt(recordPosition).setEnabled(true);
                llIndicatepoint.getChildAt(position).setEnabled(false);
                recordPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        String goods_id = getIntent().getStringExtra(GOODSID);
        String url = NetRequestSite.COMMODITY_DERAILS_FROEPART_URL + goods_id + NetRequestSite.COMMODITY_DERAILS_END_URL;
        Log.e("SSSSSSS", url);
        HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
            @Override
            public void onError(String content) {
                Log.e("commifutyDetails", content);
            }

            @Override
            public void onResponse(String response) {
                Log.e("commifutyDetails", "联网成功");
                processData(response);

            }
        });
    }

    private void processData(String response) {
        commodityDetailsBean = JSON.parseObject(response, CommodityDetailsBean.class);
        String brand_name = commodityDetailsBean.getData().getItems().getBrand_info().getBrand_name();
        commodityBreandName.setText(brand_name);
        String goods_name = commodityDetailsBean.getData().getItems().getGoods_name();
        commdoityDetails.setText(goods_name);
        String promotion_note = commodityDetailsBean.getData().getItems().getPromotion_note();
        commodityEffect.setText(promotion_note);
        String like_count = commodityDetailsBean.getData().getItems().getLike_count();
        commdoityLikeCount.setText(like_count);
        String discount_price = commodityDetailsBean.getData().getItems().getDiscount_price();
        String price = commodityDetailsBean.getData().getItems().getPrice();
        if (!TextUtils.isEmpty(discount_price)) {
            commodityNowPicre.setText("￥" + discount_price);
            commodityPastPicre.setText("￥" + price);
            commodityPastPicre.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        } else {
            commodityNowPicre.setText("￥" + price);
            commodityPastPicre.setVisibility(View.GONE);
        }

        String promotion_imgurl = commodityDetailsBean.getData().getItems().getPromotion_imgurl();
        if (!TextUtils.isEmpty(promotion_imgurl)) {
            HttpUtils.loadImage(this, promotion_imgurl, commodityStatusbar);
        } else {
            commodityStatusbar.setVisibility(View.GONE);
        }
        imagesItems = commodityDetailsBean.getData().getItems().getImages_item();
        MyViewPagerAdapetr pagerAdapetr = new MyViewPagerAdapetr(CommodityDetailsActivity.this, imagesItems);
        commodityVp.setAdapter(pagerAdapetr);

        String headimg = commodityDetailsBean.getData().getItems().getHeadimg();
        HttpUtils.loadImage(this, headimg, brandHead);
        String owner_name = commodityDetailsBean.getData().getItems().getOwner_name();
        breanName.setText(owner_name);
        String brand_id = commodityDetailsBean.getData().getItems().getBrand_info().getBrand_id();
        String brand_logo = commodityDetailsBean.getData().getItems().getBrand_info().getBrand_logo();
        breanInfoBean = new BreanInfoBean(Integer.parseInt(brand_id), brand_name, brand_logo);
        good_guide = commodityDetailsBean.getData().getItems().getGood_guide();
        items = commodityDetailsBean.getData().getItems();
        BuyerReadingFragment buyerReading = new BuyerReadingFragment();
        bundle.putSerializable("items", items);
        buyerReading.setArguments(bundle);
        showFragment(buyerReading);
        setPoint();
    }

    private void setPoint() {
        for (int i = 0; i < imagesItems.size(); i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
            imageView.setImageResource(R.drawable.point_selector);
            if (i == 0) {
                imageView.setEnabled(false);
            } else {
                imageView.setEnabled(true);
                params.leftMargin = 10;
            }
            llIndicatepoint.addView(imageView);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

        /*
    * brandNarrate : 购物详情
    * brandProduct : 购物须知
    * */

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.brand_product:
                defaultSwitchover(brandProduct);
                CommodityDetailsFragment commodityDetails = new CommodityDetailsFragment();
                bundle.putString("good_guide", good_guide.getContent());
                commodityDetails.setArguments(bundle);
                showFragment(commodityDetails);
                break;
            case R.id.brand_narrate:
                BuyerReadingFragment buyerReading = new BuyerReadingFragment();
                bundle.putSerializable("items", items);
                buyerReading.setArguments(bundle);
                showFragment(buyerReading);
                defaultSwitchover(brandNarrate);
                break;
        }
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


    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.commodity_details_fragmemt, fragment).commit();
    }
}
