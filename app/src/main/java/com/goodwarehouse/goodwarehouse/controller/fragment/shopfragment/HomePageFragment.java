package com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ShopHomePageBean;
import com.goodwarehouse.goodwarehouse.controller.activity.WebPageActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.HomePageAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class HomePageFragment extends BaseFragment {
    @InjectView(R.id.shop_homepage_rv)
    RecyclerView shopHomepageRv;
    private HomePageAdapter homePageAdapter;
    private List<ShopHomePageBean.DataBean.ItemsBean.ListBeanX> list;


    private String topic_name;
    private String topic_url;

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public String getUrl() {
        return NetRequestSite.HOME_PAGE_URL;
    }

    @Override
    public void processData(String response) {
        super.processData(response);
        ShopHomePageBean shopHomePageBean = JSON.parseObject(response, ShopHomePageBean.class);
        list = shopHomePageBean.getData().getItems().getList();
        homePageAdapter = new HomePageAdapter(context, list);
        shopHomepageRv.setAdapter(homePageAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        shopHomepageRv.setLayoutManager(layoutManager);
        homePageAdapter.setOnItemClickListener(new HomePageAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, final int position, int type) {
                Intent intent = null;
                if (type == 1) {
                    topic_name = list.get(position).getOne().getTopic_name();
                    topic_url = list.get(position).getOne().getTopic_url();
                    startWebActivity(topic_name, topic_url);
                } else if (type == 2) {
                    switch (view.getId()) {
                        case R.id.homepage_type2_left_iv:
                            topic_name = list.get(position).getOne().getTopic_name();
                            topic_url = list.get(position).getOne().getTopic_url();
                            startWebActivity(topic_name, topic_url);
                            break;
                        case R.id.homepage_type2_right_iv:
                            topic_name = list.get(position).getTwo().getTopic_name();
                            topic_url = list.get(position).getTwo().getTopic_url();
                            startWebActivity(topic_name, topic_url);
                            break;
                    }
                } else if (type == 3) {

                } else if (type == 4) {
                    switch (view.getId()) {
                        case R.id.homepage_type4_left1:
                            topic_name = list.get(position).getOne().getTopic_name();
                            topic_url = list.get(position).getOne().getTopic_url();
                            startWebActivity(topic_name, topic_url);
                            break;
                        case R.id.homepage_type4_left2:
                            topic_name = list.get(position).getTwo().getTopic_name();
                            topic_url = list.get(position).getTwo().getTopic_url();
                            startWebActivity(topic_name, topic_url);
                            break;
                        case R.id.homepage_type4_right1:
                            topic_name = list.get(position).getThree().getTopic_name();
                            topic_url = list.get(position).getThree().getTopic_url();
                            startWebActivity(topic_name, topic_url);
                            break;
                        case R.id.homepage_type4_right2:
                            topic_name = list.get(position).getFour().getTopic_name();
                            topic_url = list.get(position).getFour().getTopic_url();
                            startWebActivity(topic_name, topic_url);
                            break;
                    }
                }

            }
        });

    }

    private void startWebActivity(String topic_name, String pic_url) {
        Intent intent;
        intent = new Intent(context, WebPageActivity.class);
        intent.putExtra(PIC_URL, pic_url);
        intent.putExtra(TOPIC_NAME, topic_name);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_homepage;
    }

}
