package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ExpertBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBserAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-06.
 */

public class ExpertAdapter extends MyBserAdapter<ExpertBean.DataBean.ItemsBean> {
    private final Context context;

    public ExpertAdapter(Context context, List<ExpertBean.DataBean.ItemsBean> datas) {
        super(context, datas);
        this.context = context;
    }

    @Override
    public BaseViewHolder getHolder() {
        return new ViewHolder();
    }

    class ViewHolder extends BaseViewHolder<ExpertBean.DataBean.ItemsBean> {

        @InjectView(R.id.expert_iv)
        ImageView expertIv;
        @InjectView(R.id.expert_name)
        TextView expertName;
        @InjectView(R.id.expert_profession)
        TextView expertProfession;

        @Override
        protected void setContent(ExpertBean.DataBean.ItemsBean itemsBean) {
            String orig = itemsBean.getUser_images().getOrig();
            HttpUtils.requestImage(context, orig, expertIv);
            String username = itemsBean.getUsername();
            expertName.setText(username);
            String duty = itemsBean.getDuty();
            expertProfession.setText(duty);

        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_expert, null);
        }
    }
}
