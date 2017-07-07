package com.goodwarehouse.goodwarehouse.controller.fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ExpertBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.ExpertAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-05.
 */

public class ExpertFragment extends BaseFragment {
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

}
