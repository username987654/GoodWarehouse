package com.goodwarehouse.goodwarehouse.controller.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.BrandFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.TypeFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.GiftFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.HomePageFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.SpecialFragment;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-05.
 */

public class ShopFragment extends BaseFragment {
    @InjectView(R.id.fm_shop_vp)
    ViewPager fmShopVp;
    @InjectView(R.id.fm_shop_tab)
    TabLayout fmShopTab;
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
    private ArrayList<BaseFragment> fragments;
    String[] type = {"分类", "品牌", "首页", "专题", "礼物"};

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {
        //设置Fragment 的集合数据
        setFragment();
        //设置TabLayout显示
        setTab();
    }


    private void setTab() {
        fmShopTab.setupWithViewPager(fmShopVp);
        fmShopTab.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void setFragment() {
        fragments = new ArrayList<>();
        fragments.add(new TypeFragment());
        fragments.add(new BrandFragment());
        fragments.add(new HomePageFragment());
        fragments.add(new SpecialFragment());
        fragments.add(new GiftFragment());
        fmShopVp.setAdapter(new ShopAdapter(getFragmentManager()));
    }

    @Override
    public void initTitle() {
        titleSeek.setVisibility(View.VISIBLE);
        shopCart.setVisibility(View.VISIBLE);
        titleText.setText("商店");
    }


    class ShopAdapter extends FragmentPagerAdapter {
        public ShopAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return type[position];
        }
    }

}
