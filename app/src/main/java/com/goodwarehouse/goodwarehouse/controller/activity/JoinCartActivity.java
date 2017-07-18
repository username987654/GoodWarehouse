package com.goodwarehouse.goodwarehouse.controller.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.CommodityCartData;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.view.AddSubView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class JoinCartActivity extends BaseActivity {

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

    @InjectView(R.id.commodiity_num)
    AddSubView commodiityNum;
    @InjectView(R.id.activity_join_cart)
    LinearLayout activityJoinCart;
    @InjectView(R.id.commodity_join_model_model)
    TagFlowLayout commodityJoinModelModel;
    private CommodityCartData cartData;
    private String[] names;
    private TextView tv;

    @Override
    public void initListener() {
        commodityCartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        commodityJoinModelModel.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(JoinCartActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                TextView tv1 = (TextView) view;
                view.setBackgroundColor(Color.parseColor("#64B4E0"));
                if (tv != null) {
                    tv.setBackgroundColor(Color.parseColor("#464343"));
                }
                tv = (TextView) view;

                return false;
            }
        });

    }

    @Override
    public void initData() {
        cartData = (CommodityCartData) getIntent().getSerializableExtra("CARTDATA");
        String image = cartData.getImage();
        HttpUtils.loadImage(this, image, commodityCartPicture);
        commodityJoinName.setText(cartData.getName());
        commodityJoinIntroduce.setText(cartData.getIntroduce());
        commodityJoinPrice.setText("￥" + cartData.getPrice());
        List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> arrtList = cartData.getArrtList();
        names = new String[arrtList.size()];
        for (int i = 0; i < arrtList.size(); i++) {
            names[i] = arrtList.get(i).getAttr_name();
        }

        setTagFlow(names);

    }

    private void setTagFlow(String[] names) {
        commodityJoinModelModel.setAdapter(new TagAdapter<String>(names) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                final TextView tv = new TextView(JoinCartActivity.this);
                tv.setTextSize(15);
                tv.setTextColor(Color.WHITE);
                tv.setPadding(10, 10, 10, 10);
                //获取shape布局的实例对象
                Drawable drawable = getResources().getDrawable(R.drawable.hot_shape);
                tv.setBackgroundDrawable(drawable);
                //获取textView的背景
                final GradientDrawable gd = (GradientDrawable) tv.getBackground();
                //设置背景色
                gd.setColor(Color.parseColor("#464343"));
                tv.setText(s);

                return tv;
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_join_cart;
    }

}
