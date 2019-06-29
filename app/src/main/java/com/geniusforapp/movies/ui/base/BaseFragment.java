package com.geniusforapp.movies.ui.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;


import org.jetbrains.annotations.NotNull;

import dagger.android.support.DaggerFragment;

/**
 * Created by anajar on 5/15/17.
 */

public abstract class BaseFragment extends DaggerFragment {


    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(setContentView(), container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
    }

    protected abstract int setContentView();

    protected abstract void bindView(View view);
}
