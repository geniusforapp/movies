package com.geniusforapp.movies.mvp.presenter;

import android.content.Context;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by anajar on 5/15/17.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V view;
    protected Context context;


    public BasePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        this.view = null;
    }

    public V getView() {
        return view;
    }

    protected boolean isViewAttached() {
        return view != null && context != null;
    }


}
