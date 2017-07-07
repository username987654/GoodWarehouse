package com.goodwarehouse.goodwarehouse.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.goodwarehouse.goodwarehouse.R;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class HttpUtils {

    public static void HttpNet(String url, final onNetRequestContent onNetRequestContent) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        onNetRequestContent.onError(e.getMessage());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        onNetRequestContent.onResponse(response);
                    }
                });
    }

    public interface onNetRequestContent {
        void onError(String content);

        void onResponse(String response);
    }

    public static void loadImage(Context context, String url, ImageView iv) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.bg_topic_favour)
                .placeholder(R.drawable.bg_topic_favour)
                .into(iv);
    }

    public static void loadCricleImage(Context context, String url, ImageView iv) {
        Picasso.with(context)
                .load(url)
                .error(R.drawable.bg_topic_favour)
                .transform(new CircleTransform()).into(iv);
    }
}
