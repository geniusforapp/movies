package com.geniusforapp.movies.ui.adapters.items;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniusforapp.movies.R;
import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import butterknife.BindView;

/**
 * Created by anajar on 5/15/17.
 */


@Animate(Animation.ENTER_LEFT_DESC)
@NonReusable
@Layout(R.layout.item_movie)
public class Movie {

    com.geniusforapp.movies.mvp.model.Movie movie;
    Context context;

    public Movie(Context context) {
        this.context = context;
    }

    public com.geniusforapp.movies.mvp.model.Movie getMovie() {
        return movie;
    }

    public Movie setMovie(com.geniusforapp.movies.mvp.model.Movie movie) {
        this.movie = movie;
        return this;
    }


    @View(R.id.image)
    public ImageView image;
    @View(R.id.backboard_image)
    public ImageView backboardImage;
    @View(R.id.rating)
    public RatingBar ratingBar;
    @View(R.id.text_rating)
    public TextView textRating;
    @View(R.id.description)
    public TextView description;
    @View(R.id.title)
    public TextView title;


    @Resolve
    private void onResolved() {
        Glide.with(context).load(context.getString(R.string.image) + getMovie().getPosterPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(image);
        Glide.with(context).load(context.getString(R.string.image) + getMovie().getBackdropPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(backboardImage);
        description.setText(getMovie().getOverview());
        title.setText(getMovie().getOriginalTitle());

        Double rating = getMovie().getVoteAverage();
        ratingBar.setRating(Math.round(rating));
        textRating.setText("Vote (" + String.valueOf(getMovie().getVoteCount()) + ")");
    }

}
