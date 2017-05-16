package com.geniusforapp.movies.ui.activites;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends BaseActivity {

    @BindView(R.id.backboard_image)
    ImageView backboardImage;
    @BindView(R.id.image_cover)
    ImageView coverImage;

    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.details)
    TextView details;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.budget)
    TextView budget;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        showBack();
        final Movie movie = (Movie) getIntent().getSerializableExtra(Movie.class.getSimpleName());
        if (movie != null) {
            Glide.with(this).load(getString(R.string.image) + movie.getBackdropPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(backboardImage);
            Glide.with(this).load(getString(R.string.image) + movie.getPosterPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(coverImage);

            details.setText(movie.getOverview());
            popularity.setText(popularity.getText().toString().replace("%number", String.valueOf(movie.getPopularity())));
            budget.setText(budget.getText().toString().replace("%number", String.valueOf(movie.getBudget())));
            budget.setVisibility(movie.getBudget() != null ? View.VISIBLE : View.GONE);
            appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (verticalOffset == 0) {
                        collapsingToolbar.setTitle("");
                    } else {
                        collapsingToolbar.setTitle(movie.getOriginalTitle());
                    }

                }
            });

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
