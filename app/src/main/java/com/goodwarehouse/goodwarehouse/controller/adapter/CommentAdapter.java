package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ConnotationTextBean;
import com.goodwarehouse.goodwarehouse.controller.adapter.baseadapter.MyBaseAdapter;
import com.goodwarehouse.goodwarehouse.controller.adapter.holder.BaseViewHolder;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-14.
 */

public class CommentAdapter extends MyBaseAdapter<ConnotationTextBean.ListBean.TopCommentsBean> {


    private final Context context;
    private final List<ConnotationTextBean.ListBean.TopCommentsBean> datas;

    public CommentAdapter(Context context, List<ConnotationTextBean.ListBean.TopCommentsBean> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
    }


    @Override
    public BaseViewHolder getHolder() {
        return new ViewHolder();
    }

    class ViewHolder extends BaseViewHolder<ConnotationTextBean.ListBean.TopCommentsBean> {
        @InjectView(R.id.comment_user_name)
        TextView commentUserName;
        @InjectView(R.id.comment_content)
        TextView commentContent;

        @Override
        protected void setContent(ConnotationTextBean.ListBean.TopCommentsBean topCommentsBean) {
            String name = topCommentsBean.getU().getName();
            String content = topCommentsBean.getContent();
            commentUserName.setText(name + " :");
            commentContent.setText(content);

        }

        @Override
        public View initView() {
            return View.inflate(context, R.layout.item_commext, null);
        }
    }

}
