package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.MagazineProductionItemBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-11.
 */

public class MagazinesAdapter extends RecyclerView.Adapter {

    private final ArrayList<MagazineProductionItemBean> beanitems;
    private final LayoutInflater inflater;
    private final String date;
    private Context context;

    public MagazinesAdapter(Context context, ArrayList<MagazineProductionItemBean> beanitems) {
        this.context = context;
        this.beanitems = beanitems;
        inflater = LayoutInflater.from(context);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = sDateFormat.format(new Date());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Type2ViewHolder(inflater.inflate(R.layout.item_magazine_type2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Type2ViewHolder type2Holder = (Type2ViewHolder) holder;
        type2Holder.setData(beanitems.get(position), position);
    }

    @Override
    public int getItemCount() {
        return beanitems == null ? 0 : beanitems.size();
    }

    class Type1ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.magazines_image_type1)
        ImageView magazinesImageType1;

        public Type1ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void setData(MagazineProductionItemBean ItemBean) {
            String cover_img_new = ItemBean.getCover_img_new();
            HttpUtils.loadImage(context, cover_img_new, magazinesImageType1);

        }
    }

    class Type2ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.magazines_text_type2)
        TextView magazinesTextType2;
        @InjectView(R.id.magazines_image_type2)
        ImageView magazinesImageType2;
        @InjectView(R.id.ll_magazines)
        LinearLayout llMagazines;
        @InjectView(R.id.magazines_type2)
        TextView magazinesType2;
        private String nextAddtime;
        private String addtime;

        public Type2ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            magazinesImageType2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());

                }
            });
        }

        public void setData(MagazineProductionItemBean ItemBean, int position) {
            String time = beanitems.get(position).getAddtime();
            if (!(position == beanitems.size() - 1)) {
                String nextTime = beanitems.get(position + 1).getAddtime();
                nextAddtime = nextTime.substring(0, time.indexOf(" "));
            }
            addtime = time.substring(0, time.indexOf(" "));
            String cover_img_new = ItemBean.getCover_img_new();
            String topic_name = ItemBean.getTopic_name();
            if (addtime.equals(nextAddtime)) {
                llMagazines.setVisibility(View.GONE);
                HttpUtils.loadImage(context, cover_img_new, magazinesImageType2);
                magazinesType2.setText(topic_name);
            } else {
                HttpUtils.loadImage(context, cover_img_new, magazinesImageType2);
                magazinesType2.setText(topic_name);
                magazinesTextType2.setText(addtime);
                llMagazines.setVisibility(View.VISIBLE);
            }
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int adapterPosition);
    }
}
