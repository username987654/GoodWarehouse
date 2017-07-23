package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.CommodityInfo;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBaseAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-19.
 */

public class OrderListAdapter extends MyBaseAdapter<CommodityInfo> {


    private final Context context;
    private final List<CommodityInfo> datas;

    public OrderListAdapter(Context context, List<CommodityInfo> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new ViewHolder();
    }

    class ViewHolder extends BaseViewHolder<CommodityInfo> {

        @InjectView(R.id.order_comm_nimage)
        ImageView orderCommNimage;
        @InjectView(R.id.order_comm_name)
        TextView orderCommName;
        @InjectView(R.id.order_comm_color)
        TextView orderCommColor;
        @InjectView(R.id.order_comm_standard)
        TextView orderCommStandard;
        @InjectView(R.id.order_comm_price)
        TextView orderCommPrice;
        @InjectView(R.id.order_comm_count)
        TextView orderCommCount;
        @InjectView(R.id.default_comm)
        LinearLayout defaultComm;

        @Override
        protected void setContent(CommodityInfo commInfo) {

            HttpUtils.loadImage(context, commInfo.getCommImage(), orderCommNimage);
            orderCommName.setText(commInfo.getCommName());
            orderCommColor.setText(commInfo.getCommColocr());
            if (TextUtils.isEmpty(commInfo.getCommStandard())) {
                orderCommStandard.setVisibility(View.GONE);
            } else {
                orderCommStandard.setText(commInfo.getCommStandard());
            }
            String price = TextUtils.isEmpty(commInfo.getCommOriginalPrice()) ? commInfo.getCommPrice() : commInfo.getCommOriginalPrice();
            orderCommPrice.setText("￥" + price);
            orderCommCount.setText("x" + commInfo.getCommCount());

        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_hide_comm, null);
        }
    }
}
