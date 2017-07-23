package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.CommodityCartData;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.bean.CommodityInfo;
import com.goodwarehouse.goodwarehouse.model.dao.CommodityDAO;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.view.AddSubView;
import com.goodwarehouse.goodwarehouse.view.FlowRadioGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class JoinCartActivity extends BaseActivity implements View.OnClickListener {


    @InjectView(R.id.commodity_cart_back)
    ImageButton commodityCartBack;
    @InjectView(R.id.commodity_cart_picture)
    ImageView commodityCartPicture;
    @InjectView(R.id.commodity_join_name)
    TextView commodityJoinName;
    @InjectView(R.id.commodity_join_introduce)
    TextView commodityJoinIntroduce;
    @InjectView(R.id.commodity_join_price)
    TextView commodityJoinPrice;
    @InjectView(R.id.kind_radiogroup)
    FlowRadioGroup kindRadiogroup;
    @InjectView(R.id.linear_kind)
    LinearLayout linearKind;
    @InjectView(R.id.kind_radiogroup2)
    FlowRadioGroup kindRadiogroup2;
    @InjectView(R.id.linear_kind2)
    LinearLayout linearKind2;
    @InjectView(R.id.commodiity_num)
    AddSubView commodiityNum;
    @InjectView(R.id.btn_comfort)
    Button btnComfort;
    @InjectView(R.id.activity_join_cart)
    RelativeLayout activityJoinCart;
    @InjectView(R.id.cart_text1)
    TextView cartText1;
    @InjectView(R.id.cart_text2)
    TextView cartText2;
    @InjectView(R.id.btn_join_cart)
    Button btnJoinCart;
    @InjectView(R.id.btn_purchase)
    Button btnPurchase;
    @InjectView(R.id.cart_is_join_cart)
    LinearLayout cartIsJoinCart;
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> skuInfos;
    private CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean;
    private List<CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean> skuInvBeen;
    private CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean1;
    private CommodityDetailsBean commodityBean;

    private String goods_id;
    private String goods_name;
    private String brand_name;
    private String price;
    private String discount_price;
    private double discount = 0.00;
    private CommodityDAO dao;
    private String attr_name;
    private String attrname;
    private String img_path;
    private String amount1;
    private String amount2;
    private int count = 1;


    @Override
    public void initListener() {
        commodiityNum.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int number) {
                count = number;
            }
        });
        String isAdd = getIntent().getStringExtra(ISADD);
        if (isAdd.equals(JOINCART)) {
            cartIsJoinCart.setVisibility(View.GONE);
            btnComfort.setVisibility(View.VISIBLE);
            btnComfort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(JoinCartActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
                    CommodityInfo connInfo = setCommodityInfo();
                    dao.addCommodity(connInfo);
                    Toast.makeText(JoinCartActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        } else if (isAdd.equals(PURCHASE)) {
            cartIsJoinCart.setVisibility(View.GONE);
            btnComfort.setVisibility(View.VISIBLE);
            btnComfort.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(JoinCartActivity.this, "直接购买", Toast.LENGTH_SHORT).show();
                    CommodityInfo connInfo = setCommodityInfo();
                    List<CommodityInfo> commInfos = new ArrayList<CommodityInfo>();
                    commInfos.add(connInfo);
                    Intent intent = new Intent(JoinCartActivity.this, OrderInfoActivity.class);
                    intent.putExtra(COMMINFO, (Serializable) commInfos);
                    startActivity(intent);
                }
            });
        } else if (isAdd.equals(SELECT)) {
            btnComfort.setVisibility(View.GONE);
            cartIsJoinCart.setVisibility(View.VISIBLE);
            btnJoinCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(JoinCartActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
                    CommodityInfo connInfo = setCommodityInfo();

                    dao.addCommodity(connInfo);
                    Toast.makeText(JoinCartActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
            btnPurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(JoinCartActivity.this, "直接购买", Toast.LENGTH_SHORT).show();
                    CommodityInfo connInfo = setCommodityInfo();
                    Intent intent = new Intent(JoinCartActivity.this, OrderInfoActivity.class);
                    intent.putExtra(COMMINFO, connInfo);
                    startActivity(intent);
                }
            });
        }
        commodityCartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        kindRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                setDefaultChecked1(i);
            }
        });
        kindRadiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (skuInfos.size() > 1) {
                    setDefaultChecked2(i);
                }

            }
        });
    }

    @NonNull
    private CommodityInfo setCommodityInfo() {
        //商品ID
        goods_id = commodityBean.getData().getItems().getGoods_id();
        //商品名
        goods_name = commodityBean.getData().getItems().getGoods_name();
        //商品品牌名
        brand_name = commodityBean.getData().getItems().getBrand_info().getBrand_name();
        String goods_image = commodityBean.getData().getItems().getGoods_image();
        if (!TextUtils.isEmpty(discount_price)) {
            //商品折扣
            Log.e("TAG", discount_price + "||" + price);
            discount = Double.parseDouble(price) - Double.parseDouble(discount_price);
        }

        String image = TextUtils.isEmpty(img_path) ? goods_image : img_path;
        String amount = TextUtils.isEmpty(amount2) ? amount1 : amount2;
        commodiityNum.setMaxValue(Integer.parseInt(amount));
        return new CommodityInfo(goods_id,
                brand_name,
                price,
                discount_price,
                goods_name,
                image,
                "" + discount,
                count,
                amount,
                attr_name,
                attrname);
    }

    private void setDefaultChecked2(int i) {
        if (skuInfos.size() > 1) {
            CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean attrListBean = skuInfoBean1.getAttrList().get(i);
            String attr_id = attrListBean.getAttr_id();
            attrname = skuInfoBean1.getType_name() + ":" + attrListBean.getAttr_name();

            for (int j = 0; j < skuInvBeen.size(); j++) {
                String attr_keys = skuInvBeen.get(j).getAttr_keys();
                String[] split = attr_keys.split(",");
                for (int k = 0; k < split.length; k++) {
                    if (split[k].equals(attr_id)) {
                        price = skuInvBeen.get(j).getPrice();
                        amount2 = skuInvBeen.get(j).getAmount();
                        commodityJoinPrice.setText("￥" + price);
                        return;
                    }

                }
            }
        }
    }

    private void setDefaultChecked1(int i) {
        CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean attrListBean = skuInfoBean.getAttrList().get(i);
        img_path = attrListBean.getImg_path();

        attr_name = skuInfoBean.getType_name() + ":" + attrListBean.getAttr_name();
        if (!TextUtils.isEmpty(img_path)) {
            HttpUtils.loadImage(JoinCartActivity.this, img_path, commodityCartPicture);
        }

        String attr_id = attrListBean.getAttr_id();
        CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean skuInvBean = skuInvBeen.get(i);
        String attr_keys = skuInvBean.getAttr_keys();
        discount_price = skuInvBean.getDiscount_price();
        price = skuInvBeen.get(i).getPrice();
        amount1 = skuInvBeen.get(i).getAmount();
        if (attr_id.equals(attr_keys)) {
            if (!TextUtils.isEmpty(discount_price)) {
                commodityJoinPrice.setText("￥" + discount_price);
            } else {

                commodityJoinPrice.setText("￥" + this.price);
            }
        }
    }

    @Override
    public void initData() {
        dao = new CommodityDAO(JoinCartActivity.this);
        commodityBean = (CommodityDetailsBean) getIntent().getSerializableExtra("COMMODITY");
        CommodityCartData cartData = (CommodityCartData) getIntent().getSerializableExtra("CARTDATA");
        skuInvBeen = cartData.getSkuInvBeen();
        HttpUtils.loadImage(this, cartData.getImage(), commodityCartPicture);
        commodityJoinName.setText(cartData.getName());
        commodityJoinIntroduce.setText(cartData.getIntroduce());
        commodityJoinPrice.setText("￥" + cartData.getPrice());
        skuInfos = cartData.getSkuInfoBean();
        skuInfoBean = skuInfos.get(0);
        cartText1.setText(skuInfoBean.getType_name());
        List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfoBean.getAttrList();
        for (int i = 0; i < skuInfoBean.getAttrList().size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(attrList.get(i).getAttr_name());
            radioButton.setTextColor(Color.WHITE);
            radioButton.setTextSize(15);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            radioButton.setPadding(20, 10, 20, 10);
            layoutParams.rightMargin = 20;
            layoutParams.bottomMargin = 20;
            radioButton.setBackgroundResource(R.drawable.cart_check_all_bg);
            radioButton.setButtonDrawable(android.R.color.transparent);
            radioButton.setId(i);
            kindRadiogroup.addView(radioButton, layoutParams);
        }
        if (skuInfos.size() > 1) {
            linearKind2.setVisibility(View.VISIBLE);
            skuInfoBean1 = skuInfos.get(1);
            cartText2.setText(skuInfoBean1.getType_name());
            List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList1 = skuInfoBean1.getAttrList();
            for (int i = 0; i < skuInfoBean1.getAttrList().size(); i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(attrList1.get(i).getAttr_name());
                radioButton.setTextColor(Color.WHITE);
                radioButton.setTextSize(15);
                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.rightMargin = 20;
                layoutParams.bottomMargin = 20;
                radioButton.setPadding(10, 10, 10, 10);
                radioButton.setId(i);
                radioButton.setBackgroundResource(R.drawable.cart_check_all_bg);
                radioButton.setButtonDrawable(android.R.color.transparent);
                kindRadiogroup2.addView(radioButton, layoutParams);
            }
        }
        kindRadiogroup.check(0);
        setDefaultChecked1(0);
        kindRadiogroup2.check(0);
        setDefaultChecked2(0);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_join_cart;
    }


    @Override
    public void onClick(View view) {

    }

}

