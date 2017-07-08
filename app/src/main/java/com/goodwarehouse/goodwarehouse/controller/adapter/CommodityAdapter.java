package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.CommodityBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBserAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class CommodityAdapter extends MyBserAdapter<CommodityBean.DataBean.ItemsBean> {

    private final Context context;

    public CommodityAdapter(Context context, List<CommodityBean.DataBean.ItemsBean> datas) {
        super(context, datas);
        this.context = context;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new ViewHolder();
    }

    class ViewHolder extends BaseViewHolder<CommodityBean.DataBean.ItemsBean> {

        @InjectView(R.id.commodity_iv)
        ImageView commodityIv;
        @InjectView(R.id.commodity_title)
        TextView commodityTitle;
        @InjectView(R.id.commodity_brand_info)
        TextView commodityBrandInfo;
        @InjectView(R.id.commlike_like_count)
        TextView commlikeLikeCount;
        @InjectView(R.id.commlike_price)
        TextView commlikePrice;
        @InjectView(R.id.discount_price)
        TextView discountPrice;
        @InjectView(R.id.commodity_discount_iv)
        ImageView commodityDiscountIv;

        @Override
        protected void setContent(CommodityBean.DataBean.ItemsBean itemsBean) {
            String goods_image = itemsBean.getGoods_image();
            HttpUtils.loadImage(context, goods_image, commodityIv);
            String goods_name = itemsBean.getGoods_name();
            commodityTitle.setText(goods_name);
            String brand_name = itemsBean.getBrand_info().getBrand_name();
            commodityBrandInfo.setText(brand_name);
            String like_count = itemsBean.getLike_count();
            commlikeLikeCount.setText(like_count);
            String price = itemsBean.getPrice();
            String discount_price = itemsBean.getDiscount_price();
            String promotion_imgurl = itemsBean.getPromotion_imgurl();
            if (!TextUtils.isEmpty(promotion_imgurl)) {
                HttpUtils.loadImage(context, promotion_imgurl, commodityDiscountIv);
                discountPrice.setVisibility(View.VISIBLE);
                discountPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
                commlikePrice.setText("￥" + discount_price);
                discountPrice.setText("￥" + price);
            } else {
                commlikePrice.setText("￥"+price);
            }

        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_commodity, null);
        }
    }

}
