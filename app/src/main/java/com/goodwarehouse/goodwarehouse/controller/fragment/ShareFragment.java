package com.goodwarehouse.goodwarehouse.controller.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.controller.adapter.ShareAdapter;
import com.goodwarehouse.goodwarehouse.controller.fragment.sharefragment.ShareConnotationTextFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.sharefragment.ShareRecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-05.
 */

public class ShareFragment extends BaseFragment {
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.share_vp)
    ViewPager shareVp;
    @InjectView(R.id.share_tab)
    TabLayout shareTab;


    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new ShareRecommendFragment());
        fragments.add(new ShareConnotationTextFragment());
        ShareAdapter shareAdapter = new ShareAdapter(getFragmentManager(), fragments);
        shareVp.setAdapter(shareAdapter);
        shareTab.setupWithViewPager(shareVp);
        shareTab.setTabGravity(TabLayout.GRAVITY_FILL);
        shareTab.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initTitle() {
        titleText.setText("分享");
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmnet_share;
    }
}
