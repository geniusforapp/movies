package com.geniusforapp.movies.ui.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.bumptech.glide.Glide;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.geniusforapp.movies.mvp.model.SearchedMovie;
import com.geniusforapp.movies.mvp.presenter.MoviesPresenter;
import com.geniusforapp.movies.mvp.view.MoviesView;
import com.geniusforapp.movies.ui.adapters.pagers.PagerAdapter;
import com.geniusforapp.movies.ui.fragemnts.MoviesFragment;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.geniusforapp.movies.ui.adapters.pagers.PagerAdapter.PagerItem;

public class HomeActivity extends BaseActivity implements FloatingSearchView.OnQueryChangeListener, FloatingSearchView.OnSearchListener, MoviesView, SearchSuggestionsAdapter.OnBindSuggestionCallback, FloatingSearchView.OnMenuItemClickListener {


    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.home)
    ViewPager home;

    @BindView(R.id.floating_search_view)
    FloatingSearchView searchView;

    MoviesPresenter moviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        moviesPresenter = new MoviesPresenter(this);
        moviesPresenter.attachView(this);
        initPager();
        initSearchView();

    }

    private void initSearchView() {
        searchView.setOnQueryChangeListener(this);
        searchView.setOnSearchListener(this);
        searchView.setOnBindSuggestionCallback(this);
        searchView.setOnMenuItemClickListener(this);
    }

    private void initPager() {
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        addPages(pagerAdapter);
        home.setAdapter(pagerAdapter);
        tabs.setupWithViewPager(home);

        tabs.getTabAt(0).setText("").setIcon(ContextCompat.getDrawable(this, R.drawable.ic_video_list));
    }


    private void addPages(PagerAdapter pagerAdapter) {
        ArrayList<PagerItem> list = new ArrayList<>();
        list.add(new PagerItem(getString(R.string.tab_title_now_playing), MoviesFragment.newInstance(getString(R.string.key_now_playing))));
        list.add(new PagerItem(getString(R.string.tab_title_up_coming), MoviesFragment.newInstance(getString(R.string.key_upcoming))));
        list.add(new PagerItem(getString(R.string.tab_title_popular), MoviesFragment.newInstance(getString(R.string.key_popular))));
        list.add(new PagerItem(getString(R.string.tab_title_top_rated), MoviesFragment.newInstance(getString(R.string.key_top_rated))));

        pagerAdapter.addPages(list);
    }


    @Override
    public void onSearchTextChanged(String oldQuery, String newQuery) {
        if (!newQuery.isEmpty()) {
            moviesPresenter.search(newQuery, 1);
        } else {
            searchView.clearSuggestions();
        }


    }

    @Override
    public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
        SearchedMovie itemMovie = (SearchedMovie) searchSuggestion;
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(Movie.class.getSimpleName(), itemMovie.getMovie());
        startActivity(intent);
        searchView.clearSuggestions();
        searchView.clearQuery();
    }

    @Override
    public void onSearchAction(String currentQuery) {
        if (!currentQuery.isEmpty()) {
            moviesPresenter.search(currentQuery, 1);
        } else {
            searchView.clearSuggestions();
        }
    }

    @Override
    public void showLoading(boolean loading) {
        searchView.showProgress();
    }

    @Override
    public void showContent(String data) {

    }

    @Override
    public void showError(String message) {
        searchView.clearSuggestions();
    }

    @Override
    public void showMovies(Collection<Movie> movies) {
        searchView.hideProgress();
        List<SearchedMovie> searchedMovies = new ArrayList<>();
        for (Movie m : movies) {
            searchedMovies.add(new SearchedMovie().setMovie(m));
        }
        searchView.swapSuggestions(searchedMovies);
    }

    @Override
    public void onBindSuggestion(View suggestionView, ImageView leftIcon, TextView textView, SearchSuggestion item, int itemPosition) {
        SearchedMovie searched = (SearchedMovie) item;
        Glide.with(this).load(getString(R.string.image) + searched.getMovie().getPosterPath()).bitmapTransform(new CropCircleTransformation(this)).into(leftIcon);
        textView.setTextColor(ContextCompat.getColor(this, R.color.md_white_1000));
    }

    @Override
    public void onActionMenuItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                new LibsBuilder().withAboutAppName(getString(R.string.app_name))
                        //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                        .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                        //start the activity
                        .start(this);
                break;
            default:
                break;
        }
    }
}



