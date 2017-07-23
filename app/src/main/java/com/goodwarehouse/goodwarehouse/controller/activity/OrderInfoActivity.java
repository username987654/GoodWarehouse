package com.goodwarehouse.goodwarehouse.controller.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.CommodityInfo;
import com.goodwarehouse.goodwarehouse.controller.adapter.OrderListAdapter;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.view.MyListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class OrderInfoActivity extends BaseActivity {

    @InjectView(R.id.title_switcher)
    TextSwitcher titleSwitcher;
    @InjectView(R.id.title_seek)
    ImageView titleSeek;
    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.magazines_classify)
    ImageView magazinesClassify;
    @InjectView(R.id.shop_cart)
    ImageView shopCart;
    @InjectView(R.id.magazines_cart)
    ImageView magazinesCart;
    @InjectView(R.id.web_like)
    ImageView webLike;
    @InjectView(R.id.web_share)
    ImageView webShare;
    @InjectView(R.id.cart_compile)
    TextView cartCompile;
    @InjectView(R.id.rl_title)
    RelativeLayout rlTitle;
    @InjectView(R.id.order_user_name)
    TextView orderUserName;
    @InjectView(R.id.order_user_phone)
    TextView orderUserPhone;
    @InjectView(R.id.order_user_address)
    TextView orderUserAddress;
    @InjectView(R.id.order_user_back)
    ImageView orderUserBack;
    @InjectView(R.id.order_comm_nimage1)
    ImageView orderCommNimage;
    @InjectView(R.id.order_comm_name1)
    TextView orderCommName;
    @InjectView(R.id.order_comm_color1)
    TextView orderCommColor;
    @InjectView(R.id.order_comm_standard1)
    TextView orderCommStandard;
    @InjectView(R.id.order_comm_price1)
    TextView orderCommPrice;
    @InjectView(R.id.order_comm_count1)
    TextView orderCommCount;
    @InjectView(R.id.default_comm)
    LinearLayout defaultComm;
    @InjectView(R.id.show_hide_comm)
    TextView showHideComm;
    @InjectView(R.id.list_hide_comm)
    MyListView listHideComm;
    @InjectView(R.id.order_popup_coupons)
    ImageView orderPopupCoupons;
    @InjectView(R.id.order_coupons_edit)
    EditText orderCouponsEdit;
    @InjectView(R.id.order_coupons_confirm)
    TextView orderCouponsConfirm;
    @InjectView(R.id.order_coupons)
    LinearLayout orderCoupons;
    @InjectView(R.id.order_popup_remarks)
    ImageView orderPopupRemarks;
    @InjectView(R.id.order_remarks_edit)
    EditText orderRemarksEdit;
    @InjectView(R.id.order_remarks)
    LinearLayout orderRemarks;
    @InjectView(R.id.order_price)
    TextView orderPrice;
    @InjectView(R.id.order_discount)
    TextView orderDiscount;
    @InjectView(R.id.order_popup_pay)
    ImageView orderPopupPay;
    @InjectView(R.id.weixinpay)
    CheckBox weixinpay;
    @InjectView(R.id.alipay)
    CheckBox alipay;
    @InjectView(R.id.pay_mode)
    LinearLayout payMode;
    @InjectView(R.id.order_count_price)
    TextView orderCountPrice;
    @InjectView(R.id.oider_save_price)
    TextView oiderSavePrice;
    @InjectView(R.id.affirm_pay)
    TextView affirmPay;
    @InjectView(R.id.activity_order_info)
    RelativeLayout activityOrderInfo;
    private boolean isPopup = false;
    private boolean isPopupRemarks = false;


    @Override
    public void initListener() {
        affirmPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weixinpay.isChecked()){
                    Toast.makeText(OrderInfoActivity.this, "微信", Toast.LENGTH_SHORT).show();
                }
                if(alipay.isChecked()){
                    Toast.makeText(OrderInfoActivity.this, "支付宝", Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderPopupCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPopup) {
                    orderCoupons.setVisibility(View.VISIBLE);
                    isPopup = true;
                } else {
                    orderCoupons.setVisibility(View.GONE);
                    isPopup = false;
                }
            }
        });

        orderPopupRemarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isPopupRemarks) {
                    orderRemarks.setVisibility(View.VISIBLE);
                    isPopupRemarks = true;
                } else {
                    orderRemarks.setVisibility(View.GONE);
                    isPopupRemarks = false;
                }
            }
        });
        orderPopupPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payMode.setVisibility(View.VISIBLE);
                orderPopupPay.setVisibility(View.GONE);


            }
        });
        weixinpay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    return;
                }
                alipay.setChecked(!b);
            }
        });
        alipay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == false) {
                    return;
                }
                weixinpay.setChecked(!b);
            }
        });
        showHideComm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHideComm.setVisibility(View.GONE);
                defaultComm.setVisibility(View.GONE);
                listHideComm.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void initData() {
        double originalPriceSun = 0;
        double discountPriceSun = 0;
        List<CommodityInfo> commInfos = (List<CommodityInfo>) getIntent().getSerializableExtra(COMMINFO);

        CommodityInfo commodityInfo = commInfos.get(0);
        HttpUtils.loadImage(this, commodityInfo.getCommImage(), orderCommNimage);
        orderCommName.setText(commodityInfo.getCommName());
        orderCommColor.setText(commodityInfo.getCommColocr());
        if (TextUtils.isEmpty(commodityInfo.getCommStandard())) {
            orderCommStandard.setVisibility(View.GONE);
        } else {
            orderCommStandard.setText(commodityInfo.getCommStandard());
        }
        String price = TextUtils.isEmpty(commodityInfo.getCommOriginalPrice()) ? commodityInfo.getCommPrice() : commodityInfo.getCommOriginalPrice();
        orderCommPrice.setText("￥" + price);
        orderCommCount.setText("x" + commodityInfo.getCommCount());

        originalPriceSun += Double.parseDouble(commodityInfo.getCommPrice()) * commodityInfo.getCommCount();
        discountPriceSun += Double.parseDouble(commodityInfo.getCommDiscount()) * commodityInfo.getCommCount();
        orderPrice.setText("￥" + originalPriceSun);
        orderDiscount.setText("-￥" + discountPriceSun);
        orderCountPrice.setText("￥" + (originalPriceSun - discountPriceSun));
        oiderSavePrice.setText("-￥" + discountPriceSun);
        if (commInfos.size() > 1) {
            OrderListAdapter orderAdapter = new OrderListAdapter(this, commInfos);
            listHideComm.setAdapter(orderAdapter);
            for (int i = 0; i < commInfos.size(); i++) {
                CommodityInfo commodityInfo1 = commInfos.get(i);
                originalPriceSun += Double.parseDouble(commodityInfo1.getCommPrice()) * commodityInfo.getCommCount();
                discountPriceSun += Double.parseDouble(commodityInfo1.getCommDiscount()) * commodityInfo.getCommCount();
            }
            orderPrice.setText("￥" + originalPriceSun);
            orderDiscount.setText("-￥" + discountPriceSun);
            orderCountPrice.setText("￥" + (originalPriceSun - discountPriceSun));
            oiderSavePrice.setText("-￥" + discountPriceSun);
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_info;
    }

}
