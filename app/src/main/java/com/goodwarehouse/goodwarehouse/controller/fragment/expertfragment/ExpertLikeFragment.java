package com.goodwarehouse.goodwarehouse.controller.fragment.expertfragment;

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
        ExpertLikeCommendBean expertLikeCommendBean = JSON.parseObject(response, ExpertLikeCommendBean.class);
        countData(expertLikeCommendBean.getData().getItems());
        List<ExpertLikeCommendBean.DataBean.ItemsBean.GoodsBean> goods = expertLikeCommendBean.getData().getItems().getGoods();
        ExpertLikeCommendAdapter adapter = new ExpertLikeCommendAdapter(context, goods);
        expertDetailsRv.setAdapter(adapter);
        expertDetailsRv.setLayoutManager(new GridLayoutManager(context, 2));
    }

    private void countData(ExpertLikeCommendBean.DataBean.ItemsBean expertLikeCommendBean) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("Items", expertLikeCommendBean);
        Message message = expertDetails.handler.obtainMessage(1, bundle);
        expertDetails.handler.sendMessage(message);
    }

}
