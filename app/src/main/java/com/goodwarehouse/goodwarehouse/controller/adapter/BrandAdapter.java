package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ShopBrandBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBserAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class BrandAdapter extends MyBserAdapter<ShopBrandBean.DataBean.ItemsBean> {

    private Context context;

    public BrandAdapter(Context context, List<ShopBrandBean.DataBean.ItemsBean> datas) {
        super(context, datas);
        this.context = context;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new ViewHolder();
    }

    class ViewHolder extends BaseViewHolder<ShopBrandBean.DataBean.ItemsBean> {
        @InjectView(R.id.shop_brand_iv)
        ImageView shopBrandIv;
        @InjectView(R.id.shop_brand_tv)
        TextView shopBrandTv;

        @Override
        protected void setContent(ShopBrandBean.DataBean.ItemsBean itemsBean) {
            String brand_logo = itemsBean.getBrand_logo();
            HttpUtils.requestImage(context,brand_logo,shopBrandIv);
            String brand_name = itemsBean.getBrand_name();
            shopBrandTv.setText(brand_name);
        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_shop_brand, null);
        }
    }
}
