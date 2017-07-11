package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-11.
 */

public class CommodityDetailsAdapter extends RecyclerView.Adapter {
    private static final int TYPE1 = 1;
    private static final int TYPE2 = 0;
    private static final int TYPE3 = 2;
    private static int record = TYPE1;
    private final Context context;
    private final List<CommodityDetailsBean.DataBean.ItemsBean.GoodsInfoBean> datas;
    private final LayoutInflater inflater;


    public CommodityDetailsAdapter(Context context, List<CommodityDetailsBean.DataBean.ItemsBean.GoodsInfoBean> goods_info) {
        this.context = context;
        this.datas = goods_info;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int type = datas.get(position).getType();
        if (type == TYPE1) {
            record = TYPE1;
        } else if (type == TYPE2) {
            record = TYPE2;
        } else if (type == TYPE3) {
            record = TYPE3;
        }
        return record;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE1) {
            return new Type1Holder(inflater.inflate(R.layout.item_commodity_details_type1,parent,false));
        } else if (viewType == TYPE2) {
            return new Type2Holder(inflater.inflate(R.layout.item_commodity_details_type2, null));
        } else if (viewType == TYPE3) {
            return new Type3Holder(inflater.inflate(R.layout.item_commodity_details_type2, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE1) {
            Type1Holder type1Holder = (Type1Holder) holder;
            type1Holder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE2) {
            Type2Holder type2Holder = (Type2Holder) holder;
            type2Holder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE3) {
            Type3Holder type3Holder = (Type3Holder) holder;
            type3Holder.setData(datas.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class Type1Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cd_item_type1)
        ImageView cdItemType1;

        public Type1Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(CommodityDetailsBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean) {
            String img = goodsInfoBean.getContent().getImg();
            HttpUtils.loadImage(context, img, cdItemType1);
        }
    }

    class Type2Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_cd_type2)
        TextView itemCdType2;

        public Type2Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(CommodityDetailsBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean) {
            String text = goodsInfoBean.getContent().getText();
            itemCdType2.setTextColor(Color.parseColor("#D4D4D4"));
            itemCdType2.setText(text);
        }
    }

    class Type3Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.item_cd_type2)
        TextView itemCdType2;

        public Type3Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(CommodityDetailsBean.DataBean.ItemsBean.GoodsInfoBean goodsInfoBean) {
            String text = goodsInfoBean.getContent().getText();
            itemCdType2.setTextColor(Color.WHITE);
            itemCdType2.setText(text);
        }
    }

}
