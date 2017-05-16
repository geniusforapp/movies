package com.geniusforapp.movies.ui.adapters.items;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anajar on 5/14/17.
 */

public class ItemMovie extends AbstractItem<ItemMovie, ItemMovie.MovieViewHolder> {


    Movie movie;


    public Movie getMovie() {
        return movie;
    }

    public ItemMovie setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public MovieViewHolder getViewHolder(View v) {
        return new MovieViewHolder(v);
    }

    @Override
    public int getType() {
        return R.id.item_movie;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_movie;
    }


    @Override
    public void bindView(MovieViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        Context context = holder.itemView.getContext();
        Glide.with(context).load(context.getString(R.string.image) + getMovie().getPosterPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.image);
        Glide.with(context).load(context.getString(R.string.image) + getMovie().getBackdropPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.backboardImage);
        holder.description.setText(getMovie().getOverview());
        holder.title.setText(getMovie().getOriginalTitle());

        Double rating = getMovie().getVoteAverage();
        holder.rating.setRating(Math.round(rating));
        holder.textRating.setText("Vote (" + String.valueOf(getMovie().getVoteCount()) + ")");
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        public ImageView image;
        @BindView(R.id.back_image)
        public ImageView backboardImage;
        @BindView(R.id.rating)
        RatingBar rating;
        @BindView(R.id.text_rating)
        TextView textRating;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.title)
        TextView title;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
