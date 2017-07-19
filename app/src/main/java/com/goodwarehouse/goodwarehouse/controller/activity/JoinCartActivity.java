package com.goodwarehouse.goodwarehouse.controller.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.bean.CommodityCartData;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.CartListAdapter;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.view.AddSubView;
import com.goodwarehouse.goodwarehouse.view.MyListView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.R.attr.id;

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
    @InjectView(R.id.btn_comfort)
    Button btnComfort;
    @InjectView(R.id.cart_commodity_list)
    MyListView cartCommodityList;
    @InjectView(R.id.linear_list)
    LinearLayout linearList;
    @InjectView(R.id.activity_join_cart)
    RelativeLayout activityJoinCart;
    private CommodityCartData cartData;


    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        cartData = (CommodityCartData) getIntent().getSerializableExtra("CARTDATA");
        String image = cartData.getImage();
        HttpUtils.loadImage(this, image, commodityCartPicture);
        commodityJoinName.setText(cartData.getName());
        commodityJoinIntroduce.setText(cartData.getIntroduce());
        commodityJoinPrice.setText("￥" + cartData.getPrice());
        final List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> skuInfos = cartData.getSkuInfoBean();
        final CartListAdapter cartListAdapter = new CartListAdapter(this, skuInfos);
        cartCommodityList.setAdapter(cartListAdapter);
        setListViewHeightBasedOnChildren(cartCommodityList);
        cartListAdapter.setOnTagFlowClickListener(new CartListAdapter.OnTagFlowClickListener() {

            @Override
            public void onTagFlowClick(View view, int position, FlowLayout parent, int i, List<TagFlowLayout> tagFlowLayouts) {
                List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrList = skuInfos.get(i).getAttrList();
                switch (i) {
                    case 0:

                        CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean attrListBean1 = attrList.get(position);
                        String img_path = attrListBean1.getImg_path();
                        if (!TextUtils.isEmpty(img_path)) {
                            HttpUtils.loadImage(JoinCartActivity.this, img_path, commodityCartPicture);
                        }
                        CommodityDetailsBean.DataBean.ItemsBean.SkuInvBean skuInvBean = cartData.getSkuInvBeen().get(position);
                        String price = skuInvBean.getPrice();
                        if (!TextUtils.isEmpty(price)) {
                            commodityJoinPrice.setText("￥" + price);
                        }
                        break;

                }
            }


        });

    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_join_cart;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {

        // 获取ListView对应的Adapter

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0); // 计算子项View 的宽高

            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);

    }
}

