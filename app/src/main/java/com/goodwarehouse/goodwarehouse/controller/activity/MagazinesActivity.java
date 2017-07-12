package com.goodwarehouse.goodwarehouse.controller.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.magazinesfragment.MagazinesAuthorFagment;
import com.goodwarehouse.goodwarehouse.controller.fragment.magazinesfragment.MagazinesTypeFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MagazinesActivity extends BaseActivity {

    @InjectView(R.id.magazines_tab)
    TabLayout magazinesTab;
    @InjectView(R.id.magazines_vp)
    ViewPager magazinesVp;
    @InjectView(R.id.magazines_rb1)
    RadioButton magazinesRb1;
    @InjectView(R.id.magazines_rb2)
    RadioButton magazinesRb2;
    @InjectView(R.id.magazines_rg)
    RadioGroup magazinesRg;

    private ArrayList<BaseFragment> fragments;
    String[] title = {"分类","作者"};

    @Override
    public void initListener() {
        magazinesRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.magazines_rb1:
                        magazinesVp.setCurrentItem(0);
                        break;
                    case R.id.magazines_rb2:
                        magazinesVp.setCurrentItem(1);
                        break;
                }
            }
        });

    }

    @Override
    public void initData() {
        setFragment();
        magazinesTab.setupWithViewPager(magazinesVp);
        magazinesTab.setTabGravity(TabLayout.GRAVITY_FILL);
        magazinesTab.setTabMode(TabLayout.MODE_FIXED);
    }

/*    magazinesTab.setupWithViewPager(fmShopVp);
    magazinesTab.setTabGravity(TabLayout.GRAVITY_FILL);
    magazinesTab.setTabMode(TabLayout.MODE_FIXED);*/

    private void setFragment() {
        fragments = new ArrayList<>();
        fragments.add(new MagazinesAuthorFagment());
        fragments.add(new MagazinesTypeFragment());
        magazinesVp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_magazines;
    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
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
            return title[position];
        }
    }

}
