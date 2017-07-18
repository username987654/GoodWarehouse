package com.goodwarehouse.goodwarehouse.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.goodwarehouse.goodwarehouse.R;
import com.goodwarehouse.goodwarehouse.bean.ConnotationTextBean;
import com.goodwarehouse.goodwarehouse.utils.HttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by HaoMeng on 2017-07-13.
 */

public class ConnTextAdapter extends BaseAdapter {


    private final Context context;
    private final List<ConnotationTextBean.ListBean> list;

    public ConnTextAdapter(Context context, List<ConnotationTextBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.item_connotation_text, null);
        ViewHolder viewHolder = new ViewHolder(view);
        ConnotationTextBean.ListBean listBean = list.get(i);
        ConnotationTextBean.ListBean.UBean user = listBean.getU();
        String url = user.getHeader().get(0);
        HttpUtils.loadCricleImage(context, url, viewHolder.userTitleHeadimage);
        String name = user.getName();
        viewHolder.userTitleName.setText(name);
        String passtime = listBean.getPasstime();
        viewHolder.userTitleTime.setText(passtime);
        String content = listBean.getText();
        viewHolder.shareContent.setText(content);
        String up = listBean.getUp();
        int down = listBean.getDown();
        int forward = listBean.getForward();
        String comment = listBean.getComment();
        viewHolder.praiseCount.setText(up);
        viewHolder.noLikeText.setText("" + down);
        viewHolder.shareCount.setText("" + forward);
        viewHolder.commentText.setText(comment);
        viewHolder.commentContent.setAdapter(new CommentAdapter(context, listBean.getTop_comments()));
        setListViewHeight(viewHolder.commentContent);
        return view;
    }


    public void setListViewHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int totalHerght = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listViewItem = adapter.getView(i, null, listView);
            listViewItem.measure(0, 0);
            totalHerght += listViewItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHerght + (listView.getDividerHeight() * adapter.getCount());
        listView.setLayoutParams(params);

    }

    static class ViewHolder {
        @InjectView(R.id.user_title_headimage)
        ImageView userTitleHeadimage;
        @InjectView(R.id.user_title_name)
        TextView userTitleName;
        @InjectView(R.id.user_title_time)
        TextView userTitleTime;
        @InjectView(R.id.user_title)
        LinearLayout userTitle;
        @InjectView(R.id.share_content)
        TextView shareContent;
        @InjectView(R.id.praise_image)
        ImageView praiseImage;
        @InjectView(R.id.praise_count)
        TextView praiseCount;
        @InjectView(R.id.linear_praise)
        LinearLayout linearPraise;
        @InjectView(R.id.no_like_image)
        ImageView noLikeImage;
        @InjectView(R.id.no_like_text)
        TextView noLikeText;
        @InjectView(R.id.ll_no_like)
        LinearLayout llNoLike;
        @InjectView(R.id.share_image)
        ImageView shareImage;
        @InjectView(R.id.share_count)
        TextView shareCount;
        @InjectView(R.id.ll_share)
        LinearLayout llShare;
        @InjectView(R.id.comment_image)
        ImageView commentImage;
        @InjectView(R.id.comment_text)
        TextView commentText;
        @InjectView(R.id.ll_comment)
        LinearLayout llComment;
        @InjectView(R.id.comment_content)
        ListView commentContent;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
