package com.goodwarehouse.goodwarehouse.controller.fragment.expertfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ExpertAttentionFansBean;
import com.goodwarehouse.goodwarehouse.bean.ExpertLikeCommendBean;
import com.goodwarehouse.goodwarehouse.controller.activity.ExpertCommodityActivity;
import com.goodwarehouse.goodwarehouse.controller.activity.ExpertDetailsActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.ExpertLikeCommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-10.
 */

public class ExpertLikeFragment extends BaseFragment {
    @InjectView(R.id.expert_details_rv)
    RecyclerView expertDetailsRv;
    private String likeUrl;
    private String like_count;
    private String recommendation_count;
    private String followed_count;
    private String following_count;
    ExpertDetailsActivity expertDetails;
    private ExpertLikeCommendAdapter adapter;


    public String getLike_count() {
        return like_count;
    }

    public String getRecommendation_count() {
        return recommendation_count;
    }

    public String getFollowed_count() {
        return followed_count;
    }

    public String getFollowing_count() {
        return following_count;
    }

    @Override
    public String getUrl() {
        Log.e("URL", likeUrl);
        return likeUrl;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expert_details;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        expertDetails = (ExpertDetailsActivity) getActivity();
        int recordProition = expertDetails.recordProition;
        if (recordProition == 0) {
            Log.e("TAG", "" + recordProition);
            likeUrl = expertDetails.getLikeUrl();
        } else {
            likeUrl = expertDetails.getRecommendUrl();
        }

    }

    @Override
    public void processData(String response) {
        super.processData(response);
        Log.e("response", response);
        final ExpertLikeCommendBean expertLikeCommendBean = JSON.parseObject(response, ExpertLikeCommendBean.class);
        countData(expertLikeCommendBean.getData().getItems());
        List<ExpertLikeCommendBean.DataBean.ItemsBean.GoodsBean> goods = expertLikeCommendBean.getData().getItems().getGoods();
        adapter = new ExpertLikeCommendAdapter(context, goods);
        expertDetailsRv.setAdapter(adapter);
        expertDetailsRv.setLayoutManager(new GridLayoutManager(context, 2));
        adapter.setOnItemClickListener(new ExpertLikeCommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ExpertLikeCommendBean.DataBean.ItemsBean items = expertLikeCommendBean.getData().getItems();
                String goods_id = items.getGoods().get(position).getGoods_id();
                Intent intent = new Intent(context, ExpertCommodityActivity.class);
                intent.putExtra(ID, goods_id);
                Log.e("AAAAAAA", goods_id);
                startActivity(intent);
            }
        });
    }

    private void countData(ExpertLikeCommendBean.DataBean.ItemsBean expertLikeCommendBean) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("Items", expertLikeCommendBean);
        Message message = expertDetails.handler.obtainMessage(1, bundle);
        expertDetails.handler.sendMessage(message);
    }

}
