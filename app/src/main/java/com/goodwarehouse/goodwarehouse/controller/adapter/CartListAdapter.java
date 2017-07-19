package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.CommodityDetailsBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-19.
 */

public class CartListAdapter extends BaseAdapter {

    private final Context context;
    private final List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> datas;
    private List<TagFlowLayout> tagFlowLayouts;


    public CartListAdapter(Context context, List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean> datas) {
        this.context = context;
        this.datas = datas;
        this.tagFlowLayouts = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_cart_switch, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        tagFlowLayouts.add(viewHolder.cartTagflow);
        final CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean = datas.get(i);
        viewHolder.cartText.setText(skuInfoBean.getType_name());
        MyTagAdapter myTagAdapter = new MyTagAdapter(skuInfoBean.getAttrList());
        viewHolder.cartTagflow.setAdapter(myTagAdapter);
        myTagAdapter.setSelectedList(0);
        viewHolder.cartTagflow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                onTagFlowClickListener.onTagFlowClick(view, position, parent, i, tagFlowLayouts);
                return true;
            }
        });
        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.cart_text)
        TextView cartText;
        @InjectView(R.id.cart_Tagflow)
        TagFlowLayout cartTagflow;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

    class MyTagAdapter extends TagAdapter<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> {


        public MyTagAdapter(List<CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, CommodityDetailsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean attrListBean) {
            TextView tv = new TextView(context);
            tv.setTextSize(15);
            tv.setPadding(10, 10, 10, 10);
            tv.setTextColor(Color.WHITE);
            //获取shape布局的实例对象
            Drawable drawable = context.getResources().getDrawable(R.drawable.cart_check_all_bg);
            tv.setBackgroundDrawable(drawable);
            tv.setText(attrListBean.getAttr_name());
            return tv;

        }
    }

    private OnTagFlowClickListener onTagFlowClickListener;

    public void setOnTagFlowClickListener(OnTagFlowClickListener onTagFlowClickListener) {
        this.onTagFlowClickListener = onTagFlowClickListener;
    }

    public interface OnTagFlowClickListener {
        void onTagFlowClick(View view, int position, FlowLayout parent, int i, List<TagFlowLayout> tagFlowLayouts);
    }


}
