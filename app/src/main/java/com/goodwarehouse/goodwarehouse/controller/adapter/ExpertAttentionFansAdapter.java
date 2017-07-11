package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ExpertAttentionFansBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-09.
 */

public class ExpertAttentionFansAdapter extends RecyclerView.Adapter<ExpertAttentionFansAdapter.MyViewHolder> {
    private final Context context;
    private final List<ExpertAttentionFansBean.DataBean.ItemsBean.UsersBean> datas;


    public ExpertAttentionFansAdapter(Context context, List<ExpertAttentionFansBean.DataBean.ItemsBean.UsersBean> users) {
        this.context = context;
        this.datas = users;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_expert_details_type, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String orig = datas.get(position).getUser_image().getOrig();
        HttpUtils.loadImage(context, orig, holder.expertDetailsImage);
        holder.expertDetailsName.setVisibility(View.VISIBLE);
        String user_name = datas.get(position).getUser_name();
        holder.expertDetailsName.setText(user_name);
    }

    @Override
    public int getItemCount() {

        return datas == null ? 0 : datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.expert_details_image)
        ImageView expertDetailsImage;
        @InjectView(R.id.expert_details_name)
        TextView expertDetailsName;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            expertDetailsImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(getAdapterPosition());
                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}
