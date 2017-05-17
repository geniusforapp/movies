package com.geniusforapp.movies.mvp.presenter;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.geniusforapp.movies.R;
import com.geniusforapp.movies.mvp.model.Movie;
import com.geniusforapp.movies.mvp.model.Result;
import com.geniusforapp.movies.mvp.model.Video;
import com.geniusforapp.movies.mvp.view.BaseView;
import com.geniusforapp.movies.mvp.view.MovieView;
import com.geniusforapp.movies.mvp.view.MoviesView;
import com.geniusforapp.movies.mvp.view.VideosView;
import com.google.gson.reflect.TypeToken;
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
                    .getAsParsed(new TypeToken<Result<Movie>>() {
                    }, new ParsedRequestListener<Result<Movie>>() {
                        @Override
                        public void onResponse(Result<Movie> response) {
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

    public void getRelated(final int page, String value, String movieId) {
        if (isViewAttached() && context != null) {
            final MoviesView moviesView = (MoviesView) getView();
            if (page == 1) {
                moviesView.showLoading(true);
            }
            AndroidNetworking.get(context.getString(R.string.base_url) + "{movie_id}/" + "{value}?api_key={api_key}&language=en-US&page={page}")
                    .addPathParameter("value", value)
                    .addPathParameter("movie_id", movieId)
                    .setTag(value)
                    .addPathParameter("api_key", context.getString(R.string.api_key))
                    .addPathParameter("page", String.valueOf(page))
                    .build()
                    .getAsParsed(new TypeToken<Result<Movie>>() {
                    }, new ParsedRequestListener<Result<Movie>>() {
                        @Override
                        public void onResponse(Result<Movie> response) {
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


    public void getMovie(String movieId) {
        if (isViewAttached() && context != null) {
            final MovieView movieView = (MovieView) getView();
            movieView.showLoading(true);
            AndroidNetworking.get(context.getString(R.string.base_url) + "{movie_id}" + "?api_key={api_key}&language=en-US")
                    .addPathParameter("movie_id", movieId)
                    .setTag(movieId)
                    .addPathParameter("api_key", context.getString(R.string.api_key))
                    .build()
                    .getAsObject(Movie.class, new ParsedRequestListener<Movie>() {
                        @Override
                        public void onResponse(Movie response) {
                            if (response != null) {
                                movieView.showMovie(response);
                            } else {
                                movieView.showError(context.getString(R.string.alert_no_more_data));
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            movieView.showError(anError.getResponse().message());
                        }
                    });

        }
    }


    public void search(String query, int page) {
        if (isViewAttached() && context != null) {
            final MoviesView moviesView = (MoviesView) getView();
            if (page == 1) {
                moviesView.showLoading(true);
            }
            AndroidNetworking.get("https://api.themoviedb.org/3/search/movie?api_key={api_key}&language=en-US&query={query}&page={page}&include_adult=true")
                    .setTag(query)
                    .addPathParameter("api_key", context.getString(R.string.api_key))
                    .addPathParameter("query", query)
                    .addPathParameter("page", String.valueOf(page))
                    .build()
                    .getAsParsed(new TypeToken<Result<Movie>>() {
                    }, new ParsedRequestListener<Result<Movie>>() {
                        @Override
                        public void onResponse(Result<Movie> response) {
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


    public void getVideos(String moveId) {
        if (isViewAttached() && context != null) {
            final VideosView videosView = (VideosView) getView();
            AndroidNetworking.get("https://api.themoviedb.org/3/movie/{movie_id}/videos?api_key={api_key}&language=en-US")
                    .setTag(moveId)
                    .addPathParameter("api_key", context.getString(R.string.api_key))
                    .addPathParameter("movie_id", moveId)
                    .build()
                    .getAsParsed(new TypeToken<Result<Video>>() {
                    }, new ParsedRequestListener<Result<Video>>() {
                        @Override
                        public void onResponse(Result<Video> response) {
                            if (response.getResults() != null) {
                                videosView.showVideos(response.getResults());
                            } else {
                                videosView.showContent(context.getString(R.string.alert_no_more_data));
                            }

                        }

                        @Override
                        public void onError(ANError anError) {
                            videosView.showError(anError.getResponse().message());
                        }
                    });
        }
    }


}
