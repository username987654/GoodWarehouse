package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.MagazineProductionItemBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-13.
 */

public class MagazinesAuthorAdapter extends RecyclerView.Adapter<MagazinesAuthorAdapter.MyViewHolder> {
    private final Context context;
    private final ArrayList<MagazineProductionItemBean> beanitems;


    public MagazinesAuthorAdapter(Context context, ArrayList<MagazineProductionItemBean> beanitems) {
        this.context = context;
        this.beanitems = beanitems;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_magazines_author, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MagazineProductionItemBean itemBean = beanitems.get(position);
        String cover_img_new = itemBean.getCover_img_new();
        HttpUtils.loadImage(context, cover_img_new, holder.magazinesAuthorImage);
        String topic_name = itemBean.getTopic_name();
        holder.magazinesAuthorContent.setText(topic_name);
        String cat_name = itemBean.getCat_name();
        holder.magazinesAuthorType.setText(cat_name);

    }

    @Override
    public int getItemCount() {
        return beanitems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.magazines_author_image)
        ImageView magazinesAuthorImage;
        @InjectView(R.id.magazines_author_content)
        TextView magazinesAuthorContent;
        @InjectView(R.id.magazines_author_type)
        TextView magazinesAuthorType;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            magazinesAuthorImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());

                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
