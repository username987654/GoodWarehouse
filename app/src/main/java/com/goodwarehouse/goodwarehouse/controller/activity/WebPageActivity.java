package com.goodwarehouse.goodwarehouse.controller.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment.HomePageFragment;
import com.goodwarehouse.goodwarehouse.utils.ShareUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebPageActivity extends BaseActivity {

    @InjectView(R.id.web_page)
    WebView webPage;

    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.web_like)
    ImageView webLike;
    @InjectView(R.id.web_share)
    ImageView webShare;
    private String pic_url;


    @Override
    public void initView() {
        String topic_name = getIntent().getStringExtra(BaseFragment.TOPIC_NAME);
        titleBack.setVisibility(View.VISIBLE);
        webLike.setVisibility(View.VISIBLE);
        webShare.setVisibility(View.VISIBLE);
        titleText.setText(topic_name);
    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        webShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareUtils.setShareImagePath(pic_url);
                ShareUtils.showShare(WebPageActivity.this);
            }
        });
    }

    @Override
    public void initData() {
        WebSettings settings = webPage.getSettings();
        //支持javascript
        settings.setJavaScriptEnabled(true);
        //支持缩放
        settings.setUseWideViewPort(true);
        //不跳转到系统的浏览器里
        webPage.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        pic_url = getIntent().getStringExtra(BaseFragment.PIC_URL);
        webPage.loadUrl(pic_url);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_page;
    }

}
