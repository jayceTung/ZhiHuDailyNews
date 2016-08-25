package com.asuper.zhihudailynews.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.asuper.zhihudailynews.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Super on 2016/8/25.
 */
public class DailyListAdapter extends RecyclerView.Adapter<DailyListAdapter.ItemContentViewHolder> {
    private static final String TAG = "DailyListAdapter";

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public ItemContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemContentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemContentViewHolder extends RecyclerView.ViewHolder {

        public ItemContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Bind(R.id.card_view)
        CardView mLayout;

        @Bind(R.id.item_image)
        SimpleDraweeView mPic;

        @Bind(R.id.item_title)
        TextView mTitle;

        @Bind(R.id.item_more_pic)
        ImageView mMorePic;
    }
}
