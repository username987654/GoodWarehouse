package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.MagazinesAuthorBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBaseAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-12.
 */

public class MAuthorAdapter extends MyBaseAdapter<MagazinesAuthorBean.DataBean.ItemsBean> {
    private final Context context;
    private final List<MagazinesAuthorBean.DataBean.ItemsBean> datas;

    public MAuthorAdapter(Context context, List<MagazinesAuthorBean.DataBean.ItemsBean> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new MyViewHolder();
    }

    class MyViewHolder extends BaseViewHolder<MagazinesAuthorBean.DataBean.ItemsBean> {
        @InjectView(R.id.author_iocn)
        ImageView authorIocn;
        @InjectView(R.id.author_name)
        TextView authorName;
        @InjectView(R.id.author_introduce)
        TextView authorIntroduce;

        @Override
        protected void setContent(MagazinesAuthorBean.DataBean.ItemsBean itemsBean) {
            authorName.setText(itemsBean.getAuthor_name());
            ;
            authorIntroduce.setText(itemsBean.getNote());
            HttpUtils.loadCricleImage(context, itemsBean.getThumb(), authorIocn);

        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_magezines_author, null);
        }
    }
}
