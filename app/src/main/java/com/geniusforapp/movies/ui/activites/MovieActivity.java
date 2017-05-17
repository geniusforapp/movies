package com.geniusforapp.movies.ui.activites;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Genre;
import com.geniusforapp.movies.mvp.model.Movie;
import com.geniusforapp.movies.mvp.presenter.MoviesPresenter;
import com.geniusforapp.movies.mvp.view.MovieView;
import com.geniusforapp.movies.mvp.view.MoviesView;
import com.geniusforapp.movies.ui.adapters.items.ItemGenres;
import com.geniusforapp.movies.ui.adapters.items.ItemProgress;
import com.geniusforapp.movies.ui.adapters.items.ItemRelatedMovie;
import com.mikepenz.fastadapter.adapters.FooterAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;
import com.mikepenz.itemanimators.SlideLeftAlphaAnimator;
import com.mikepenz.itemanimators.SlideRightAlphaAnimator;
import com.vlonjatg.progressactivity.ProgressFrameLayout;
import com.vlonjatg.progressactivity.ProgressLinearLayout;

import java.util.Collection;

import butterknife.BindView;
import ooo.oxo.library.widget.PullBackLayout;

public class MovieActivity extends BaseActivity implements PullBackLayout.Callback, MovieView, MoviesView, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.frame_layout_progress)
    ProgressFrameLayout progressFrameLayout;
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
    @BindView(R.id.list_similar)
    RecyclerView listSimilar;
    @BindView(R.id.list_trials)
    RecyclerView listTrials;
    @BindView(R.id.list_genres)
    RecyclerView listGenres;
    @BindView(R.id.similar_progress)
    ProgressLinearLayout similarProgress;
    @BindView(R.id.trials_progress)
    ProgressLinearLayout trialsProgress;
    @BindView(R.id.puller)
    PullBackLayout puller;


    FastItemAdapter fastItemAdapter;
    FooterAdapter<ItemProgress> itemFooterAdapter;

    MoviesPresenter moviesPresenter;


    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        showBack();
        // TODO: 5/17/17  add puller listener
        puller.setCallback(this);
        // TODO: 5/17/17 init movie data
        setupData();


        if (movie != null) {

            initAdapter();
            initLists();
            initPresenters();
        }


    }

    private void setupData() {
        movie = (Movie) getIntent().getSerializableExtra(Movie.class.getSimpleName());
    }


    private void initAdapter() {
        itemFooterAdapter = new FooterAdapter<>();
        fastItemAdapter = new FastItemAdapter();
    }

    private void initLists() {
        // similar list
        listSimilar.setItemAnimator(new SlideLeftAlphaAnimator());
        listSimilar.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        listSimilar.setAdapter(itemFooterAdapter.wrap(fastItemAdapter));
        listSimilar.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                itemFooterAdapter.clear();
                itemFooterAdapter.add(new ItemProgress().withEnabled(true));
                moviesPresenter.getRelated(currentPage, getString(R.string.key_similar), String.valueOf(movie.getId()));
            }
        });

    }

    private void initPresenters() {
        moviesPresenter = new MoviesPresenter(this);
        moviesPresenter.attachView(this);
        moviesPresenter.getMovie(String.valueOf(movie.getId()));
        moviesPresenter.getRelated(1, getString(R.string.key_similar), String.valueOf(movie.getId()));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }


    @Override
    public void onPullStart() {

    }

    @Override
    public void onPull(float v) {
        if (v > 0.2) {
            fab.hide();
        } else {
            fab.show();
        }
    }

    @Override
    public void onPullCancel() {

    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();
    }


    @Override
    public void showLoading(boolean loading) {
        progressFrameLayout.showLoading();
    }


    @Override
    public void showContent(final String m) {


    }

    @Override
    public void showMovie(Movie m) {
        // TODO: 5/17/17 show content view after loading finish
        progressFrameLayout.showContent();
        // TODO: 5/17/17 Load poster image and backboard image
        Glide.with(getApplicationContext()).load(getString(R.string.image) + m.getBackdropPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade(0).into(backboardImage);
        Glide.with(getApplicationContext()).load(getString(R.string.image) + m.getPosterPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(coverImage);

        // TODO: 5/17/17 set data text of the movie
        details.setText(m.getOverview());
        popularity.setText(popularity.getText().toString().replace("%number", String.valueOf(m.getPopularity())));
        budget.setText(budget.getText().toString().replace("%number", String.valueOf(m.getBudget())));
        budget.setVisibility(m.getBudget() != null ? View.VISIBLE : View.GONE);


        // TODO: 5/17/17 Add app bar listener
        appBar.addOnOffsetChangedListener(this);
        // TODO: 5/17/17 init genres method
        FastItemAdapter<ItemGenres> genresFastItemAdapter = getItemGenresFastItemAdapter(m);
        initGenresList(genresFastItemAdapter);
    }

    @NonNull
    private FastItemAdapter<ItemGenres> getItemGenresFastItemAdapter(Movie m) {
        FastItemAdapter<ItemGenres> genresFastItemAdapter = new FastItemAdapter<>();
        for (Genre g : m.getGenres()) {
            genresFastItemAdapter.add(new ItemGenres().setGenre(g));
        }
        return genresFastItemAdapter;
    }

    private void initGenresList(FastItemAdapter<ItemGenres> genresFastItemAdapter) {
        listGenres.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        listGenres.setItemAnimator(new SlideRightAlphaAnimator());
        listGenres.setAdapter(genresFastItemAdapter);
    }

    @Override
    public void showError(String message) {
        progressFrameLayout.showEmpty(R.drawable.ic_exclamation, getString(android.R.string.dialog_alert_title), message);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == -140) {
            collapsingToolbar.setTitle("");
        } else {
            collapsingToolbar.setTitle(movie.getOriginalTitle());
        }

    }

    @Override
    public void showMovies(Collection<Movie> movies) {
        itemFooterAdapter.clear();
        for (Movie m : movies) {
            fastItemAdapter.add(new ItemRelatedMovie().setMovie(m));
        }
    }
}

