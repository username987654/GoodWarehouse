package com.goodwarehouse.goodwarehouse.controller.fragment.shoppager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.shopbean.ShopTypeBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.shopadapter.TypeAdabter;
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
                Toast.makeText(context, "" + items.get(i).getCat_name(), Toast.LENGTH_SHORT).show();
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
        myGridAdabter = new TypeAdabter(context, items);
        shopTypeGv.setAdapter(myGridAdabter);
    }
}
