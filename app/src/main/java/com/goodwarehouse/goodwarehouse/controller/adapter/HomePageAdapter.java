package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ShopHomePageBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-07.
 */

public class HomePageAdapter extends RecyclerView.Adapter {
    private static final int HOMEPAGETYPE1 = 1;
    private static final int HOMEPAGETYPE2 = 2;
    private static final int HOMEPAGETYPE3 = 6;
    private static final int HOMEPAGETYPE4 = 4;
    private static int RECORDHOMEPAGETYPE = HOMEPAGETYPE1;


    private final Context context;
    private final List<ShopHomePageBean.DataBean.ItemsBean.ListBeanX> list;
    private final LayoutInflater inflater;

    public HomePageAdapter(Context context, List<ShopHomePageBean.DataBean.ItemsBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        int home_type = list.get(position).getHome_type();
        if (HOMEPAGETYPE1 == home_type) {
            RECORDHOMEPAGETYPE = HOMEPAGETYPE1;
        } else if (HOMEPAGETYPE2 == home_type) {
            RECORDHOMEPAGETYPE = HOMEPAGETYPE2;

        } else if (HOMEPAGETYPE3 == home_type) {
            RECORDHOMEPAGETYPE = HOMEPAGETYPE3;

        } else if (HOMEPAGETYPE4 == home_type) {
            RECORDHOMEPAGETYPE = HOMEPAGETYPE4;
        }
        return RECORDHOMEPAGETYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HOMEPAGETYPE1) {
            return new Type1ViewHolder(inflater.inflate(R.layout.shop_homepage_type1, parent,false));
        } else if (viewType == HOMEPAGETYPE2) {
            return new Type2ViewHodler(inflater.inflate(R.layout.shop_homepage_type2, parent,false));
        } else if (viewType == HOMEPAGETYPE3) {
            return new Type3ViewHodler(inflater.inflate(R.layout.shop_homepage_type3, parent,false));
        } else if (viewType == HOMEPAGETYPE4) {
            return new Type4ViewHodler(inflater.inflate(R.layout.shop_homepage_type4, parent,false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HOMEPAGETYPE1) {
            Type1ViewHolder typeHolder = (Type1ViewHolder) holder;
            typeHolder.setData(list.get(position));
        } else if (getItemViewType(position) == HOMEPAGETYPE2) {
            Type2ViewHodler type2Holder = (Type2ViewHodler) holder;
            type2Holder.setData(list.get(position));
        } else if (getItemViewType(position) == HOMEPAGETYPE3) {
            Type3ViewHodler type3Hodler = (Type3ViewHodler) holder;
            type3Hodler.setData(list.get(position).getList().get(position).getPic_url());

        } else if (getItemViewType(position) == HOMEPAGETYPE4) {
            Type4ViewHodler type4Holder = (Type4ViewHodler) holder;
            type4Holder.setData(list.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Type1ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.homepage_iv)
        ImageView homepageIv;

        public Type1ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            homepageIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE1);
                }
            });
        }

        public void setData(ShopHomePageBean.DataBean.ItemsBean.ListBeanX listBean) {
            String pic_url = listBean.getOne().getPic_url();
            HttpUtils.loadImage(context, pic_url, homepageIv);
        }
    }

    class Type2ViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.homepage_type2_left_iv)
        ImageView homepageType2LeftIv;
        @InjectView(R.id.homepage_type2_right_iv)
        ImageView homepageType2RightIv;

        public Type2ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            homepageType2LeftIv.setOnClickListener(this);
            homepageType2RightIv.setOnClickListener(this);
        }

        public void setData(ShopHomePageBean.DataBean.ItemsBean.ListBeanX listBean) {
            String pic_url = listBean.getOne().getPic_url();
            String pic_url1 = listBean.getTwo().getPic_url();
            HttpUtils.loadImage(context, pic_url, homepageType2LeftIv);
            HttpUtils.loadImage(context, pic_url1, homepageType2RightIv);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.homepage_type2_left_iv:
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE2);
                    break;
                case R.id.homepage_type2_right_iv:
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE2);
                    break;
            }
        }
    }

    class Type3ViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.homepage_iv6)
        ImageView homepageIv6;

        public Type3ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            homepageIv6.setOnClickListener(this);
        }

        public void setData(String pic_url) {

            HttpUtils.loadImage(context, pic_url, homepageIv6);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE3);
        }
    }

    class Type4ViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.homepage_type4_left1)
        ImageView homepageType4Left1;
        @InjectView(R.id.homepage_type4_left2)
        ImageView homepageType4Left2;
        @InjectView(R.id.homepage_type4_right1)
        ImageView homepageType4Right1;
        @InjectView(R.id.homepage_type4_right2)
        ImageView homepageType4Right2;

        public Type4ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            homepageType4Left1.setOnClickListener(this);
            homepageType4Left2.setOnClickListener(this);
            homepageType4Right1.setOnClickListener(this);
            homepageType4Right2.setOnClickListener(this);
        }

        public void setData(ShopHomePageBean.DataBean.ItemsBean.ListBeanX listBean) {
            String pic_url = listBean.getOne().getPic_url();
            String pic_url1 = listBean.getTwo().getPic_url();
            String pic_url2 = listBean.getThree().getPic_url();
            String pic_url3 = listBean.getFour().getPic_url();
            HttpUtils.loadImage(context, pic_url, homepageType4Left1);
            HttpUtils.loadImage(context, pic_url1, homepageType4Left2);
            HttpUtils.loadImage(context, pic_url2, homepageType4Right1);
            HttpUtils.loadImage(context, pic_url3, homepageType4Right2);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.homepage_type4_left1:
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE4);
                    break;
                case R.id.homepage_type4_left2:
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE4);
                    break;
                case R.id.homepage_type4_right1:
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE4);
                    break;
                case R.id.homepage_type4_right2:
                    onItemClickListener.onClick(view, getAdapterPosition(), HOMEPAGETYPE4);
                    break;
            }
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(View view, int position, int type);
    }

}
