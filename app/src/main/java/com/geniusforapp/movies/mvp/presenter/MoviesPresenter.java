package com.geniusforapp.movies.mvp.presenter;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.geniusforapp.movies.mvp.model.Result;
import com.geniusforapp.movies.mvp.view.BaseView;
import com.geniusforapp.movies.mvp.view.MoviesView;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anajar on 5/15/17.
 */

public class MoviesPresenter extends MvpBasePresenter<BaseView> {
    protected Context context;

    public static final String TAG = MoviesPresenter.class.getSimpleName();

    public MoviesPresenter(Context context) {
        this.context = context;
    }

    public void getMovies(final int page, String value) {
        if (isViewAttached() && context != null) {
            final MoviesView moviesView = (MoviesView) getView();
            if (page == 1) {
                moviesView.showLoading(true);
            }
            AndroidNetworking.get(context.getString(R.string.base_url) + "{value}?api_key={api_key}&language=en-US&page={page}")
                    .addPathParameter("value", value)
                    .setTag(value)
                    .addPathParameter("api_key", context.getString(R.string.api_key))
                    .addPathParameter("page", String.valueOf(page))
                    .build()
                    .getAsObject(Result.class, new ParsedRequestListener<Result>() {
                        @Override
                        public void onResponse(Result response) {
                            if (response.getResults() != null) {
                                moviesView.showMovies(response.getResults());
                            } else {
                                moviesView.showContent(context.getString(R.string.alert_no_more_data));
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            moviesView.showError(anError.getResponse().message());
                        }
                    });

        }
    }


}
