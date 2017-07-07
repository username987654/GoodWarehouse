package com.goodwarehouse.goodwarehouse.controller.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.CommodityBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.CommodityAdapter;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class CommodityActivity extends BaseActivity implements View.OnClickListener {

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
    @InjectView(R.id.shop_type_gv)
    GridView shopTypeGv;
    @InjectView(R.id.activity_commodity)
    LinearLayout activityCommodity;
    @InjectView(R.id.price_screen)
    ImageView priceScreen;
    @InjectView(R.id.rl_title)
    RelativeLayout rlTitle;
    @InjectView(R.id.price_title)
    RelativeLayout priceTitle;
    private List<CommodityBean.DataBean.ItemsBean> items;
    private CommodityAdapter commodityAdapter;
    private PopupWindow mPopWindow;
    private TextView pwPeiceAll;
    private TextView pwPeice1;
    private TextView pwPeice2;
    private TextView pwPeice3;
    private TextView pwPeice4;
    private TextView pwPeice5;

    @Override
    public void initView() {
        titleBack.setVisibility(View.VISIBLE);
        shopCart.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        shopTypeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CommodityActivity.this, "" + items.get(i).getGoods_name(), Toast.LENGTH_SHORT).show();
            }
        });
        priceScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });
        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CommodityActivity.this, "购物车", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {
        String stringExtra = getIntent().getStringExtra(BaseFragment.URL);
        HttpUtils.HttpNet(stringExtra, new HttpUtils.onNetRequestContent() {
            @Override
            public void onError(String content) {
                Log.e("onError", content);
            }

            @Override
            public void onResponse(String response) {
                processData(response);

            }
        });

    }

    private void processData(String json) {
        CommodityBean commodityBean = JSON.parseObject(json, CommodityBean.class);
        items = commodityBean.getData().getItems();
        commodityAdapter = new CommodityAdapter(CommodityActivity.this, items);
        shopTypeGv.setAdapter(commodityAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity;
    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(CommodityActivity.this).inflate(R.layout.price_screen_popupwindow, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setContentView(contentView);
        pwPeiceAll = (TextView) contentView.findViewById(R.id.pw_price_all);
        pwPeice1 = (TextView) contentView.findViewById(R.id.pw_price_1);
        pwPeice2 = (TextView) contentView.findViewById(R.id.pw_price_2);
        pwPeice3 = (TextView) contentView.findViewById(R.id.pw_price_3);
        pwPeice4 = (TextView) contentView.findViewById(R.id.pw_price_4);
        pwPeice5 = (TextView) contentView.findViewById(R.id.pw_price_5);
        pwPeiceAll.setOnClickListener(this);
        pwPeice1.setOnClickListener(this);
        pwPeice2.setOnClickListener(this);
        pwPeice3.setOnClickListener(this);
        pwPeice4.setOnClickListener(this);
        pwPeice5.setOnClickListener(this);

        //显示PopupWindow
        View rootview = LayoutInflater.from(CommodityActivity.this).inflate(R.layout.activity_commodity, null);
//        int i = rlTitle.getHeight() + priceTitle.getHeight();
        mPopWindow.showAsDropDown(priceScreen);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pw_price_all:
                showPopupWindowItembg(pwPeiceAll);
                Toast.makeText(this, "全部", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pw_price_1:
                showPopupWindowItembg(pwPeice1);
                Toast.makeText(this, "0-200", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pw_price_2:
                showPopupWindowItembg(pwPeice2);
                Toast.makeText(this, "201-500", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pw_price_3:
                showPopupWindowItembg(pwPeice3);
                Toast.makeText(this, "501-100", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pw_price_4:
                showPopupWindowItembg(pwPeice4);
                Toast.makeText(this, "1001-3000", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pw_price_5:
                showPopupWindowItembg(pwPeice5);
                Toast.makeText(this, "3000以上", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void showPopupWindowItembg(TextView tv) {
        pwPeiceAll.setTextColor(Color.BLACK);
        pwPeiceAll.setBackgroundColor(Color.WHITE);
        pwPeice1.setTextColor(Color.BLACK);
        pwPeice1.setBackgroundColor(Color.WHITE);
        pwPeice2.setTextColor(Color.BLACK);
        pwPeice2.setBackgroundColor(Color.WHITE);
        pwPeice3.setTextColor(Color.BLACK);
        pwPeice3.setBackgroundColor(Color.WHITE);
        pwPeice4.setTextColor(Color.BLACK);
        pwPeice4.setBackgroundColor(Color.WHITE);
        pwPeice5.setTextColor(Color.BLACK);
        pwPeice5.setBackgroundColor(Color.WHITE);
        tv.setBackgroundColor(Color.GRAY);
        tv.setTextColor(Color.WHITE);
    }
}
