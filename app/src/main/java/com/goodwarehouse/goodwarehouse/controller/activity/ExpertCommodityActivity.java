package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ExpertCommodityBean;
import com.goodwarehouse.goodwarehouse.bean.ExpertDataBean;
import com.goodwarehouse.goodwarehouse.bean.ExpertLikeCommendBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExpertCommodityActivity extends BaseActivity {

    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.web_share)
    ImageView webShare;

    @InjectView(R.id.expert_commodity)
    ViewPager expertCommodity;
    @InjectView(R.id.expert_commodity_name)
    TextView expertCommodityName;
    @InjectView(R.id.expert_commodity_price)
    TextView expertCommodityPrice;
    @InjectView(R.id.like_image)
    ImageView likeImage;
    @InjectView(R.id.like_count)
    TextView likeCount;
    @InjectView(R.id.link)
    TextView link;
    @InjectView(R.id.author_image)
    ImageView authorImage;
    @InjectView(R.id.author_name)
    TextView authorName;
    @InjectView(R.id.activity_expert_commodity)
    LinearLayout activityExpertCommodity;
    @InjectView(R.id.ll_acthor)
    LinearLayout llActhor;
    private ExpertLikeCommendBean.DataBean.ItemsBean datas;
    private ExpertCommodityBean expertCommodityBean;

    @Override
    public void initTitle() {
        titleBack.setVisibility(View.VISIBLE);
        webShare.setVisibility(View.VISIBLE);
        titleText.setText("良品");
    }

    @Override
    public void initListener() {
        llActhor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = expertCommodityBean.getData().getItems().getOwner_name();
                String user_desc = expertCommodityBean.getData().getItems().getOwner_desc();
                String mid = expertCommodityBean.getData().getItems().getHeadimg();

                String user_id = expertCommodityBean.getData().getItems().getOwner_id();
                ExpertDataBean expertDataBean = new ExpertDataBean(user_id, user_name, user_desc, mid);
                Intent intent = new Intent(ExpertCommodityActivity.this, ExpertDetailsActivity.class);
                intent.putExtra(BaseFragment.EXPERTUBEAN, expertDataBean);
                startActivity(intent);
            }
        });

    }


    @Override
    public void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(BaseFragment.ID);
        String url = NetRequestSite.EXPERT_RECOMMEND_DTAILS_TITLE + id + NetRequestSite.EXPERT_RECOMMEND_DTAILS_END;
        HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
            @Override
            public void onError(String content) {
                Log.e("ExpertCommodityActivity", content);

            }

            @Override
            public void onResponse(String response) {
                Log.e("ExpertCommodityActivity", response);
                processData(response);
            }
        });


    }

    private void processData(String response) {
        expertCommodityBean = JSON.parseObject(response, ExpertCommodityBean.class);
        ExpertCommodityBean.DataBean.ItemsBean items = expertCommodityBean.getData().getItems();
        expertCommodityName.setText(items.getGoods_name());
        expertCommodityPrice.setText("￥" + items.getPrice());
        likeCount.setText(items.getLike_count());
        HttpUtils.loadImage(ExpertCommodityActivity.this, items.getHeadimg(), authorImage);
        authorName.setText(items.getOwner_name());
        List<String> images_item = items.getImages_item();
        expertCommodity.setAdapter(new ViewPagerAdapter(images_item));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_expert_commodity;
    }

    class ViewPagerAdapter extends PagerAdapter {

        private final List<String> datas;

        public ViewPagerAdapter(List<String> images_item) {
            this.datas = images_item;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(ExpertCommodityActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            HttpUtils.loadImage(ExpertCommodityActivity.this, datas.get(position), imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
