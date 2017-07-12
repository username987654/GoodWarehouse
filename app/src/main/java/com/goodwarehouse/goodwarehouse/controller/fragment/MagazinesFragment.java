package com.goodwarehouse.goodwarehouse.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.bean.MagazineProductionItemBean;
import com.goodwarehouse.goodwarehouse.controller.activity.WebPageActivity;
import com.goodwarehouse.goodwarehouse.controller.adapter.MagazinesAdapter;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by HaoMeng on 2017-07-05.
 */

public class MagazinesFragment extends BaseFragment {

    @InjectView(R.id.magazines_rv)
    RecyclerView magazinesRv;

    @InjectView(R.id.title_text)
    TextView titleText;
    @InjectView(R.id.rl_title)
    RelativeLayout rltitle;
    @InjectView(R.id.magazines_classify)
    ImageView magazinesClassify;
    @InjectView(R.id.title_switcher)
    TextSwitcher titleSwitcher;

    private String[] keys;
    /**
     * 集合里边套着一个集合
     */
    private ArrayList<ArrayList<MagazineProductionItemBean>> beans;

    private ArrayList<MagazineProductionItemBean> beanitems;
    private MagazinesAdapter magazinesAdapter;


    @Override
    public void initListener() {
        rltitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        titleText.setText("杂志");
        magazinesClassify.setVisibility(View.VISIBLE);
        titleSwitcher.setVisibility(View.VISIBLE);
        titleSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(context);
                tv.setTextColor(Color.WHITE);
                return tv;
            }
        });


    }

    @Override
    public String getUrl() {
        return NetRequestSite.MAGAZINE_URL;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_magazines;
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
        String addtime = beanitems.get(0).getAddtime();
        titleSwitcher.setText(addtime.substring(0, addtime.indexOf(" ")));

        /**
         * 解析数据成功后 设置 数据
         */
//        setData();
        magazinesAdapter = new MagazinesAdapter(context, beanitems);
        magazinesRv.setAdapter(magazinesAdapter);
        magazinesRv.setLayoutManager(new LinearLayoutManager(context));
        TextView tv = new TextView(context);
        tv.setTextColor(Color.WHITE);
        titleSwitcher.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_bottom));
        titleSwitcher.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_up));


        magazinesRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public String tempAddtime;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    String addtime = beanitems.get(firstVisibleItemPosition).getAddtime();
                    if (!addtime.equals(tempAddtime)) {
                        titleSwitcher.setText(addtime.substring(0, addtime.indexOf(" ")));
                    }
                    tempAddtime = addtime;
                }
            }
        });
        magazinesAdapter.setOnItemClickListener(new MagazinesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String topic_url = beanitems.get(position).getTopic_url();
                Intent intent = new Intent(context, WebPageActivity.class);
                intent.putExtra(PIC_URL, topic_url);
                startActivity(intent);
            }
        });
    }

}

