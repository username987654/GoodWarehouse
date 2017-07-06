package com.goodwarehouse.goodwarehouse.controller.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.ExpertFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.MagazinesFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.ShareFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.ShopFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.main_fragment)
    FrameLayout mainFragment;
    @InjectView(R.id.rb_shop)
    RadioButton rbShop;
    @InjectView(R.id.rb_magazines)
    RadioButton rbMagazines;
    @InjectView(R.id.rb_expert)
    RadioButton rbExpert;
    @InjectView(R.id.rb_share)
    RadioButton rbShare;
    @InjectView(R.id.rb_user)
    RadioButton rbUser;
    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    @InjectView(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;
    private ArrayList<BaseFragment> fragments;
    private int recordProition;
    private Fragment tempFragment;

    @Override
    public void initListener() {
        mainRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int proition) {
                switch (proition) {
                    case R.id.rb_shop:
                        recordProition = 0;
                        break;
                    case R.id.rb_magazines:
                        recordProition = 1;
                        break;
                    case R.id.rb_expert:
                        recordProition = 2;
                        break;
                    case R.id.rb_share:
                        recordProition = 3;
                        break;
                    case R.id.rb_user:
                        recordProition = 4;
                        break;
                }
                Fragment fragment = fragments.get(recordProition);
                showFragment(fragment);

            }
        });
    }


    private void showFragment(Fragment currentFragment) {
        if (currentFragment != tempFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (!currentFragment.isAdded()) {

                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.main_fragment, currentFragment);

            } else {
                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把当前的显示
                ft.show(currentFragment);
            }


            //提交
            ft.commit();

            tempFragment = currentFragment;

        }

    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new MagazinesFragment());
        fragments.add(new ExpertFragment());
        fragments.add(new ShareFragment());
        fragments.add(new UserFragment());
        mainRadiogroup.check(R.id.rb_shop);
        showFragment(fragments.get(0));


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

}
