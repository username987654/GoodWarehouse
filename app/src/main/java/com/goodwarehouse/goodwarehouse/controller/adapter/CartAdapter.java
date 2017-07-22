package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.CommodityInfo;
import com.goodwarehouse.goodwarehouse.model.dao.CommodityDAO;
import com.goodwarehouse.goodwarehouse.model.table.Commodity;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.view.AddSubView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-20.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private final Context context;
    private final CommodityDAO dao;
    private final TextView cartDiscount;
    private final TextView cartTotalPrices;
    private final List<CommodityInfo> datas;
    private final CheckBox allSelected;
    private final CheckBox allSelectedAccomplish;
    private int state = 1;

    public CartAdapter(Context context, List<CommodityInfo> commAll, TextView cartDiscount, TextView cartTotalPrices, CheckBox allSelected, CheckBox allSelectedAccomplish) {
        this.context = context;
        this.datas = commAll;
        this.dao = new CommodityDAO(context);
        this.cartDiscount = cartDiscount;
        this.cartTotalPrices = cartTotalPrices;
        this.allSelected = allSelected;
        this.allSelectedAccomplish = allSelectedAccomplish;
        getTotalPrice();
        checkAll();
    }

    public void checkAll() {
        if (datas == null && datas.size() < 0) {
            return;
        }
        int number = 0;
        for (int i = 0; i < datas.size(); i++) {
            CommodityInfo commodityInfo = datas.get(i);
            if (!commodityInfo.getChecked()) {
                allSelected.setChecked(false);
//                allSelectedAccomplish.setChecked(false);
            } else {
                number++;
            }
            if (number == datas.size()) {
                allSelected.setChecked(true);
//                allSelectedAccomplish.setChecked(true);
            }
        }

    }

    /*
    * 选中商品总价
    * */
    public void getTotalPrice() {
        if (datas == null && datas.size() < 0) {
            return;
        }
        double result = 0;
        double discount = 0;
        CommodityInfo commodityInfo = null;
        for (int i = 0; i < datas.size(); i++) {
            commodityInfo = datas.get(i);
            if (commodityInfo.getChecked()) {
                String price = TextUtils.isEmpty(commodityInfo.getCommOriginalPrice()) ? commodityInfo.getCommPrice() : commodityInfo.getCommOriginalPrice();
                result += Double.parseDouble(price) * commodityInfo.getCommCount();
                discount += Double.parseDouble(commodityInfo.getCommDiscount()) * commodityInfo.getCommCount();
            }
        }
        cartTotalPrices.setText("￥:" + result);
        cartDiscount.setText("" + discount);
    }

    public void checkAll_none(boolean isChecked) {
        if (datas == null && datas.size() < 0) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            CommodityInfo commodityInfo = datas.get(i);
            commodityInfo.setChecked(isChecked);
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setData(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setIsState(int state) {
        this.state = state;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.cart_commodity_checkbox)
        CheckBox cartCommodityCheckbox;
        @InjectView(R.id.cart_commodity_image)
        ImageView cartCommodityImage;
        @InjectView(R.id.cart_comm_name)
        TextView cartCommName;
        @InjectView(R.id.cart_comm_colocr)
        TextView cartCommColocr;
        @InjectView(R.id.cart_comm_standard)
        TextView cartCommStandard;
        @InjectView(R.id.cart_comm_price)
        TextView cartCommPrice;
        @InjectView(R.id.cart_comm_original_price)
        TextView cartCommOriginalPrice;
        @InjectView(R.id.cart_comm_count)
        TextView cartCommCount;
        @InjectView(R.id.ll_accomplish)
        LinearLayout llAccomplish;
        @InjectView(R.id.cart_editor_checkbox)
        CheckBox cartEditorCheckbox;
        @InjectView(R.id.cart_editor_image)
        ImageView cartEditorImage;
        @InjectView(R.id.cart_editor_count)
        AddSubView cartEditorCount;
        @InjectView(R.id.cart_editor_colocr)
        TextView cartEditorColocr;
        @InjectView(R.id.cart_editor_price)
        TextView cartEditorPrice;
        @InjectView(R.id.ll_compile)
        LinearLayout llCompile;
        @InjectView(R.id.cart_editor_delete)
        Button cartEditorDelete;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            cartCommodityCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommodityInfo commodityInfo = datas.get(getLayoutPosition());
                    commodityInfo.setChecked(!commodityInfo.getChecked());
                    notifyItemChanged(getLayoutPosition());
                    getTotalPrice();
                    checkAll();
                }
            });
            cartEditorCheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommodityInfo commodityInfo = datas.get(getLayoutPosition());
                    commodityInfo.setChecked(!commodityInfo.getChecked());
                    notifyItemChanged(getLayoutPosition());
                    getTotalPrice();
                    checkAll();
                }
            });

        }

        public void setData(final CommodityInfo commodityInfo) {
            if (state == 1) {
                cartCommodityCheckbox.setChecked(commodityInfo.getChecked());
                llAccomplish.setVisibility(View.VISIBLE);
                llCompile.setVisibility(View.GONE);
                HttpUtils.loadImage(context, commodityInfo.getCommImage(), cartCommodityImage);
                String commName = commodityInfo.getBrandName() + " | " + commodityInfo.getCommName();
                cartCommName.setText(commName);
                cartCommStandard.setText(commodityInfo.getCommStandard());
                cartCommColocr.setText(commodityInfo.getCommColocr() + ";");
                String commOriginalPrice = commodityInfo.getCommOriginalPrice();
                String commPrice = commodityInfo.getCommPrice();
                String price = TextUtils.isEmpty(commOriginalPrice) ? commPrice : commOriginalPrice;
                cartCommPrice.setText("￥" + price);
                cartCommCount.setText("x" + commodityInfo.getCommCount());
                if (!TextUtils.isEmpty(commOriginalPrice)) {
                    cartCommOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                    cartCommOriginalPrice.setText("￥" + commPrice);
                }

            } else {
                cartEditorCheckbox.setChecked(commodityInfo.getChecked());
                llCompile.setVisibility(View.VISIBLE);
                llAccomplish.setVisibility(View.GONE);
                HttpUtils.loadImage(context, commodityInfo.getCommImage(), cartEditorImage);
                final String commName = commodityInfo.getBrandName() + " | " + commodityInfo.getCommName();
                cartEditorColocr.setText(commName);
                String commOriginalPrice = commodityInfo.getCommOriginalPrice();
                String commPrice = commodityInfo.getCommPrice();
                String price = TextUtils.isEmpty(commOriginalPrice) ? commPrice : commOriginalPrice;
                cartEditorPrice.setText("￥" + price);
                int inventory = Integer.parseInt(commodityInfo.getCommSum());
                cartEditorCount.setMaxValue(inventory);

                cartEditorCount.setValue(commodityInfo.getCommCount());
                cartEditorDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View inflate = View.inflate(context, R.layout.delete_diadog, null);
                        builder.setView(inflate);
                        final AlertDialog dialog = builder.show();
                        inflate.findViewById(R.id.dialog_close).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        inflate.findViewById(R.id.dialog_affirm).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dao.removeCommodity(commodityInfo.getCommID());
                                datas.remove(commodityInfo);
                                checkAll();
                                getTotalPrice();
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "已删除", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                cartEditorCount.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
                    @Override
                    public void onNumberChange(int number) {
                        dao.updataCommodity(commodityInfo.getCommID(), number + "");
                        commodityInfo.setCommCount(number);
                        getTotalPrice();
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }

    private OnNumberListener onNumberListener;

    public void setOnNumberListener(OnNumberListener onNumberListener) {
        this.onNumberListener = onNumberListener;
    }

    public interface OnNumberListener {

        void onNumberClick(CommodityInfo commodityInfo, int number);
    }
}
