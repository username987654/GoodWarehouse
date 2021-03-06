package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ShopTypeBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBaseAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class TypeAdabter extends MyBaseAdapter<ShopTypeBean.DataBean.ItemsBean> {

    private final Context context;

    public TypeAdabter(Context context, List<ShopTypeBean.DataBean.ItemsBean> datas, int i) {
        super(context, datas);
        this.context = context;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new ViewHodler();
    }

    class ViewHodler extends BaseViewHolder<ShopTypeBean.DataBean.ItemsBean> {

        @InjectView(R.id.item_shop_type_iv)
        ImageView itemShopTypeIv;

        @Override
        protected void setContent(ShopTypeBean.DataBean.ItemsBean itemsBean) {
            String cover_new_img = itemsBean.getCover_new_img();
            String cat_name = itemsBean.getCat_name();

            //加载图片
//            HttpUtils.loadImage(context, cover_new_img, itemShopTypeIv);
            HttpUtils.GlideArcImage(context, cover_new_img, itemShopTypeIv, 5);
        }

        @Override
        public View initView() {

            return View.inflate(context, R.layout.item_shop_type, null);

        }
    }
}
