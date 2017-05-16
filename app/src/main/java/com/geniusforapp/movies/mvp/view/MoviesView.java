package com.geniusforapp.movies.mvp.view;

import com.geniusforapp.movies.mvp.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by anajar on 5/15/17.
 */

public interface MoviesView extends BaseView<String> {

    void showMovies(Collection<Movie> movies);


}
