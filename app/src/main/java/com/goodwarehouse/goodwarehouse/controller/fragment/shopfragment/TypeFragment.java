package com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.ShopTypeBean;
import com.goodwarehouse.goodwarehouse.controller.activity.CommodityActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.TypeAdabter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class TypeFragment extends BaseFragment {
    @InjectView(R.id.shop_type_gv)
    GridView shopTypeGv;
    private TypeAdabter myGridAdabter;
    private List<ShopTypeBean.DataBean.ItemsBean> items;
    String[] urls = {NetRequestSite.HOME_URL, NetRequestSite.FITMENT_URL,
            NetRequestSite.STATIONERY_URL, NetRequestSite.NUMERICAL_URL,
            NetRequestSite.PLAY_URL, NetRequestSite.KITCHEN_URL,
            NetRequestSite.CATE_URL, NetRequestSite.MENWEAR_URL,
            NetRequestSite.FROCK_URL, NetRequestSite.BABYWEAR_URL,
            NetRequestSite.SHOE_URL, NetRequestSite.ACC_URL,
            NetRequestSite.BEAUTY_URL, NetRequestSite.OUTDOORS_URL,
            NetRequestSite.PLANT_URL, NetRequestSite.BOOK_URL,
            NetRequestSite.GIFT_URL, NetRequestSite.RECOMMEND_URL,
            NetRequestSite.ART_URL};

    @Override
    public String getUrl() {
        return NetRequestSite.TYPE_URL;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_type;
    }

    @Override
    public void initListener() {
        shopTypeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = urls[i];
                Intent intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, url);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public void initData() {
    }


    @Override
    public void processData(String response) {
        ShopTypeBean shopTypeInfo = JSON.parseObject(response, ShopTypeBean.class);
        items = shopTypeInfo.getData().getItems();
        myGridAdabter = new TypeAdabter(context, items,1);
        shopTypeGv.setAdapter(myGridAdabter);
    }
}
