package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.goodwarehouse.goodwarehouse.base.BaseFragment;

import java.util.List;

/**
 * Created by HaoMeng on 2017-07-13.
 */

public class ShareAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;
    String[] title = {"推荐", "段子"};

    public ShareAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
