package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ShopSpecialBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBserAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class SpecialAdapter extends MyBserAdapter<ShopSpecialBean.DataBean.ItemsBean> {
    private final Context context;

    public SpecialAdapter(Context context, List<ShopSpecialBean.DataBean.ItemsBean> datas) {
        super(context, datas);
        this.context = context;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new ViewHodler();
    }

    class ViewHodler extends BaseViewHolder<ShopSpecialBean.DataBean.ItemsBean> {

        @InjectView(R.id.shop_special_iv)
        ImageView shopSpecialIv;
        @InjectView(R.id.shop_special_tv)
        TextView shopSpecialTv;

        @Override
        protected void setContent(ShopSpecialBean.DataBean.ItemsBean itemsBean) {
            String cover_img_new = itemsBean.getCover_img_new();
            HttpUtils.loadImage(context, cover_img_new, shopSpecialIv);
            String topic_name = itemsBean.getTopic_name();
            shopSpecialTv.setText(topic_name);

        }

        @Override
        public View initView() {
             return View.inflate(context, R.layout.item_shop_special, null);
        }
    }
}
