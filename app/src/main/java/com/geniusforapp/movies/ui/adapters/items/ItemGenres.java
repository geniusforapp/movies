package com.geniusforapp.movies.ui.adapters.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Genre;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.robertlevonyan.views.chip.Chip;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anajar on 5/16/17.
 */

public class ItemGenres extends AbstractItem<ItemGenres, ItemGenres.GenresViewHolder> {


    Genre genre;


    public Genre getGenre() {
        return genre;
    }

    public ItemGenres setGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    @Override
    public GenresViewHolder getViewHolder(View v) {
        return new GenresViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.item_genres;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_genres;
    }

    @Override
    public void bindView(GenresViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        holder.chip.setChipText(getGenre().getName());
    }

    public static class GenresViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chip)
        Chip chip;

        public GenresViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
