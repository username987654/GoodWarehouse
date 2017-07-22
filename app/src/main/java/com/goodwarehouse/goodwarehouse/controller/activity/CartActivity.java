package com.goodwarehouse.goodwarehouse.controller.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.CommodityInfo;
import com.goodwarehouse.goodwarehouse.controller.adapter.CartAdapter;
import com.goodwarehouse.goodwarehouse.model.dao.CommodityDAO;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CartActivity extends BaseActivity {

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

    @InjectView(R.id.cart_reach_minus)
    TextView cartReachMinus;
    @InjectView(R.id.cart_discount)
    TextView cartDiscount;
    @InjectView(R.id.cart_total_prices)
    TextView cartTotalPrices;
    @InjectView(R.id.settle_accounts)
    Button settleAccounts;
    @InjectView(R.id.activity_cart)
    RelativeLayout activityCart;
    @InjectView(R.id.cart_commodity)
    RecyclerView cartCommodity;
    @InjectView(R.id.All_selected_compile)
    CheckBox AllSelectedCompile;
    @InjectView(R.id.All_selected_accomplish)
    CheckBox AllSelectedAccomplish;

    private CommodityDAO dao;

    public static final int ACCOMPLISH = 1;
    private static final int COMPILE = 2;
    public int state = COMPILE;

    private List<CommodityInfo> commAll;
    private CartAdapter cartAdapter;
    private int count;
    private CommodityInfo commInfo;

    @Override
    public void initTitle() {
        titleText.setText("购物车");
        cartCompile.setVisibility(View.VISIBLE);
        titleBack.setVisibility(View.VISIBLE);
        AllSelectedCompile.setVisibility(View.VISIBLE);

    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        cartCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(CartActivity.this, "编辑", Toast.LENGTH_SHORT).show();
                if (state == ACCOMPLISH) {
                    cartCompile.setText("编辑");
                    cartAdapter.setIsState(ACCOMPLISH);
                    //  AllSelectedCompile.setVisibility(View.GONE);
                    //  AllSelectedAccomplish.setVisibility(View.VISIBLE);
                    cartAdapter.notifyDataSetChanged();
                    state = COMPILE;
                } else {
                    cartCompile.setText("完成");
                    cartAdapter.setIsState(COMPILE);
                    // AllSelectedCompile.setVisibility(View.VISIBLE);
                    //AllSelectedAccomplish.setVisibility(View.GONE);
                    cartAdapter.notifyDataSetChanged();
                    state = ACCOMPLISH;
                }
                cartAdapter.notifyDataSetChanged();
            }
        });
        AllSelectedCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = AllSelectedCompile.isChecked();
                cartAdapter.checkAll_none(checked);
                cartAdapter.getTotalPrice();
            }
        });
    }

    @Override
    public void initData() {
        dao = new CommodityDAO(CartActivity.this);
        commAll = dao.getCommAll();
        cartAdapter = new CartAdapter(CartActivity.this, commAll, cartDiscount, cartTotalPrices, AllSelectedCompile, AllSelectedAccomplish);
        cartCommodity.setAdapter(cartAdapter);
        cartCommodity.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }
}
