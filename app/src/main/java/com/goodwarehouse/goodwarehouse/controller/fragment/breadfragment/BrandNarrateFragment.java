package com.goodwarehouse.goodwarehouse.controller.fragment.breadfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.BrandDetailsBean;
import com.goodwarehouse.goodwarehouse.controller.activity.BrandDetailsActivity;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-07.
 */

public class BrandNarrateFragment extends BaseFragment {

    @InjectView(R.id.brand_narrate_tv)
    TextView brandNarrateTv;
    private BrandDetailsActivity brandDetails;
    private String path;


    @Override
    public String getUrl() {
        return path;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_brand_narrate;
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        brandDetails = (BrandDetailsActivity) getActivity();
        path = NetRequestSite.BRAND_INFO_FROEPART_URL
                + brandDetails.getItembean().getBrand_id()
                + NetRequestSite.BRAND_INFO_END_URL;

    }

    @Override
    public void processData(String response) {
        super.processData(response);
        BrandDetailsBean brandDetailsBean = JSON.parseObject(response, BrandDetailsBean.class);
        List<BrandDetailsBean.DataBean.ItemsBean> items = brandDetailsBean.getData().getItems();
        if (items != null && items.size() > 0) {
            String brand_desc = items.get(0).getBrand_info().getBrand_desc();
            brandNarrateTv.setText(brand_desc);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
