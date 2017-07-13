package com.goodwarehouse.goodwarehouse.controller.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;

import butterknife.ButterKnife;
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
        loadSplashGif();
    }

    private void loadSplashGif() {

        Glide.with(this)
                .load(R.drawable.loading_start)
                .listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model,
                                               Target<GlideDrawable> target,
                                               boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final GlideDrawable resource, Integer model,
                                                   Target<GlideDrawable> target,
                                                   boolean isFromMemoryCache,
                                                   boolean isFirstResource) {

                        int durcation = 0;

                        GifDrawable drawable = (GifDrawable) resource;
                        GifDecoder decoder = drawable.getDecoder();
                        for (int i = 0; i < drawable.getFrameCount(); i++) {
                            durcation += decoder.getDelay(i);
                            //durcation为获取到的动画播放的事件
                        }
                        handler.sendEmptyMessageDelayed(1, durcation);

                        return false;
                    }
                }).into(new GlideDrawableImageViewTarget(welcomeIv, 1)); // 设置播放一次

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;

            if (what == 1) {

                /**
                 * 这里还要判断是否为第一次进入软件，并记录状态
                 */
                //设置转场动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class),
                            ActivityOptions.makeSceneTransitionAnimation(WelcomeActivity.this).toBundle());
                    finish();
                } else {
                    //跳转到的Activity
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }

            }
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

}
