package com.goodwarehouse.goodwarehouse.controller.fragment.shopfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;
import com.goodwarehouse.goodwarehouse.controller.activity.CommodityActivity;
import com.goodwarehouse.goodwarehouse.utils.NetRequestSite;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class GiftFragment extends BaseFragment implements View.OnClickListener {
    @InjectView(R.id.shop_gift_sift)
    ImageView shopGiftSift;
    @InjectView(R.id.shop_gift_festival)
    ImageView shopGiftFestival;
    @InjectView(R.id.shop_gift_love)
    ImageView shopGiftLove;
    @InjectView(R.id.shop_gift_birthday)
    ImageView shopGiftBirthday;
    @InjectView(R.id.shop_gift_friend)
    ImageView shopGiftFriend;
    @InjectView(R.id.shop_gift_baby)
    ImageView shopGiftBaby;
    @InjectView(R.id.shop_gift_parent)
    ImageView shopGiftParent;
    @InjectView(R.id.shop_gift_remind)
    ImageView shopGiftRemind;
    String[] path = {NetRequestSite.GIFTSIFT_URL, NetRequestSite.FESTIVAL_URL,
            NetRequestSite.LOVE_URL, NetRequestSite.BIRTHDAY_URL,
            NetRequestSite.FRIEND_URL, NetRequestSite.BABY_URL,
            NetRequestSite.PARENT_URL};
    private Intent intent;

    @Override
    public void initListener() {
        shopGiftSift.setOnClickListener(this);
        shopGiftFestival.setOnClickListener(this);
        shopGiftLove.setOnClickListener(this);
        shopGiftBirthday.setOnClickListener(this);
        shopGiftFriend.setOnClickListener(this);
        shopGiftBaby.setOnClickListener(this);
        shopGiftParent.setOnClickListener(this);
        shopGiftRemind.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {

    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_gift;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shop_gift_sift:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[0]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_festival:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[1]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_love:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[2]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_birthday:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[3]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_friend:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[4]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_baby:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[5]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_parent:
                intent = new Intent(context, CommodityActivity.class);
                intent.putExtra(URL, path[6]);
                context.startActivity(intent);
                break;
            case R.id.shop_gift_remind:
                showToash("送礼提醒");
                break;

        }
    }
}
