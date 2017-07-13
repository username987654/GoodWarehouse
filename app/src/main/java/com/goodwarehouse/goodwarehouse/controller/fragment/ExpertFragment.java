package com.goodwarehouse.goodwarehouse.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ExpertBean;
import com.goodwarehouse.goodwarehouse.bean.ExpertDataBean;
import com.goodwarehouse.goodwarehouse.controller.activity.ExpertDetailsActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.ExpertAdapter;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.ButterKnife;
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
    @InjectView(R.id.rl_title)
    RelativeLayout rlTitle;
    private List<ExpertBean.DataBean.ItemsBean> items;
    private PopupWindow mPopWindow;
    private TextView pwPeiceAll;
    private TextView pwPeice1;
    private TextView pwPeice2;
    private TextView pwPeice3;
    private TextView pwPeice4;
    private ExpertAdapter expertAdapter;
    private String url = NetRequestSite.EXPERT_URL;
    private int record = 0;
    private int expertclick;
    private int expertdefault;
    private int color;


    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void processData(String response) {
        ExpertBean expertBean = JSON.parseObject(response, ExpertBean.class);
        items = expertBean.getData().getItems();
        expertAdapter = new ExpertAdapter(context, items);
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
                switch (record) {
                    case 0:
                        pwPeiceAll.setBackgroundColor(expertclick);
                        pwPeiceAll.setTextColor(Color.WHITE);
                        break;
                    case 1:
                        pwPeice1.setBackgroundColor(expertclick);
                        pwPeice1.setTextColor(Color.WHITE);
                        break;
                    case 2:
                        pwPeice2.setBackgroundColor(expertclick);
                        pwPeice2.setTextColor(Color.WHITE);
                        break;
                    case 3:
                        pwPeice3.setBackgroundColor(expertclick);
                        pwPeice3.setTextColor(Color.WHITE);
                        break;
                    case 4:
                        pwPeice4.setBackgroundColor(expertclick);
                        pwPeice4.setTextColor(Color.WHITE);
                        break;
                }
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
        mPopWindow.showAsDropDown(rlTitle);

    }

    @Override
    public void initData() {
        expertclick = getResources().getColor(R.color.expertclick);
        expertdefault = getResources().getColor(R.color.expertdefault);
        color = getResources().getColor(R.color.textdefault);
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
                if (record == 0) {
                    mPopWindow.dismiss();
                    return;
                }
//                Toast.makeText(context, "默认推荐", Toast.LENGTH_SHORT).show();
                url = NetRequestSite.EXPERT_URL;
                HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
                    @Override
                    public void onError(String content) {

                    }

                    @Override
                    public void onResponse(String response) {
                        processData(response);
                    }
                });
                expertAdapter.notifyDataSetChanged();
                record = 0;
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_1:
                showPopupWindowItembg(pwPeice1);
                if (record == 1) {
                    mPopWindow.dismiss();
                    return;
                }
//                Toast.makeText(context, "最多推荐", Toast.LENGTH_SHORT).show();
                url = NetRequestSite.EXPERT_MUCHMORE_URL;
                HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
                    @Override
                    public void onError(String content) {

                    }

                    @Override
                    public void onResponse(String response) {
                        processData(response);
                    }
                });
                expertAdapter.notifyDataSetChanged();
                record = 1;
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_2:
                showPopupWindowItembg(pwPeice2);
                if (record == 2) {
                    mPopWindow.dismiss();
                    return;
                }
//                Toast.makeText(context, "最受欢迎", Toast.LENGTH_SHORT).show();
                url = NetRequestSite.EXPERT_WELCOME_URL;
                HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
                    @Override
                    public void onError(String content) {

                    }

                    @Override
                    public void onResponse(String response) {
                        processData(response);
                    }
                });
                record = 2;
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_3:
                showPopupWindowItembg(pwPeice3);
                if (record == 3) {
                    mPopWindow.dismiss();
                    return;
                }
//                Toast.makeText(context, "最新推荐", Toast.LENGTH_SHORT).show();
                url = NetRequestSite.EXPERT_NEW_URL;
                HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
                    @Override
                    public void onError(String content) {

                    }

                    @Override
                    public void onResponse(String response) {
                        processData(response);
                    }
                });
                mPopWindow.dismiss();
                mPopWindow.dismiss();
                break;
            case R.id.pw_price_4:
                showPopupWindowItembg(pwPeice4);
                if (record == 4) {
                    mPopWindow.dismiss();
                    return;
                }
//                Toast.makeText(context, "最新加入", Toast.LENGTH_SHORT).show();
                url = NetRequestSite.EXPERT_JOIN_URL;
                HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
                    @Override
                    public void onError(String content) {

                    }

                    @Override
                    public void onResponse(String response) {
                        processData(response);
                    }
                });
                record = 4;
                mPopWindow.dismiss();
                break;
        }
    }

    private void showPopupWindowItembg(TextView tv) {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
