package com.geniusforapp.movies.mvp.model;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by anajar on 5/17/17.
 */

@SuppressLint("ParcelCreator")
public class SearchedMovie implements SearchSuggestion {

    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public SearchedMovie setMovie(Movie movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public String getBody() {
        return movie.getOriginalTitle();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
