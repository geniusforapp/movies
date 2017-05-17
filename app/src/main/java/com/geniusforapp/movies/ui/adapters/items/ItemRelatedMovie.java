package com.geniusforapp.movies.ui.adapters.items;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by anajar on 5/16/17.
 */

public class ItemRelatedMovie extends AbstractItem<ItemRelatedMovie, ItemRelatedMovie.RelatedMovieViewHolder> {


    protected Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public ItemRelatedMovie setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public RelatedMovieViewHolder getViewHolder(View v) {
        return new RelatedMovieViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.item_related_movie;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_related_movie;
    }

    @Override
    public void bindView(RelatedMovieViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        Context context = holder.itemView.getContext();
        Glide.with(context).load(context.getString(R.string.image) + getMovie().getBackdropPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).bitmapTransform(new BlurTransformation(context, 8)).into(holder.imagePoster);
        holder.title.setText(getMovie().getOriginalTitle());

    }

    public static class RelatedMovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_poster)
        ImageView imagePoster;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.card)
        public CardView card;

        public RelatedMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
