package com.goodwarehouse.goodwarehouse.controller.fragment.shoppager;

import android.view.View;
import android.widget.ImageView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.base.BaseFragment;

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

    @Override
    public void initListener() {
        shopGiftSift.setOnClickListener(this);
        shopGiftFestival.setOnClickListener(this);
        shopGiftLove.setOnClickListener(this);
        shopGiftBirthday.setOnClickListener(this);
        shopGiftFriend.setOnClickListener(this);
        shopGiftBaby.setOnClickListener(this);
        shopGiftParent.setOnClickListener(this);
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
                showToash("礼物精选");
                break;
            case R.id.shop_gift_festival:
                showToash("节日");
                break;
            case R.id.shop_gift_love:
                showToash("爱情");
                break;
            case R.id.shop_gift_birthday:
                showToash("生日");
                break;
            case R.id.shop_gift_friend:
                showToash("朋友");
                break;
            case R.id.shop_gift_baby:
                showToash("孩子");
                break;
            case R.id.shop_gift_parent:
                showToash("父母");
                break;

        }
    }
}
