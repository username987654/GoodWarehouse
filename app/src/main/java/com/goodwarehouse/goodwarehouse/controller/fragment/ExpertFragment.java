package com.goodwarehouse.goodwarehouse.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ExpertBean;
import com.goodwarehouse.goodwarehouse.bean.ExpertDataBean;
import com.goodwarehouse.goodwarehouse.controller.activity.ExpertDetailsActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.ExpertAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-05.
 */

public class ExpertFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.title_seek)
    ImageView titleSeek;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.magazines_classify)
    ImageView magazinesClassify;
    @InjectView(R.id.shop_cart)
    ImageView shopCart;
    @InjectView(R.id.magazines_cart)
    ImageView magazinesCart;
    @InjectView(R.id.fm_expert_gv)
    GridView fmExpertGv;
    private List<ExpertBean.DataBean.ItemsBean> items;
    private PopupWindow mPopWindow;
    private TextView pwPeiceAll;
    private TextView pwPeice1;
    private TextView pwPeice2;
    private TextView pwPeice3;
    private TextView pwPeice4;


    @Override
    public String getUrl() {
        return NetRequestSite.EXPERT_URL;
    }

    @Override
    public void processData(String response) {
        ExpertBean expertBean = JSON.parseObject(response, ExpertBean.class);
        items = expertBean.getData().getItems();
        ExpertAdapter expertAdapter = new ExpertAdapter(context, items);
        fmExpertGv.setAdapter(expertAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expert;
    }

    @Override
    public void initListener() {
        fmExpertGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ExpertBean.DataBean.ItemsBean expertBean = items.get(i);
                Intent intent = new Intent(context, ExpertDetailsActivity.class);
                ExpertDataBean dataBean = new ExpertDataBean(expertBean.getUid(), expertBean.getUsername(), expertBean.getDuty(), expertBean.getUser_images().getMid());
                intent.putExtra(EXPERTUBEAN, dataBean);
                startActivity(intent);
            }
        });
        magazinesCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });

    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.expert_popupwindow, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setContentView(contentView);
        pwPeiceAll = (TextView) contentView.findViewById(R.id.pw_price_all);
        pwPeice1 = (TextView) contentView.findViewById(R.id.pw_price_1);
        pwPeice2 = (TextView) contentView.findViewById(R.id.pw_price_2);
        pwPeice3 = (TextView) contentView.findViewById(R.id.pw_price_3);
        pwPeice4 = (TextView) contentView.findViewById(R.id.pw_price_4);
        pwPeiceAll.setOnClickListener(this);
        pwPeice1.setOnClickListener(this);
        pwPeice2.setOnClickListener(this);
        pwPeice3.setOnClickListener(this);
        pwPeice4.setOnClickListener(this);

        //显示PopupWindow
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_expert_details, null);
        mPopWindow.showAsDropDown(magazinesCart);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        titleSeek.setVisibility(View.VISIBLE);
        magazinesCart.setVisibility(View.VISIBLE);
        titleText.setText("达人");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pw_price_all:
                showPopupWindowItembg(pwPeiceAll);
                Toast.makeText(context, "默认推荐", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_1:
                showPopupWindowItembg(pwPeice1);
                Toast.makeText(context, "最多推荐", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_2:
                showPopupWindowItembg(pwPeice2);
                Toast.makeText(context, "最受欢迎", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_3:
                showPopupWindowItembg(pwPeice3);
                Toast.makeText(context, "最新推荐", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_4:
                showPopupWindowItembg(pwPeice4);
                Toast.makeText(context, "最新加入", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                break;
        }
    }

    private void showPopupWindowItembg(TextView tv) {
        int expertclick = getResources().getColor(R.color.expertclick);
        int expertdefault = getResources().getColor(R.color.expertdefault);
        int color = getResources().getColor(R.color.textdefault);
        pwPeiceAll.setTextColor(color);
        pwPeiceAll.setBackgroundColor(expertdefault);
        pwPeice1.setTextColor(color);
        pwPeice1.setBackgroundColor(expertdefault);
        pwPeice2.setTextColor(color);
        pwPeice2.setBackgroundColor(expertdefault);
        pwPeice3.setTextColor(color);
        pwPeice3.setBackgroundColor(expertdefault);
        pwPeice4.setTextColor(color);
        pwPeice4.setBackgroundColor(expertdefault);
        tv.setBackgroundColor(expertclick);
        tv.setTextColor(Color.WHITE);
    }
}
