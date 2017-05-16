package com.geniusforapp.movies.ui.adapters.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geniusforapp.movies.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by anajar on 5/16/17.
 */

public class ItemProgress extends AbstractItem<ItemProgress, ItemProgress.ItemProgressViewHolder> {

    @Override
    public ItemProgressViewHolder getViewHolder(View v) {
        return new ItemProgressViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.item_progress;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_progress;
    }


    @Override
    public void bindView(ItemProgressViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        
    }

    public static class ItemProgressViewHolder extends RecyclerView.ViewHolder {

        public ItemProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
