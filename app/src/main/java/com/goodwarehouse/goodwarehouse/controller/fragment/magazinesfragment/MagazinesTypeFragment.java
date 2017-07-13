package com.goodwarehouse.goodwarehouse.controller.fragment.magazinesfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.MagazinesAuthorBean;
import com.goodwarehouse.goodwarehouse.bean.ShopTypeBean;
import com.goodwarehouse.goodwarehouse.controller.activity.MagazinesAuthorActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.MagazinesTypeAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.TypeAdabter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-12.
 */

public class MagazinesTypeFragment extends BaseFragment {
    @InjectView(R.id.shop_type_gv)
    GridView shopTypeGv;
    private List<MagazinesAuthorBean.DataBean.ItemsBean> items;

    @Override
    public String getUrl() {
        return NetRequestSite.MAGAZINE_AUTHOR_URL;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_magazines_type;
    }

    @Override
    public void initListener() {
        shopTypeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String author_id = items.get(i).getAuthor_id();
                String author_name = items.get(i).getAuthor_name();
                Intent intent = new Intent(context, MagazinesAuthorActivity.class);
                intent.putExtra(AUTHOR_ID, author_id);
                intent.putExtra("author_name", author_name);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }

    @Override
    public void initData() {
    }

    @Override
    public void processData(String response) {
        MagazinesAuthorBean magazinesAuthorBean = JSON.parseObject(response, MagazinesAuthorBean.class);
        items = magazinesAuthorBean.getData().getItems();

        if (items != null && items.size() > 0) {
            MagazinesTypeAdapter typeAdapter = new MagazinesTypeAdapter(context, items);
            shopTypeGv.setAdapter(typeAdapter);

        }
    }
}
