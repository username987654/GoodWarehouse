package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ExpertLikeCommendBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-09.
 */

public class ExpertLikeCommendAdapter extends RecyclerView.Adapter<ExpertLikeCommendAdapter.MyViewHolder> {
    private final Context context;
    private final List<ExpertLikeCommendBean.DataBean.ItemsBean.GoodsBean> datas;


    public ExpertLikeCommendAdapter(Context context, List<ExpertLikeCommendBean.DataBean.ItemsBean.GoodsBean> goods) {
        this.context = context;
        this.datas = goods;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_expert_details_type, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String goods_image = datas.get(position).getGoods_image();
        HttpUtils.loadImage(context, goods_image, holder.expertDetailsImage);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.expert_details_image)
        ImageView expertDetailsImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
