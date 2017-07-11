package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ExpertDataBean;
import com.goodwarehouse.goodwarehouse.bean.ExpertLikeCommendBean;
import com.goodwarehouse.goodwarehouse.controller.fragment.expertfragment.ExpertAttentionFragment;
import com.goodwarehouse.goodwarehouse.controller.fragment.expertfragment.ExpertLikeFragment;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

public class ExpertDetailsActivity extends BaseActivity implements View.OnClickListener {

    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.expert_image)
    ImageView expertImage;
    @InjectView(R.id.expert_author)
    TextView expertAuthor;
    @InjectView(R.id.expert_occupation)
    TextView expertOccupation;
    @InjectView(R.id.expert_attention_tv)
    TextView expertAttentionTv;
    @InjectView(R.id.expert_private_letter)
    TextView expertPrivateLetter;


    @InjectView(R.id.like)
    LinearLayout like;

    @InjectView(R.id.recommend)
    LinearLayout recommend;

    @InjectView(R.id.attention)
    LinearLayout attention;

    @InjectView(R.id.fans)
    LinearLayout fans;
    @InjectView(R.id.expert_like)
    TextView expertLike;
    @InjectView(R.id.like_count)
    TextView likeCount;
    @InjectView(R.id.expert_recommend)
    TextView expertRecommend;
    @InjectView(R.id.recommend_count)
    TextView recommendCount;
    @InjectView(R.id.expert_attention)
    TextView expertAttention;
    @InjectView(R.id.attention_count)
    TextView attentionCount;
    @InjectView(R.id.expert_fans)
    TextView expertFans;
    @InjectView(R.id.fans_count)
    TextView fansCount;
    private String likeUrl;
    private String recommendUrl;
    private String attentionUrl;
    private String fansUrl;
    public int recordProition = 1;
    private List<BaseFragment> fragments;
    private Fragment tempFragment;
    private List<String> countList;
    private ExpertDataBean dataBean;

    public String getRecommendUrl() {
        return recommendUrl;
    }

    public String getAttentionUrl() {
        return attentionUrl;
    }

    public String getFansUrl() {
        return fansUrl;
    }

    public String getLikeUrl() {
        return likeUrl;
    }

    @Override

    public void initTitle() {
        setTitle();
        defaultBg(recommend, expertRecommend, recommendCount);
    }


    private void setTitle() {
        Intent intent = getIntent();
        dataBean = (ExpertDataBean) intent.getSerializableExtra(BaseFragment.EXPERTUBEAN);
        titleBack.setVisibility(View.VISIBLE);
        titleText.setText(dataBean.getName());
        expertAuthor.setText(dataBean.getName());
        expertOccupation.setText(dataBean.getDuty());
        HttpUtils.loadImage(ExpertDetailsActivity.this, dataBean.getUser_images(), expertImage);
    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        like.setOnClickListener(this);
        recommend.setOnClickListener(this);
        attention.setOnClickListener(this);
        fans.setOnClickListener(this);

    }



    private void defaultBg(LinearLayout l, TextView tv, TextView count) {
        like.setBackgroundResource(R.color.defaultbg);
        recommend.setBackgroundResource(R.color.defaultbg);
        attention.setBackgroundResource(R.color.defaultbg);
        fans.setBackgroundResource(R.color.defaultbg);
        l.setBackgroundResource(R.color.text);
        int defaultbg = getResources().getColor(R.color.defaultbg);
        int color = getResources().getColor(R.color.text);
        expertLike.setTextColor(color);
        expertRecommend.setTextColor(color);
        expertAttention.setTextColor(color);
        expertFans.setTextColor(color);
        likeCount.setTextColor(color);
        recommendCount.setTextColor(color);
        attentionCount.setTextColor(color);
        fansCount.setTextColor(color);
        tv.setTextColor(defaultbg);
        count.setTextColor(defaultbg);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.like:
                defaultBg(like, expertLike, likeCount);
                recordProition = 0;
                break;
            case R.id.recommend:
                defaultBg(recommend, expertRecommend, recommendCount);
                recordProition = 1;
                break;
            case R.id.attention:
                defaultBg(attention, expertAttention, attentionCount);
                recordProition = 2;
                break;
            case R.id.fans:
                defaultBg(fans, expertFans, fansCount);
                recordProition = 3;
                break;
        }
        Fragment fragment = fragments.get(recordProition);
        showFragment(fragment);
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
                ft.add(R.id.expert_details_fm, currentFragment);
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
        String uid = dataBean.getId();
        likeUrl = NetRequestSite.EXPERT_LIKE_FROEPART_URL + uid + NetRequestSite.EXPERT_LIKE_END_URL;
        recommendUrl = NetRequestSite.EXPERT_RECOMMEND_FROEPART_URL + uid + NetRequestSite.TYPE_RECOMMEND_END_URL;
        fansUrl = NetRequestSite.EXPERT_ATTENTION_FROEPART_URL + uid + NetRequestSite.EXPERT_ATTENTION_END_URL;
        attentionUrl = NetRequestSite.EXPERT_FANS_FROEPART_URL + uid + NetRequestSite.EXPERT_FANS_END_URL;
        setFragment();
    }

    private void setFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ExpertLikeFragment());
        fragments.add(new ExpertLikeFragment());
        fragments.add(new ExpertAttentionFragment());
        fragments.add(new ExpertAttentionFragment());
        showFragment(fragments.get(1));
        ExpertLikeFragment fragment = (ExpertLikeFragment) fragments.get(1);
        likeCount.setText(fragment.getLike_count());
        recommendCount.setText(fragment.getRecommendation_count());
        attentionCount.setText(fragment.getFollowing_count());
        fansCount.setText(fragment.getFollowed_count());
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = (Bundle) msg.obj;
            ExpertLikeCommendBean.DataBean.ItemsBean items = (ExpertLikeCommendBean.DataBean.ItemsBean) bundle.getSerializable("Items");
            likeCount.setText(items.getLike_count());
            recommendCount.setText(items.getRecommendation_count());
            attentionCount.setText(items.getFollowing_count());
            fansCount.setText(items.getFollowed_count());
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_expert_details;
    }

}
