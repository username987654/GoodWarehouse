package com.goodwarehouse.goodwarehouse.controller.fragment.expertfragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.goodwarehouse.goodwarehouse.bean.ExpertDataBean;
import com.goodwarehouse.goodwarehouse.controller.activity.ExpertDetailsActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.ExpertAttentionFansAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-10.
 */

public class ExpertAttentionFragment extends BaseFragment {
    @InjectView(R.id.expert_details_rv)
    RecyclerView expertDetailsRv;
    private String url;
    private ExpertAttentionFansAdapter adapter;


    @Override
    public String getUrl() {
        return url;
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
        ExpertDetailsActivity expertDetails = (ExpertDetailsActivity) getActivity();
        int recordProition = expertDetails.recordProition;
        if (recordProition == 2) {
            url = expertDetails.getAttentionUrl();
        } else {
            url = expertDetails.getFansUrl();
        }
    }

    @Override
    public void processData(String response) {
        Log.e("TAG", response);
        final ExpertAttentionFansBean expertAttentionFansBean = JSON.parseObject(response, ExpertAttentionFansBean.class);
        List<ExpertAttentionFansBean.DataBean.ItemsBean.UsersBean> users = expertAttentionFansBean.getData().getItems().getUsers();
        adapter = new ExpertAttentionFansAdapter(context, users);
        expertDetailsRv.setAdapter(adapter);
        expertDetailsRv.setLayoutManager(new GridLayoutManager(context, 3));
        adapter.setOnItemClickListener(new ExpertAttentionFansAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
//                String user_name = expertAttentionFansBean.getData().getItems().getUser_name();
//                String user_desc = expertAttentionFansBean.getData().getItems().getUser_desc();
//                String mid = expertAttentionFansBean.getData().getItems().getUser_image().getMid();
                String user_name = expertAttentionFansBean.getData().getItems().getUsers().get(position).getUser_name();
                String user_desc = expertAttentionFansBean.getData().getItems().getUsers().get(position).getUser_desc();
                String mid = expertAttentionFansBean.getData().getItems().getUsers().get(position).getUser_image().getMid();

                String user_id = expertAttentionFansBean.getData().getItems().getUsers().get(position).getUser_id();
                ExpertDataBean expertDataBean = new ExpertDataBean(user_id, user_name, user_desc, mid);
                Intent intent = new Intent(context, ExpertDetailsActivity.class);
                intent.putExtra(EXPERTUBEAN, expertDataBean);
                getActivity().startActivity(intent);
            }
        });
    }

}
