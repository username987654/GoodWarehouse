package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;

import butterknife.InjectView;

public class WelcomeActivity extends BaseActivity {

    @InjectView(R.id.activity_main)
    LinearLayout activityMain;
    @InjectView(R.id.welcome_iv)
    ImageView welcomeIv;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        AnimationDrawable drawable = (AnimationDrawable) welcomeIv.getBackground();
        drawable.start();
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }
}
