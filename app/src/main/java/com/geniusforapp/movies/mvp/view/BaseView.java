package com.geniusforapp.movies.mvp.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by anajar on 5/14/17.
 */

public interface BaseView<T> extends MvpView {

    void showLoading(boolean loading);

    void showContent(T data);

    void showError(String message);

}
