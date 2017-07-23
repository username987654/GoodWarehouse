package com.goodwarehouse.goodwarehouse.utils;

import android.content.Context;

import com.goodwarehouse.goodwarehouse.R;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by HaoMeng on 2017-07-22.
 */

public class ShareUtils {
    private static String shareText;
    private static String shareTitleUrl;
    private static String shareImagePath;
    private static String shareUrl;
    private static String shareComment;
    private static String shareSiteUrl;


    public static void showShare(Context context) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(context.getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(getShareTitleUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(getShareText());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(getShareImagePath());//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(getShareUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(getShareComment());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(getShareSiteUrl());

// 启动分享GUI
        oks.show(context);
    }

    public static String getShareText() {
        return shareText;
    }

    public static void setShareText(String shareText) {
        ShareUtils.shareText = shareText;
    }

    public static String getShareTitleUrl() {
        return shareTitleUrl;
    }

    public static void setShareTitleUrl(String shareTitleUrl) {
        ShareUtils.shareTitleUrl = shareTitleUrl;
    }

    public static String getShareImagePath() {
        return shareImagePath;
    }

    public static void setShareImagePath(String shareImagePath) {
        ShareUtils.shareImagePath = shareImagePath;
    }

    public static String getShareUrl() {
        return shareUrl;
    }

    public static void setShareUrl(String shareUrl) {
        ShareUtils.shareUrl = shareUrl;
    }

    public static String getShareComment() {
        return shareComment;
    }

    public static void setShareComment(String shareComment) {
        ShareUtils.shareComment = shareComment;
    }

    public static String getShareSiteUrl() {
        return shareSiteUrl;
    }

    public static void setShareSiteUrl(String shareSiteUrl) {
        ShareUtils.shareSiteUrl = shareSiteUrl;
    }


}
