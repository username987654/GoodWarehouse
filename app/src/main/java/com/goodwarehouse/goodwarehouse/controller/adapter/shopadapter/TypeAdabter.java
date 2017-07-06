package com.goodwarehouse.goodwarehouse.controller.adapter.shopadapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.shopbean.ShopTypeBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBserAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class TypeAdabter extends MyBserAdapter<ShopTypeBean.DataBean.ItemsBean> {

    private final Context context;

    public TypeAdabter(Context context, List<ShopTypeBean.DataBean.ItemsBean> datas) {
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
            //加载图片
            HttpUtils.requestImage(context, cover_new_img, itemShopTypeIv);
        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_shop_type, null);
        }
    }
}
