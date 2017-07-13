package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.MagazinesAuthorBean;
import com.goodwarehouse.goodwarehouse.bean.ShopTypeBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBaseAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;


/**
 * Created by HaoMeng on 2017-07-12.
 */

public class MagazinesTypeAdapter extends MyBaseAdapter<MagazinesAuthorBean.DataBean.ItemsBean> {
    private final Context context;
    private final List<MagazinesAuthorBean.DataBean.ItemsBean> items;

    public MagazinesTypeAdapter(Context context, List<MagazinesAuthorBean.DataBean.ItemsBean> items) {
        super(context, items);
        this.context = context;
        this.items = items;
    }


    @Override
    public BaseViewHolder getHolder() {
        return new MagazinesTypeAdapter.ViewHodler();
    }

    class ViewHodler extends BaseViewHolder<MagazinesAuthorBean.DataBean.ItemsBean> {

        @InjectView(R.id.item_magazines_type_iv)
        ImageView itemMagazinesTypeIv;
        @InjectView(R.id.item_magazines_type_tv)
        TextView itemMagazinesTypeTv;
        @InjectView(R.id.all)
        ImageView all;
        @InjectView(R.id.collect)
        ImageView collect;

        @Override
        protected void setContent(MagazinesAuthorBean.DataBean.ItemsBean itemsBean) {
            String note = itemsBean.getNote();
            String author_name = itemsBean.getAuthor_name();
            String thumb = itemsBean.getThumb();

            itemMagazinesTypeTv.setText(author_name);
            all.setVisibility(View.GONE);
            collect.setVisibility(View.GONE);
            if (author_name.equals(items.get(0).getAuthor_name())) {
                collect.setVisibility(View.VISIBLE);
            }
            if (author_name.equals(items.get(1).getAuthor_name())) {
                all.setVisibility(View.VISIBLE);
            }


            //加载图片
            HttpUtils.loadImage(context, thumb, itemMagazinesTypeIv);
        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_magazine_type, null);
        }
    }
}


