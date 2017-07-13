package com.goodwarehouse.goodwarehouse.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseActivity;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.MagazineProductionItemBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.MagazinesAuthorAdapter;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MagazinesAuthorActivity extends BaseActivity {
    @InjectView(R.id.magazines_author_rv)
    RecyclerView magazinesAuthorRv;


    @InjectView(R.id.title_back)
    ImageView titleBack;
    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.magazines_classify)
    ImageView magazinesClassify;

    private String[] keys;
    /**
     * 集合里边套着一个集合
     */
    private ArrayList<ArrayList<MagazineProductionItemBean>> beans;

    private ArrayList<MagazineProductionItemBean> beanitems;

    private String authorId;
    private MagazinesAuthorAdapter authorAdapter;
    private Intent intent;

    @Override
    public void initTitle() {
        intent = getIntent();
        titleBack.setVisibility(View.VISIBLE);
        String author_name = intent.getStringExtra("author_name");
        titleText.setText("杂志" + "·" + author_name);
        magazinesClassify.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titleText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MagazinesAuthorActivity.this, MagazinesActivity.class));
                finish();
            }
        });
    }

    @Override
    public void initData() {

        intent.getStringExtra(BaseFragment.AUTHOR_ID);
        String url = NetRequestSite.MAGAZINE_AUTHOR_FROEPART_URL + authorId + NetRequestSite.MAGAZINE_AUTHOR_END_URL;
        HttpUtils.HttpNet(url, new HttpUtils.onNetRequestContent() {
            @Override
            public void onError(String content) {
                Log.e("MagazinesAuthorActivity", content);
            }

            @Override
            public void onResponse(String response) {
                processData(response);
            }
        });

    }

    public void processData(String json) {
        beans = new ArrayList<>();
        beanitems = new ArrayList<>();

        try {
            JSONObject object = new JSONObject(json);
            if (object != null) {
                JSONObject data = object.optJSONObject("data");
                if (data != null) {
                    JSONObject items = data.optJSONObject("items");
                    if (items != null) {
                        JSONArray arrs = items.optJSONArray("keys");
                        if (arrs != null && arrs.length() > 0) {
                            keys = new String[arrs.length()];
                            for (int i = 0; i < arrs.length(); i++) {
                                keys[i] = (String) arrs.opt(i);
                            }
                            JSONObject infos = items.optJSONObject("infos");
                            for (int i = 0; i < keys.length; i++) {
                                JSONArray jsonArray = infos.optJSONArray(keys[i]);
                                ArrayList<MagazineProductionItemBean> list = new ArrayList<>();
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonObject = jsonArray.optJSONObject(j);
                                    MagazineProductionItemBean productionBean = new MagazineProductionItemBean();
                                    productionBean.setAccess_url(jsonObject.optString("access_url"));
                                    productionBean.setTaid(jsonObject.optString("taid"));
                                    productionBean.setTopic_name(jsonObject.optString("topic_name"));
                                    productionBean.setCat_id(jsonObject.optString("cat_id"));
                                    productionBean.setAuthor_id(jsonObject.optString("author_id"));
                                    productionBean.setTopic_url(jsonObject.optString("topic_url"));
                                    productionBean.setCover_img(jsonObject.optString("cover_img"));
                                    productionBean.setCover_img_new(jsonObject.optString("cover_img_new"));
                                    productionBean.setHit_number(jsonObject.optInt("hit_number"));
                                    productionBean.setAddtime(jsonObject.optString("addtime"));
                                    productionBean.setContent(jsonObject.optString("content"));
                                    productionBean.setNav_title(jsonObject.optString("nav_title"));
                                    productionBean.setAuthor_name(jsonObject.optString("author_name"));
                                    productionBean.setCat_name(jsonObject.optString("cat_name"));
                                    list.add(productionBean);
                                }
                                if (list != null && list.size() > 0) {
                                    beans.add(list);
                                    beanitems.addAll(list);

                                }
                            }
//                            if (beans != null && beans.size() > 0) {
//                                adapter.refresh(beans, keys);
//                            }
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /**
         * 解析数据成功后 设置 数据
         */
        setData(beanitems);

    }

    private void setData(final ArrayList<MagazineProductionItemBean> beanitems) {
        authorAdapter = new MagazinesAuthorAdapter(MagazinesAuthorActivity.this, beanitems);
        magazinesAuthorRv.setAdapter(authorAdapter);
        magazinesAuthorRv.setLayoutManager(new LinearLayoutManager(MagazinesAuthorActivity.this));
        authorAdapter.setOnItemClickListener(new MagazinesAuthorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String topic_url = beanitems.get(position).getTopic_url();
                String cat_name = beanitems.get(position).getCat_name();
                Intent intent = new Intent(MagazinesAuthorActivity.this, WebPageActivity.class);
                intent.putExtra(BaseFragment.PIC_URL, topic_url);
                startActivity(intent);
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_magazines_author;
    }

}
