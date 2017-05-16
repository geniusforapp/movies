package com.geniusforapp.movies.ui.fragemnts;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.geniusforapp.movies.mvp.presenter.MoviesPresenter;
import com.geniusforapp.movies.mvp.view.MoviesView;
import com.geniusforapp.movies.ui.activites.MovieActivity;
import com.geniusforapp.movies.ui.adapters.items.ItemMovie;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FooterAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter_extensions.items.ProgressItem;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;
import com.mikepenz.itemanimators.SlideUpAlphaAnimator;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.vlonjatg.progressactivity.ProgressFrameLayout;

import java.util.Collection;

import butterknife.BindView;

/**
 * Created by anajar on 5/15/17.
 */

public class MoviesFragment extends BaseFragment implements MoviesView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.list_movies)
    RecyclerView listMovies;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.frame_layout_progress)
    ProgressFrameLayout progressFrameLayout;


    MoviesPresenter moviesPresenter;

    FooterAdapter<ProgressItem> itemFooterAdapter;
    FastItemAdapter fastItemAdapter;

    private String type;

    public static MoviesFragment newInstance(String movies) {
        MoviesFragment moviesFragment = new MoviesFragment();
        moviesFragment.type = movies;
        return moviesFragment;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_movies;
    }

    @Override
    protected void bindView(View view) {


        initAdapters();
        initList();
        initSwipe();

        initPresenter();

    }


    private void initSwipe() {
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    private void initList() {
        listMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        listMovies.setItemAnimator(new SlideUpAlphaAnimator());
        listMovies.setAdapter(itemFooterAdapter.wrap(fastItemAdapter));
        listMovies.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    private void initPresenter() {
        moviesPresenter = new MoviesPresenter(getContext());
        moviesPresenter.attachView(this);
        moviesPresenter.getMovies(1, type);
    }

    private void initAdapters() {
        itemFooterAdapter = new FooterAdapter<>();
        fastItemAdapter = new FastItemAdapter();
        fastItemAdapter.withOnClickListener(new FastAdapter.OnClickListener() {
            @Override
            public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
                if (item instanceof ItemMovie) {
                    ItemMovie itemMovie = (ItemMovie) item;
                    Intent intent = new Intent(getContext(), MovieActivity.class);
                    intent.putExtra(Movie.class.getSimpleName(), itemMovie.getMovie());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), (View) itemMovie.getViewHolder(v).image, "cover");
                    startActivity(intent, options.toBundle());
                }
                return false;
            }
        });
    }

    @Override
    public void showLoading(boolean loading) {
        progressFrameLayout.showLoading();
    }

    @Override
    public void showContent(String data) {

    }

    @Override
    public void showError(String message) {
        progressFrameLayout.showEmpty(R.drawable.ic_exclamation, getString(android.R.string.dialog_alert_title), message);
    }

    @Override
    public void showMovies(Collection<Movie> nowPlaying) {
        itemFooterAdapter.clear();
        progressFrameLayout.showContent();
        swipeRefreshLayout.setRefreshing(false);
        for (Movie m : nowPlaying) {
            fastItemAdapter.add(new ItemMovie().setMovie(m));

        }

    }


    protected EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(itemFooterAdapter) {
        @Override
        public void onLoadMore(int currentPage) {
            itemFooterAdapter.clear();
            itemFooterAdapter.add(new ProgressItem().withEnabled(false));
            moviesPresenter.getMovies(currentPage, type);

        }
    };

    @Override
    public void onRefresh() {
        fastItemAdapter.clear();
        moviesPresenter.getMovies(1, type);
    }
}
