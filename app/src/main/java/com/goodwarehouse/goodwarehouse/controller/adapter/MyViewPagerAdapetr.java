package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.goodwarehouse.goodwarehouse.controller.activity.CommodityDetailsActivity;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

/**
 * Created by HaoMeng on 2017-07-11.
 */

public class MyViewPagerAdapetr extends PagerAdapter {
    private final Context context;
    private final List<String> imagesItem;

    public MyViewPagerAdapetr(Context context, List<String> images_item) {
        this.context = context;
        this.imagesItem = images_item;

    }

    @Override
    public int getCount() {
        return imagesItem == null ? 0 : imagesItem.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        HttpUtils.loadImage(context, imagesItem.get(position), imageView);
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
