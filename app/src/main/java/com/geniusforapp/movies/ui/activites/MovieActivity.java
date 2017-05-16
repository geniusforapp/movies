package com.geniusforapp.movies.ui.activites;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
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
import butterknife.ButterKnife;

public class MovieActivity extends BaseActivity implements MoviesView {

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
    FastItemAdapter fastItemAdapter;
    FooterAdapter<ItemProgress> itemFooterAdapter;

    MoviesPresenter moviesPresenter;
    @BindView(R.id.similar_progress)
    ProgressLinearLayout similarProgress;
    @BindView(R.id.trials_progress)
    ProgressLinearLayout trialsProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        showBack();


        final Movie movie = (Movie) getIntent().getSerializableExtra(Movie.class.getSimpleName());
        if (movie != null) {

            initPresenter();
            moviesPresenter.attachView(new MovieView() {
                @Override
                public void showLoading(boolean loading) {
                    progressFrameLayout.showLoading();
                }

                @Override
                public void showContent(final Movie m) {
                    progressFrameLayout.showContent();
                    Glide.with(getApplicationContext()).load(getString(R.string.image) + m.getBackdropPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade(0).into(backboardImage);
                    Glide.with(getApplicationContext()).load(getString(R.string.image) + m.getPosterPath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(coverImage);
                    details.setText(m.getOverview());
                    popularity.setText(popularity.getText().toString().replace("%number", String.valueOf(m.getPopularity())));
                    budget.setText(budget.getText().toString().replace("%number", String.valueOf(m.getBudget())));
                    budget.setVisibility(m.getBudget() != null ? View.VISIBLE : View.GONE);
                    appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                        @Override
                        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                            if (verticalOffset == 0) {
                                collapsingToolbar.setTitle("");
                            } else {
                                collapsingToolbar.setTitle(m.getOriginalTitle());
                            }

                        }
                    });


                    listGenres.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                    listGenres.setItemAnimator(new SlideRightAlphaAnimator());
                    FastItemAdapter<ItemGenres> genresFastItemAdapter = new FastItemAdapter<>();
                    for (Genre g : m.getGenres()) {
                        genresFastItemAdapter.add(new ItemGenres().setGenre(g));
                    }
                    listGenres.setAdapter(genresFastItemAdapter);
                }

                @Override
                public void showError(String message) {
                    progressFrameLayout.showEmpty(R.drawable.ic_exclamation, getString(android.R.string.dialog_alert_title), message);
                }
            });
            moviesPresenter.getMovie(String.valueOf(movie.getId()));
            initAdapter();
            initLists();
            getSimilarMovies(movie);

        }


    }

    private void getSimilarMovies(final Movie movie) {
        moviesPresenter.attachView(this);
        moviesPresenter.getRelated(1, "similar", String.valueOf(movie.getId()));
        listSimilar.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(int currentPage) {
                itemFooterAdapter.clear();
                itemFooterAdapter.add(new ItemProgress().withEnabled(true));
                // get data
                moviesPresenter.getRelated(currentPage, "similar", String.valueOf(movie.getId()));
            }
        });
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


    }

    private void initPresenter() {
        moviesPresenter = new MoviesPresenter(this);
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
    public void showMovies(Collection<Movie> movies) {
        itemFooterAdapter.clear();
        similarProgress.showContent();
        for (Movie m : movies) {
            fastItemAdapter.add(new ItemRelatedMovie().setMovie(m));
        }
    }

    @Override
    public void showLoading(boolean loading) {
        similarProgress.showLoading();
    }

    @Override
    public void showContent(String data) {

    }

    @Override
    public void showError(String message) {

    }
}

